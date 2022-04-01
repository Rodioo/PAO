module pao {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens pao to javafx.fxml;
    exports pao;
    exports gui.welcome;
    opens gui.welcome to javafx.fxml;
    opens gui.login to javafx.fxml;
    opens gui.register to javafx.fxml;
}