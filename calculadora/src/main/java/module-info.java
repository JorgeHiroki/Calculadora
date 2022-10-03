module com.mycompany.calculadora {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.calculadora to javafx.fxml;
    exports com.mycompany.calculadora;
}
