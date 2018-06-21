package br.com.fiap.utilitarios;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;


public class TopicMessageListener implements MessageListener 
{

	private String selector;

	public void onMessage( Message message ) 
	{
		if ( ( message instanceof TextMessage ) ) 
		{
			handleMessage((TextMessage) message);
		}
		
		if ( ( message instanceof ObjectMessage ) ) 
		{
			handleObjectMessage((ObjectMessage) message);
		}
	}

	private void handleMessage(TextMessage message) 
	{
		try 
		{
			System.out.println("Consumer[" + selector + "] consumed message: " + message.getText());
		}
		catch (JMSException jmsException) 
		{
			System.err.println( "Error reading message" + jmsException);
		}
	}
	
	private void handleObjectMessage(ObjectMessage message) 
	{
		try 
		{
			System.out.println("Consumer[" + selector + "] consumed message: " + ( ( TopicoTO ) message.getObject() ).getName() );
		}
		catch (JMSException jmsException) 
		{
			System.err.println( "Error reading message" + jmsException);
		}
	}

	public void setSelector(String selector) 
	{
		this.selector = selector;
	}
}
