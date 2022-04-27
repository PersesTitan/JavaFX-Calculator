module com.example.javafxcalculator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.javafxcalculator to javafx.fxml;
    exports com.example.javafxcalculator;
}