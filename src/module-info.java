module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.xerial.sqlitejdbc;
    requires java.sql;

    opens gui to javafx.fxml;
    exports gui;
    exports main;
}