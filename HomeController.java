package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Button back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void backAction(ActionEvent e) throws IOException {
        back.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("LoginMain.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }
}
