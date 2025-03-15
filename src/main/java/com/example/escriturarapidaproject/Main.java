package com.example.escriturarapidaproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal que inicia la aplicación de JavaFX.
 * Carga la vista inicial y configura la ventana principal.
 * @author Kevin Muñoz
 */
public class Main extends Application {

    /**
     * Método que inicia la aplicación y configura la ventana principal.
     *
     * @param primaryStage La ventana principal de la aplicación.
     * @throws IOException Si hay un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/escriturarapidaproject/images/logo_solo_icon.png")));
        // Cargar el archivo FXML correctamente
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/escriturarapidaproject/view/menu-view.fxml"));

        Parent root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Escritura Rapida - The Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Método principal que inicia la aplicación de JavaFX.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }
}