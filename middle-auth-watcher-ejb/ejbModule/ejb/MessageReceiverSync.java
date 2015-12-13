package ejb;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Queue;

import common.UserModel;

@Stateless
@LocalBean
public class MessageReceiverSync implements MessageReceiverSyncLocal {

	@Inject
	JMSContext context;
	@Resource(lookup = "java:/jms/queue/watcher-queue")
	Queue queue;

	public MessageReceiverSync() {
		super();
	}

	public UserModel receiveMessageUser() {
		JMSConsumer consumer = context.createConsumer(queue);
		Message user = consumer.receive();
		System.out.println(user);
		System.out.println("Receive message user.");

		return (UserModel) user;
	}

	public String receiveMessage() {
		JMSConsumer message = context.createConsumer(queue);
		System.out.println("99999999999999999999999999999");
		System.out.println("Receive message string.");
		System.out.println(message.getMessageSelector());
//		message.getMessageSelector();0
		System.out.println("coucou");
		String messages = "coucou";
		return messages;
	}
}