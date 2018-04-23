package com.example.apurva.gmail_api;

import android.media.MediaCas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MainActivity extends AppCompatActivity {

    EditText to,subject,msg;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        to=findViewById(R.id.to);
        subject=findViewById(R.id.subject);
        msg=findViewById(R.id.message);
        send=findViewById(R.id.send);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Thread t=new Thread(){
                    public void run(){
                        String e1=to.getText().toString();
                        String e2=subject.getText().toString();
                        String e3=msg.getText().toString();

                        String from="apurvamandal27@gmail.com";
                        String pass="*****";
                        String host="smtp.gmail.com";
                        try {

                            Properties p=new Properties();
                            Session sess=Session.getInstance(p);

                            MimeMessage msgg=new MimeMessage(sess);
                            InternetAddress toId=new InternetAddress(e1);
                            InternetAddress fromId=new InternetAddress(from);

                            msgg.setRecipient(Message.RecipientType.TO,toId);
                            msgg.setFrom(fromId);
                            msgg.setSubject(e2);
                            msgg.setText(e3);

                            Transport tpt=sess.getTransport("smtps");
                            tpt.connect(host,from,pass);
                            tpt.sendMessage(msgg,msgg.getAllRecipients());

                        }catch (Exception ex){

                        }
                    }
                };
                t.start();
            }
        });
    }
}
