package lk.acpt.demofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private AnchorPane root;

    @FXML
    void login(ActionEvent event) throws IOException {

        //catch the stage
        Stage stage = (Stage) root.getScene().getWindow();

        //load next fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("load-view.fxml"));

        //create a scene
        Scene scene = new Scene(fxmlLoader.load());

        //set for the stage
        stage.setScene(scene);

    }

    @FXML
    void cancle(ActionEvent event) {
        //jvm shutdown
        System.exit(0);
    }
}
