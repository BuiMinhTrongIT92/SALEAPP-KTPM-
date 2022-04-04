module com.ktpm.saleapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.ktpm.saleapp to javafx.fxml;
    exports com.ktpm.saleapp;
    exports com.ktpm.pojo;
}
