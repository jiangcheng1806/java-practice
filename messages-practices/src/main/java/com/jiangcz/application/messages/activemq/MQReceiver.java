package com.jiangcz.application.messages.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 类名称：MQReceiver<br>
 * 类描述：<br>
 * 创建时间：2018年08月23日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class MQReceiver {

    public static void main(String[] args) throws Exception {
        String url = args[0];
        testQueue(url,"metting");
    }

    public static void testQueue(String url,String application) throws JMSException {

        ConnectionFactory factory = new ActiveMQConnectionFactory(url);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(application);

        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(message -> {
            TextMessage textMessage = (TextMessage) message;
            String text = "";
            try {
                text = textMessage.getText();
            } catch (JMSException e) {
                e.printStackTrace();
            }
            System.out.println("消费端1对" + text + "进行消费");
        });

        System.out.println("消费端1启动");
    }
}
