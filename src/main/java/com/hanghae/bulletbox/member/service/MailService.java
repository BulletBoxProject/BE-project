package com.hanghae.bulletbox.member.service;

import com.hanghae.bulletbox.common.redis.RedisUtil;
import com.hanghae.bulletbox.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;

import java.util.Random;

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.DIFFERENT_CODE_MSG;
import static com.hanghae.bulletbox.common.exception.ExceptionMessage.DUPLICATE_EMAIL_MSG;
import static com.hanghae.bulletbox.common.exception.ExceptionMessage.FAILED_TO_SEND_MAIL;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    private final MemberRepository memberRepository;

    private final RedisUtil redisUtil;

    private String authNum;


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
        StringBuilder key = new StringBuilder();

        for(int i = 0; i< 8; i++){
            int index = random.nextInt(3);

            switch (index) {
                case 0 -> key.append((char) (int) random.nextInt(26) + 97);
                case 1 -> key.append((char) (int) random.nextInt(26) + 65);
                case 2 -> key.append(random.nextInt(9));
            }
        }
        return authNum = key.toString();
    }

    public void sendSimpleMessage(String email) throws Exception{

        if(memberRepository.findByEmail(email).isPresent()){
            throw new IllegalArgumentException(DUPLICATE_EMAIL_MSG.getMsg());
        }

        String authCode = redisUtil.getData(email);
        if (authCode != null) {
            redisUtil.deleteData(email);
        }

        authNum = createCode();

        MimeMessage message = createMessage(email);
        try{
            javaMailSender.send(message);
        }catch (MailException e){
            e.printStackTrace();
            throw new IllegalArgumentException(FAILED_TO_SEND_MAIL.getMsg());
        }

        redisUtil.setDataExpire(email, authNum, 5 * 60 * 1000L);
    }

    @Transactional
    public void verifyCode(String email, String code) {
        String authCode = redisUtil.getData(email);
        if (!authCode.equals(code)) {
            throw new IllegalStateException(DIFFERENT_CODE_MSG.getMsg());
        }
    }

}
