package sample;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import DbConnection.DbHandler;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button signup;

    @FXML
    private Button login;

    @FXML
    private CheckBox remember;

    @FXML
    private ImageView progress;

    @FXML
    private Label lbl_username;

    @FXML
    private Label lbl_password;

    @FXML
    private Label forgotpassword;

    private DbHandler handler;
    private Connection connection;
    private java.sql.PreparedStatement pst;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        progress.setVisible(false);
        username.setStyle("-fx-text-inner-color: #a0a2ab;");
        password.setStyle(("-fx-text-inner-color: #a0a2ab;"));

        handler = new DbHandler();

    }

    @FXML
    void loginAction(ActionEvent event) {

        progress.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        pt.setOnFinished(ev -> {

            connection = handler.getConnection();
            String ql = "SELECT * from user_information where names=? and password=?";

            try {
                pst = connection.prepareStatement(ql);
                pst.setString(1, username.getText());
                pst.setString(2, password.getText());
                ResultSet rs = pst.executeQuery();

                int count = 0;
                while (rs.next()) {
                    count++;
                }
                if (count == 1) {
                    login.getScene().getWindow().hide();

                    Stage home = new Stage();
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                        Scene scene = new Scene(root);
                        home.setScene(scene);
                        home.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Username and password is not correct");
                    alert.show();
                    progress.setVisible(false);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }


        });
        pt.play();

        if(username.getText().trim().isEmpty() && password.getText().trim().isEmpty()){
            lbl_username.setText("Username is empty");
            lbl_password.setText("password is empty");
        }
        else if(username.getText().trim().isEmpty()){
            lbl_username.setText("Username is empty");
        }
        else if(password.getText().trim().isEmpty()){
            lbl_password.setText("password is empty");
        }


    }

    @FXML
    public void signUp(ActionEvent event) throws IOException {

        login.getScene().getWindow().hide();

        Stage signup = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene scene = new Scene(root);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }
}
