package com.jd.service.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @program: cloudConnectWMS
 * @description: 服务提供者
 * @author: by hanpeng
 * @create: 2018-12-02 19:48
 **/
public class JMSProducer {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER; // 默认连接
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD; // 默认密码
    private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL; // 默认连接地址 为 failover://tcp://localhost:61616
    private static final int SENDNUM = 1000; // 发送的消息数量

    public static void main(String[] args) {
        ConnectionFactory connectionFactory; // 连接工程，生产Connection
        Connection connection = null; // 连接
        Session session; // 会话 接受或者发送消息的线程
        Destination destination; // 消息的目的地
        MessageProducer messageProducer; // 消息生产者
        // 实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
        // 创建连接
        try {
            connection = connectionFactory.createConnection();
            connection.start(); // 启动连接
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE); // 提交事务，自动确认
            destination = session.createTopic("FirstTopic"); // 创建主题
            messageProducer = session.createProducer(destination); // 创建消息发送者
            for (int i = 0; i< JMSProducer.SENDNUM; i++) {
                TextMessage message = session.createTextMessage("ActiveMQ 发送的消息 "+i);
                System.out.println("发送消息: ActiveMQ 发送的消息 "+i);
                messageProducer.send(message);
            }
            session.commit(); // 提交事务
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (connection!=null)
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
        }
    }
}
