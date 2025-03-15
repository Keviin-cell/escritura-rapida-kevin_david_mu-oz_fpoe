module com.example.escriturarapidaproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.escriturarapidaproject.controller to javafx.fxml;

    opens com.example.escriturarapidaproject to javafx.fxml;
    exports com.example.escriturarapidaproject;
}