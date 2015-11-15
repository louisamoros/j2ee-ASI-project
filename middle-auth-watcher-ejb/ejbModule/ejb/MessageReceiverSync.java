package ejb;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;

import common.UserModel;

/**
 * Session Bean implementation class MessageReceiverSync
 */
@Stateless
@LocalBean
public class MessageReceiverSync implements MessageReceiverSyncLocal {

	@Inject
	JMSContext context;

	@Resource(mappedName = "java:/jms/queue/watcherqueue")
	Queue queue;
	
	/**
	 * Default constructor.
	 */
	public MessageReceiverSync() {
		// TODO Auto-generated constructor stub
	}
	
	public UserModel receiveMessage() {
		Message message = null;
		try {
			MessageConsumer consumer  = (MessageConsumer) context.createConsumer(queue);
			message = consumer.receive(1000); // Time out after a second
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return (UserModel) message;
	}

}
