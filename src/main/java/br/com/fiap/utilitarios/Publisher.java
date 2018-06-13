package br.com.fiap.utilitarios;
import java.io.Serializable;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Publisher 
{
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	public static void createConnectionAndSendMessage( )
    {
        try
        {
        	ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session topicSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = topicSession.createTopic("Test-Topic");

            MessageProducer producer = topicSession.createProducer(topic);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            ObjectMessage message = topicSession.createObjectMessage();

            TopicoTO topicTO = new TopicoTO();
            topicTO.setId( 100 );
            topicTO.setName("Sample");

            message.setStringProperty("s_id", "Sample");
            message.setObject((Serializable) topicTO);                

            producer.send(message);
            System.out.println("message sent successfully");
        }
        catch( JMSException e)
        {
        	System.out.println("error :" + e);
        }
    }
	
	public static void main(String[] args) 
	{
		Publisher.createConnectionAndSendMessage( ) ;
	}
}
