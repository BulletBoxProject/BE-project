package com.hanghae.bulletbox.member.service;

import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;

import java.util.Random;

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.DUPLICATE_EMAIL_MSG;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    private final MemberRepository memberRepository;
    private String authNum;

    private void checkDuplicatedEmail(String email){
        memberRepository.findByEmail(email).ifPresent(
                m -> {throw new IllegalArgumentException(DUPLICATE_EMAIL_MSG.getMsg());
                });
    }
    public MimeMessage createMessage(String to)throws MessagingException, UnsupportedEncodingException{

        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to);
        message.setSubject("회원가입 이메일 인증");

        String msgg = "";
        msgg += "<div style='margin:100px;'>";
        msgg += "<h1> 안녕하세요</h1>";
        msgg += "<br>";
        msgg += "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요<p>";
        msgg += "<br>";
        msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg += "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg += "<div style='font-size:130%'>";
        msgg += "CODE : <strong>";
        msgg += authNum + "</strong>";
        msgg += "</div>";
        message.setText(msgg, "utf-8", "html");
        message.setFrom(new InternetAddress("jkdev12345@gmail.com", "BulletBox"));

        return message;
    }

    public String createCode(){
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for(int i = 0; i< 8; i++){
            int index = random.nextInt(3);

            switch (index) {
                case 0 -> key.append((char) ((int) random.nextInt(26) + 97));
                case 1 -> key.append((char) (int) random.nextInt(26) + 65);
                case 2 -> key.append(random.nextInt(9));
            }
        }
        return authNum = key.toString();
    }

    public String sendSimpleMessage(String sendEmail, MemberDto memberDto) throws Exception{

        String email = memberDto.getEmail();

        checkDuplicatedEmail(email);

        authNum = createCode();

        MimeMessage message = createMessage(sendEmail);
        try{
            javaMailSender.send(message);
        }catch (MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }

        return authNum;
    }

}
