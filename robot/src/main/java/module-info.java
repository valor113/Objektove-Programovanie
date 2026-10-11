module com.example.robot {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.robot to javafx.fxml;
    exports com.example.robot;
}