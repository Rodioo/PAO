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
    requires org.jetbrains.annotations;
    requires java.sql;

    opens pao to javafx.fxml;
    exports pao;
    exports gui.welcome;
    opens gui.welcome to javafx.fxml;
    opens gui.login to javafx.fxml;
    opens gui.register to javafx.fxml;
    opens gui.student to javafx.fxml;
    opens gui.teacher to javafx.fxml;
    opens gui.course to javafx.fxml;
    opens gui.chapter to javafx.fxml;
    opens gui.question to javafx.fxml;
    exports utils;
    opens utils to javafx.fxml;
}