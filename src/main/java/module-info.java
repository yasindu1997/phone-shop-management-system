module lk.acpt.demofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    exports lk.acpt.demofx;
    opens lk.acpt.demofx to javafx.fxml;

    exports lk.acpt.demofx.controller;
    opens lk.acpt.demofx.controller to javafx.fxml;

    exports lk.acpt.demofx.tm;
    opens lk.acpt.demofx.tm to javafx.fxml;

    exports lk.acpt.demofx.entity;
    opens lk.acpt.demofx.entity;
}