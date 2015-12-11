package ejb;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
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

	public UserModel receiveMessage() {
		UserModel user = (UserModel) context.createConsumer(queue);
		return user;
	}
}