package com.example.demo.IES.Mailer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSending implements Runnable{
    @Autowired
    JavaMailSender Sender;
    public String Receiver;
    public String Sub;
    public String Body;


    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String receiver) {
        Receiver = receiver;
    }

    public String getSub() {
        return Sub;
    }

    public void setSub(String sub) {
        Sub = sub;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }


    public void SendMail(String toEmail, String Subject, String body){
        SimpleMailMessage M=new SimpleMailMessage();
        M.setFrom("hamza.cherkaoui1234@gmail.com");
        M.setTo((toEmail));
        M.setText(body);
        M.setSubject(Subject);
        Sender.send(M);
        System.out.println("message received ");
    }

    @Override
    public void run() {
        this.SendMail(this.getReceiver(),this.getSub(),this.getBody());
    }
}

