package sample;

import com.sun.management.VMOption;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.awt.event.MouseAdapter;

public class MailController {
    @FXML
    private Label lblExit;

    @FXML
    private Label lblSend;

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtSubject;

    @FXML
    private TextArea txtMessage;

    @FXML
    void lblExit(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void lblSend(MouseEvent event) {
        try{
            if (txtMail.getText() != null && ! txtMail.getText().equals("")){
                Email email = new Email();
                email.sendMail(txtMail.getText(), txtSubject.getText(), txtMessage.getText());
            }else{
                JOptionPane.showMessageDialog(null, "Value Required: Mail", "Error", JOptionPane.OK_OPTION);
            }
        }catch (Exception e){

        }
    }
}
