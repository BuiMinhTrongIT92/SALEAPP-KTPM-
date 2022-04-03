module com.ktpm.saleapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.ktpm.saleapp to javafx.fxml;
    exports com.ktpm.saleapp;
}
