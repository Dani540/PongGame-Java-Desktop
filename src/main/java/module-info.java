module com.app.fxtestgame {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.app.fxtestgame to javafx.fxml;
    exports com.app.fxtestgame;
}