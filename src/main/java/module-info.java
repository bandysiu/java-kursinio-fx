module com.example.kursinis {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kursinis to javafx.fxml;
    exports com.example.kursinis;
}