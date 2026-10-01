module com.example.kalkulacka {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kalkulacka to javafx.fxml;
    exports com.example.kalkulacka;
}