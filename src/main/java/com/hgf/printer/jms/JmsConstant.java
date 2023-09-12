package com.hgf.printer.jms;

public class JmsConstant {

    /**
     * 用的activemq做为JMS的Provider服务提供者，activemq的默认端口为 61616
     */
    public static final String url = "tcp://localhost:61616";
    public static final String queueName = "queue-test";
}
