package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminPanelController implements Initializable {

    @FXML
    private Button login;

    @FXML
    private TextField AdminEmail;

    @FXML
    private TextField AdminPassword;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void loginAction(ActionEvent event) {
        String email = AdminEmail.getText();
        String password = AdminPassword.getText();
        if(email.trim().equalsIgnoreCase(EmailPassword.email) && password.trim().equalsIgnoreCase(EmailPassword.password)){
            System.out.println("Admin login successful");
            Stage home = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                Scene scene = new Scene(root);
                home.setScene(scene);
                home.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Admin login failed");
        }
    }
}
