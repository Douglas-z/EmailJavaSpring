package br.com.senac.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void enviarEmail(String emailDe,
                            String emailPara,
                            String titulo,
                            String mensagem) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(emailDe);
        email.setTo(emailPara);
        email.setSubject(titulo);
        email.setText(mensagem);

        javaMailSender.send(email);
    }
}
