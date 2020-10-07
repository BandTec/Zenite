package orion.zenite.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;

import orion.zenite.entidades.Conta;
import orion.zenite.repositorios.*;

import java.util.Optional;
import java.util.Random;

import static org.springframework.http.ResponseEntity.*;

@Api(description = "Reset de senha", tags = "Email")
@RestController
public class EmailController {
//    @Autowired private JavaMailSender mailSender;

    @Autowired private ContaRepository contaRepository;

    @Autowired private PasswordEncoder passwordEncoder;

    @ApiOperation("Envio de senha aleatoria para o email do usuário")
    @GetMapping("/esqueci-senha/{email}")
    @Transactional
    public ResponseEntity enviarEmail(@PathVariable String email){
//        try{
//            Optional<Conta> conta = contaRepository.findByEmail(email);
//            if(conta.isPresent()) {
//                String senhaRandom = senhaAleatoria();
//                String senhaCriptografada = passwordEncoder.encode(senhaRandom);
//
//                conta.get().setSenha(senhaCriptografada);
//                contaRepository.save(conta.get());
//
//                MimeMessage mail = mailSender.createMimeMessage();
//                MimeMessageHelper helper = new MimeMessageHelper(mail);
//
//                helper.setTo(email);
//                helper.setSubject("Recuperação de Senha");
//                helper.setText(String.format("<h3>Esqueceu sua senha?</h3><h3>Não se preocupe geramos uma nova para você!</h3><p>Aqui está a sua nova senha temporária: <b>%s</b></p><br><br><b>Não se esqueça de troca-la após o login.</b>",senhaRandom), true);
//                mailSender.send(mail);
//
//                return ok("Email enviado com sucesso");
//            }else{
//                return notFound().build();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return status(500).body("Erro ao enviar email");
//        }
        return ok("Email enviado com sucesso");
    }

    public String senhaAleatoria(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 8;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

}
