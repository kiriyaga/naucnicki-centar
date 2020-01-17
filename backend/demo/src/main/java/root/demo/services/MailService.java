package root.demo.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.Properties;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Metoda koja salje aktivacioni link(token) korisniku koji se registrovao
     *
     * @param email
     * @param token
     * @throws MailException
     */

    @Async
    public void sendRegistrationActivation(String email, String token) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("majkemijevrosime@gmail.com");
        mail.setTo(email);
        mail.setSubject("Activate account:");
        mail.setText("To activate account click on link: " + "http://localhost:8080/register/validate/" + token);
        mailSender.send(mail);
    }

    @Async
    public void sendTest(String subject, String message) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("makiarambasic@gmail.com");
        mail.setFrom("majkemijevrosime@gmail.com");
        mail.setSubject(subject);
        mail.setText(message);

        mailSender.send(mail);
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("majkemijevrosime@gmail.com");
        mailSender.setPassword("teamrocketisa");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");

        return mailSender;
    }

}