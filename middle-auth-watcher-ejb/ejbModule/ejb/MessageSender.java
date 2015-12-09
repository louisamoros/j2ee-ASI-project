package ejb;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

import common.UserModel;

/**
 * Session Bean implementation class MessageSender
 */
@Stateless
@LocalBean
public class MessageSender implements MessageSenderLocal {

	@Inject
	JMSContext context;
	@Resource(mappedName = "java:/jms/queue/watcher-queue")
	Queue queue;

	/**
	 * Default constructor.
	 */
	public MessageSender() {
		// TODO Auto-generated constructor stub
	}

	public void sendMessage(String message) {
		// TODO
	}

	public void sendMessage(UserModel user) {
		try {
			ObjectMessage message = context.createObjectMessage();
			message.setObject((Serializable) user);
			context.createProducer().send(queue, (Message) user);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
