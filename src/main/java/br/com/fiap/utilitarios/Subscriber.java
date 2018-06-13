package br.com.fiap.utilitarios;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Subscriber 
{
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	public static void createConnectionAndReceiveMessage(String clientId)
    {
		ConnectionFactory connectionFactory = null  ;
		Connection connection = null ;
        try
        {
        	connectionFactory = new ActiveMQConnectionFactory(url);
            connection = connectionFactory.createConnection();

            connection.setClientID(clientId);
            connection.start();            
            
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("Test-Topic");

//            String selector = "s_id = Sample";
            String selector = "" ;
            System.out.println("selector : '"+selector+"'....");
            TopicSubscriber consumer = session.createDurableSubscriber(topic, "Sub1", "s_id = 'Sample'", true);

            consumer.setMessageListener(new TopicMessageListener());            

	    }
	    catch(Exception e)
	    {
	        System.out.println("error :" + e);
	    }
    }
	
	public static void main(String[] args) throws Exception 
	{
		Subscriber.createConnectionAndReceiveMessage( "100" ) ;
	}
}
