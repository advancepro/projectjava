package sample;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import DbConnection.DbHandler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SignUpControllers extends Component implements Initializable {

    @FXML
    private AnchorPane parentPane;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private RadioButton male;

    @FXML
    private ToggleGroup genders;

    @FXML
    private RadioButton female;

    @FXML
    private TextField email;

    @FXML
    private Button signup;

    @FXML
    private Button login;

    @FXML
    private ImageView progress;

    @FXML
    private RadioButton other;

    private Connection connection;
    private DbHandler handler;
    private PreparedStatement pst;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        progress.setVisible(false);
        username.setStyle("-fx-text-inner-color: #a0a2ab;");
        password.setStyle("-fx-text-inner-color: #a0a2ab;");
        email.setStyle("-fx-text-inner-color: #a0a2ab;");

        handler = new DbHandler();

    }

    @FXML
    public void signUp(ActionEvent event) {
        progress.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        pt.setOnFinished(ev -> {
            System.out.print("SingUp successfull");
        });
        pt.play();

        String insert = "INSERT INTO user_information(names, password, gender, email)"
                + "VALUES (?,?,?,?)";

        connection = handler.getConnection();
        try {
            pst = connection.prepareStatement(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            pst.setString(1, username.getText());
            pst.setString(2, password.getText());
            pst.setString(4, getGender());
            pst.setString(3, email.getText());

            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loginAction(ActionEvent event) throws IOException {
        signup.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("LoginMain.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }

    public String getGender() {
        String gen = "";

        if (male.isSelected()) {
            gen = "Male";
        } else if (female.isSelected()) {
            gen = "Female";
        } else if (other.isSelected()) {
            gen = "Other";
        }
        return gen;
    }
}
