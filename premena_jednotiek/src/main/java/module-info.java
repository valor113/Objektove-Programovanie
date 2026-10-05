module com.example.premena_jednotiek {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.premena_jednotiek to javafx.fxml;
    exports com.example.premena_jednotiek;
}