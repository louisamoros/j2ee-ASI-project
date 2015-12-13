package ejb;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

import common.UserModel;

@Stateless
@LocalBean
public class MessageSender implements MessageSenderLocal {

	@Inject
	JMSContext context;
	@Resource(lookup = "java:/jms/queue/watcher-queue")
	Queue queue;

	public MessageSender() {
		super();
	}

	public void sendMessage(String message) {
		try {
			context.createProducer().send(queue, message); 
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}

	public void sendMessage(UserModel user) {
		try {
			ObjectMessage message = context.createObjectMessage();
			message.setObject(user);
			context.createProducer().send(queue, message);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
