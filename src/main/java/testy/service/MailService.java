package testy.service;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @author heidisu
 */
public final class MailService {
  public void sendMail(String title, String content, String address) {
    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);

    try {
      Message msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress("admin@bibliotek.com"));
      msg.addRecipient(Message.RecipientType.TO,
          new InternetAddress(address));
      msg.setSubject(title);
      msg.setText(content);
      //Transport.send(msg);

    } catch (AddressException e) {
      Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null, e);
    } catch (MessagingException e) {
      Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null, e);
    }
  }
}
