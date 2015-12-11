package ejb;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Queue;

import common.UserModel;


@Stateless
@LocalBean
public class MessageReceiverSync implements MessageReceiverSyncLocal {

	@Inject
	JMSContext context;
	@Resource(mappedName = "java:/jms/queue/watcher-queue")
	Queue queue;
	
    public MessageReceiverSync() {}

	public UserModel receiveMessageUser() {
		UserModel user = (UserModel) context.createConsumer(queue);
		return user;
	}
	
	public Message receiveMessage() {
		Message message = (Message) context.createConsumer(queue);
		return message;
	}
}