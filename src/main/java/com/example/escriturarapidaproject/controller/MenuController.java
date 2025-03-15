package com.example.escriturarapidaproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

/**
 * Controlador para la gestión del menú principal del juego.
 * Permite la navegación entre diferentes escenas del juego de manera dinámica.
 * @author Kevin Muñoz
 */
public class MenuController {

    /**
     * Cambia la escena actual a la vista correspondiente según el botón presionado.
     *
     * <p>El método obtiene el botón que activó el evento, extrae su ID y lo utiliza para
     * determinar qué archivo FXML debe cargarse.</p>
     *
     * @param event Evento de acción generado al hacer clic en un botón del menú.
     */
    @FXML
    public void changeScene(ActionEvent event) {
        try {
            // Obtener el botón que disparó el evento y su ID
            String buttonId = ((Button) event.getSource()).getId();

            // Generar automáticamente el nombre del archivo FXML basado en el ID del botón
            String fxmlFile = buttonId.replace("Button", "-view.fxml");

            // Cargar la nueva escena
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/escriturarapidaproject/view/" + fxmlFile));
            Parent newScene = loader.load();

            // Obtener la ventana actual (Stage) y cambiar la escena
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(newScene));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
