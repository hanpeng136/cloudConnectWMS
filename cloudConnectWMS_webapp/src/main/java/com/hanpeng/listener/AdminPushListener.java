package com.hanpeng.listener;

import com.alibaba.fastjson.JSON;
import com.hanpeng.bean.Admin;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @program: cloudConnectWMS
 * @description: 管理员信息推送监听
 * @author: by hanpeng
 * @create: 2018-12-03 11:25
 **/
@Component("adminPushListener")
public class AdminPushListener implements MessageListener {
    protected static final Logger logger = Logger.getLogger(AdminPushListener.class);
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            //获取数据
            String jsonStr = textMessage.getText();
            logger.info("[AdminPushListener.onMessage]:receive message is,"+ jsonStr);
            if (jsonStr != null) {
                Admin info = JSON.parseObject(jsonStr, Admin.class);
                System.out.println("==============================接受到的用户信息 开始====================================");
                System.out.println(info.toString());
                System.out.println("==============================接受到的用户信息 结束====================================");
                //WebsocketController.broadcast("user", jsonStr);
            }
        } catch (JMSException e) {
            logger.error("[AdminPushListener.onMessage]:receive message occured an exception",e);
        }
    }
}
