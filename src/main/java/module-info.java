module org.example.sigimd {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.sigimd.controllers to javafx.fxml;
    exports org.example.sigimd;
    exports org.example.sigimd.models;
    opens org.example.sigimd.models to javafx.fxml;
}