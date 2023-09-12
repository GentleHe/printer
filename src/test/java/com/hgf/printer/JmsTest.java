package com.hgf.printer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.jupiter.api.Test;

import javax.jms.*;

public class JmsTest {

    /**
     * 用的activemq做为JMS的Provider服务提供者，activemq的默认端口为 61616
     */
    private static final String url = "tcp://localhost:61616";
    private static final String queueName = "queue-test";


    public static void main(String[] args) throws JMSException {
        // 1.创建ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        // 2.创建连接
        Connection connection = connectionFactory.createConnection();

        // 3.启动连接
        connection.start();

        // 4.创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 5.创建目标
        Destination destination = session.createQueue(queueName);

        // 6.创建一个消费者
        MessageConsumer consumer = session.createConsumer(destination);

        // 7.创建一个监听器
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    String text = textMessage.getText();
                    System.out.println("接收消息" + text);
                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // 8.关闭连接，别关太早
//        connection.close();
    }

}
