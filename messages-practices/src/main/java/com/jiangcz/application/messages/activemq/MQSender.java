package com.jiangcz.application.messages.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 类名称：MQSender<br>
 * 类描述：<br>
 * 创建时间：2018年08月23日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class MQSender {

    public static void main(String[] args) throws JMSException {
        String url = args[0];
        for (int i = 0; i < 500; i++) {
            testQueue(url,"metting","Today having metting");
        }
    }

    public static void testQueue(String url,String application,String message) throws JMSException {

        //创建一个mq连接对象
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //创建session
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(application);

        MessageProducer producer = session.createProducer(queue);
        TextMessage textMessage = session.createTextMessage(message);
        producer.send(textMessage);

        producer.close();
        session.close();
        connection.close();
    }
}
