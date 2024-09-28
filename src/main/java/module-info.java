module com.example.npcbank {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.npcbank to javafx.fxml;
    exports com.example.npcbank;
    exports com.example.npcbank.models;
    opens com.example.npcbank.models to javafx.fxml;
    exports com.example.npcbank.controllers;
    opens com.example.npcbank.controllers to javafx.fxml;
}