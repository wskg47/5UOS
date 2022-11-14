package com.example.server.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.server.pojo.Employee;
import com.example.server.pojo.MailConstant;
import com.example.server.pojo.MailLog;
import com.example.server.service.IEmployeeService;
import com.example.server.service.IMailLogService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 邮件发送定时任务
 * @author: wskg
 * @date: 2022/11/08
 */
@Component
public class MailTask {

    @Autowired
    private IMailLogService mailLogService;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 邮件发送定时任务
     * 十秒执行一次
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void mailTask(){
        // 查询状态处于投递中的数据 status=0   尝试时间小于当前时间
        List<MailLog> list = mailLogService.list(new QueryWrapper<MailLog>().eq("status", 0).apply("tryTime", LocalDateTime.now()));
        list.forEach(mailLog -> {
            // 如果重试次数超过三次，更新投递状态为投递失败，不再重试
            if (3<mailLog.getCount()){
                mailLogService.update(new UpdateWrapper<MailLog>().set("status",2).eq("msgId",mailLog.getMsgId()));
            }
            mailLogService.update(new UpdateWrapper<MailLog>().set("count",mailLog.getCount()+1)
                    .set("updateTime",LocalDateTime.now())
                    .set("tryTime",LocalDateTime.now().plusMinutes(MailConstant.MSG_TIMEOUT))
                    .eq("msgId",mailLog.getMsgId()));
            Employee employee = employeeService.getEmployeeExcel(mailLog.getEid()).get(0);
            // 重新发送消息
            rabbitTemplate.convertAndSend(MailConstant.MAIL_EXCHANGE_NAME,MailConstant.MAIL_ROUTING_KEY_NAME,employee,new CorrelationData(mailLog.getMsgId()));
        });


    }
}
