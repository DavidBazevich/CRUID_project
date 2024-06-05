module com.example.lab_9 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.lab_9 to javafx.fxml;
    exports com.example.lab_9;
    exports com.example.lab_9.Constants;
    opens com.example.lab_9.Constants to javafx.fxml;
    exports com.example.lab_9.Controllers;
    opens com.example.lab_9.Controllers to javafx.fxml;
}