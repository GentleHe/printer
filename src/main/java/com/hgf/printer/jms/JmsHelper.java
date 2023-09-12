package com.hgf.printer.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

import java.util.function.Consumer;

import static com.hgf.printer.jms.JmsConstant.url;

public class JmsHelper {

    public static ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
    public static Connection defaultConnection;
    public static Session defaultSession = null;

    static {
        try {
            // 默认的连接
            defaultConnection = connectionFactory.createConnection();
            // 默认的session
            defaultSession = openSession();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    public static Session openSession() throws JMSException {
        // 2.创建连接
        Connection connection = connectionFactory.createConnection();

        // 3.启动连接
        connection.start();

        // 4.创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        return session;
    }

    public static MessageConsumer getConsumer(Session session, String queueName) throws JMSException {
        // 5.创建目标
        Destination destination = session.createQueue(queueName);

        // 6.创建一个消费者
        MessageConsumer consumer = session.createConsumer(destination);
        return consumer;
    }

//    public static String getSessionStatus(Session session){
//    }

    public void listenQueue(MessageConsumer consumer, MessageListener messageListener) throws JMSException {
        consumer.setMessageListener(messageListener);
    }

    public static void closeSession(Session session) throws JMSException {
        session.close();
    }
}
