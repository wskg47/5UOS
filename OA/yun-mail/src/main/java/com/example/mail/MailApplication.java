package com.example.mail;

import com.example.server.pojo.MailConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MailApplication {
    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class, args);
    }

    // 创建消息队列 名字是 mail.welcome
    @Bean
    public Queue queue(){
        return new Queue(MailConstant.MAIL_QUEUE_NAME);
    };
}
