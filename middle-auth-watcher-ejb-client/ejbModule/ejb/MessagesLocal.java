package ejb;

import javax.ejb.Local;
import javax.jms.JMSException;

@Local
public interface MessagesLocal {
	public void sendMessage(String text) throws JMSException;
	public String receiveMessage() throws JMSException;
}
