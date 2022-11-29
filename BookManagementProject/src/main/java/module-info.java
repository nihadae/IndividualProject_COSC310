module com.example.bookmanagementproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
	requires jdk.incubator.vector;
    requires org.junit.jupiter.api;
    requires org.junit.platform.commons;
    requires json.simple;

    opens com.example.bookmanagementproject to javafx.fxml;
    exports com.example.bookmanagementproject;
}