package ejb;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 * Session Bean implementation class MessageSender
 */
@Stateless
@LocalBean
public class MessageSender implements MessageSenderLocal {

	@Inject
	JMSContext context;

	@Resource(mappedName = "java:/jms/queue/watcherqueue")
	Queue queue;

	/**
	 * Default constructor.
	 */
	public MessageSender() {
		// TODO Auto-generated constructor stub
	}

}
