module com.example.messenger {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.rmi;

    opens com.example.messenger to javafx.fxml;
    exports com.example.messenger;
}