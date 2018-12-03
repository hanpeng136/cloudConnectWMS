package com.hanpeng.serviceImpl;

import com.hanpeng.bean.Admin;
import com.hanpeng.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @program: cloudConnectWMS
 * @description: 用户信息推送
 * @author: by hanpeng
 * @create: 2018-12-02 20:56
 **/

@Service
public class AdminPushServiceImpl implements PushService {

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 这里是根据MQ配置文件定义的queue来注入的，也就是这里将会把不同的内容推送到不同的queue中
     */
    @Autowired
    @Qualifier("adminServiceQueue")
    private Destination destination;

    @Override
    public void push(final Object info) {
        pushExecutor.execute(new Runnable() {
            @Override
            public void run() {
                jmsTemplate.send(destination, new MessageCreator() {
                    public Message createMessage(Session session) throws JMSException {
                        Admin p = (Admin) info;
                        return session.createTextMessage(JSON.toJSONString(p));
                    }
                });
            }
        });
    }
}
