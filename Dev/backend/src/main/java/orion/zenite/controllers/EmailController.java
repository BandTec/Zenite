package orion.zenite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;

import static org.springframework.http.ResponseEntity.*;

@RestController
public class EmailController {
    @Autowired private JavaMailSender mailSender;

    @GetMapping("/esqueci-senha/{email}")
    public ResponseEntity enviarEmail(@PathVariable String email){
        try{

            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail);

            helper.setTo(email);
            helper.setSubject("Teste Envio de e-mail");
            helper.setText("<h2>Esqueceu sua senha seu bosta</h2>", true);
            mailSender.send(mail);

            return ok("Email enviado com sucesso");
        }catch (Exception e){
            e.printStackTrace();
            return status(500).body("Erro ao enviar email");
        }
    }
}
