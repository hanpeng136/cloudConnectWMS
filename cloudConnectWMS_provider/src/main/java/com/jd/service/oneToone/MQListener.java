package com.jd.service.oneToone;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @program: cloudConnectWMS
 * @description: 消息监听器
 * @author: by hanpeng
 * @create: 2018-12-02 18:50
 **/
public class MQListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("收到消息：" + ((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
