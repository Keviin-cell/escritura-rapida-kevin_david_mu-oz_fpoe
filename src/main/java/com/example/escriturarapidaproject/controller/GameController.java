package com.example.escriturarapidaproject.controller;

import com.example.escriturarapidaproject.model.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Controlador para gestionar la lógica del juego "Escritura Rápida".
 * Se encarga de manejar la interacción con la interfaz de usuario y la lógica del juego.
 * Administra las oportunidades, el tiempo restante y el avance de niveles.
 * @author Kevin Muñoz
 */
public class GameController {
    @FXML
    private Label palabraLabel, puntosLabel, nivelLabel, tiempoLabel, oportunidadesLabel;
    @FXML
    private TextField entradaUsuario;
    @FXML
    private Button nextButton;
    @FXML
    private Button menuButton;
    @FXML
    private Label modoLabel;
    @FXML
    private ImageView solView;

    /** Array con las rutas de las imágenes que representan las fases del sol eclipsado. */
    private final String[] solEstados = {
            "/com/example/escriturarapidaproject/images/eclipse/eclipse-0%.png",
            "/com/example/escriturarapidaproject/images/eclipse/eclipse-25%.png",
            "/com/example/escriturarapidaproject/images/eclipse/eclipse-25%.png",
            "/com/example/escriturarapidaproject/images/eclipse/eclipse-75%.png",
            "/com/example/escriturarapidaproject/images/eclipse/eclipse-100%.png",
    };

    private boolean respuestaCorrecta;
    private Game game;
    private Timeline timeline;
    private boolean modoFrases;

    /**
     * Inicializa la interfaz del juego cuando se carga la vista.
     * Determina si el juego está en modo palabras o frases y comienza el temporizador.
     */
    @FXML
    public void initialize() {
        this.modoFrases = modoLabel.getText().equalsIgnoreCase("Modo Frases");
        game = new Game(modoFrases);
        actualizarUI();
        iniciarTemporizador();
        actualizarSol();

        entradaUsuario.setOnAction(event -> validarEntrada());
    }

    /**
     * Inicia el temporizador del juego y lo actualiza cada segundo.
     * Controla el tiempo restante de la partida y ejecuta la lógica cuando el tiempo llega a cero.
     */
    private void iniciarTemporizador() {
        if (timeline != null) {
            timeline.stop();
        }

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            game.reducirTiempo();
            actualizarTiempo();
        }));

        timeline.setCycleCount(game.getTiempoRestante());
        timeline.play();
    }

    /**
     * Verifica si el tiempo restante sigue disponible.
     * Si el tiempo llega a 0, finaliza la partida y muestra el mensaje de fin de juego.
     */
    private void actualizarTiempo() {
        if (game.getTiempoRestante() > 0) {
            actualizarUI();
        } else {
            timeline.stop();
            manejarFinDelJuego();
        }
    }

    /**
     * Actualiza la imagen del sol según la cantidad de oportunidades restantes.
     * Representa visualmente el eclipse progresivo del sol en función de los errores del jugador.
     */
    private void actualizarSol() {
        int indice = Math.max(0, 4 - game.getOportunidades()); // Calcula qué imagen usar
        solView.setImage(new Image(getClass().getResourceAsStream(solEstados[indice])));
    }

    /**
     * Valida la entrada del usuario y verifica si la palabra/frase ingresada es correcta.
     * Si es incorrecta, se reduce una oportunidad y se actualiza el eclipse del sol.
     */
    @FXML
    public void validarEntrada() {
        String entradaTexto = entradaUsuario.getText().trim();

        if (entradaTexto.equalsIgnoreCase(game.getPalabraOFraseActual())) {
            respuestaCorrecta = true;
            nextButton.setDisable(false);
        } else {
            game.perderOportunidad();
            actualizarSol();
            mostrarAlertaPalabraIncorrecta();
        }

        // Llama a la siguiente palabra después de la validación
        siguientePalabra();

        entradaUsuario.clear();
        actualizarUI();

        if (game.juegoTerminado()) {
            manejarFinDelJuego();
        }
    }

    /**
     * Avanza a la siguiente palabra o frase si la respuesta anterior fue correcta.
     * Reinicia el temporizador.
     */
    @FXML
    public void siguientePalabra() {
        if (respuestaCorrecta) {
            game.avanzarNivel();
            if (timeline != null) {
                timeline.stop();
            }
            iniciarTemporizador(); // Reiniciar temporizador con nuevo tiempo
            respuestaCorrecta = false;
        }
        actualizarUI();
    }


    /**
     * Maneja el fin del juego, mostrando una alerta con el puntaje final y redirigiendo al menú principal.
     * Detiene el temporizador y muestra los datos finales del jugador.
     */
    private void manejarFinDelJuego() {
        nextButton.setDisable(false);
        palabraLabel.setText("Juego Terminado");
        tiempoLabel.setText("00");

        if (timeline != null) {
            timeline.stop();
        }

        int nivelesCompletados = game.getNivel() - 1;
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Juego Terminado");
            alert.setHeaderText(null);
            alert.setContentText("¡Felicidades! \nHas llegado muy lejos, tu progreso fue:\n" +
                    "\nPuntaje final: " + game.getPuntos() +
                    "\nNiveles completados: " + nivelesCompletados +
                    "\n\nEl juego ha terminado. ¡Inténtalo de nuevo!");

            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/escriturarapidaproject/images/logo_solo_icon.png")));


            alert.showAndWait();

            try {
                volverAlMenu();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Actualiza la interfaz de usuario con la información más reciente del juego.
     * Muestra la palabra/frase actual, nivel, puntaje, tiempo restante y oportunidades disponibles.
     */
    private void actualizarUI() {
        palabraLabel.setText(game.getPalabraOFraseActual());
        puntosLabel.setText(String.valueOf(game.getPuntos()));
        nivelLabel.setText(String.valueOf(game.getNivel()));
        tiempoLabel.setText(String.valueOf(game.getTiempoRestante()));
        oportunidadesLabel.setText(String.valueOf(game.getOportunidades()));
    }

    /**
     * Muestra una alerta cuando la palabra ingresada es incorrecta.
     * La alerta no detiene el tiempo del juego y solo se muestra si aún hay oportunidades disponibles.
     */
    private void mostrarAlertaPalabraIncorrecta() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Palabra Incorrecta");
            alert.setHeaderText(null);
            alert.setContentText("Inténtalo de nuevo. ¡Sigue escribiendo antes de que acabe el tiempo!");

            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/escriturarapidaproject/images/logo_solo_icon.png")));

            if (game.getOportunidades() > 0) {
                alert.show();
            }

        });
    }

    /**
     * Regresa al menú principal cargando la escena correspondiente.
     * Detiene el juego y cambia a la vista del menú principal.
     * @throws IOException Si ocurre un error al cargar la vista del menú.
     */
    public void volverAlMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/escriturarapidaproject/view/menu-view.fxml"));
        Parent newScene = loader.load();

        Stage stage = (Stage) menuButton.getScene().getWindow();
        stage.setScene(new Scene(newScene));
        stage.show();
    }
}
