package sample;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.*;

public class Email {

    public void sendMail(String address, String subject, String message) throws Exception {
        String from  = "smyrbetul77@gmail.com";
        String pass = "121015betul2000";
        String[] to = {address};
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.password", pass);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        InternetAddress[] toaddress = new InternetAddress[to.length];
        for (int i = 0; i < to.length; i++){
            toaddress[i] = new InternetAddress(to[i]);
        }

        for (int i = 0; i < toaddress.length; i++){
            msg.setRecipient(Message.RecipientType.TO, toaddress[i]);
        }

        msg.setSubject(subject);
        msg.setContent(message, "text/xml; encoding=utf-8");
        Transport transport = session.getTransport("smtp");
        transport.connect(host, from, pass);
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
    }
}
