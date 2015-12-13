package ejb;

import java.util.Date;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import model.DataContainer;
import common.Role;
import common.UserModel;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/watcher-queue") })
public class AuthWatcherMessageDrivenEJB implements MessageListener {

	private DataContainer dataContainer;

	@EJB
	MessageReceiverSyncLocal receiver;

	public AuthWatcherMessageDrivenEJB() {
		dataContainer = new DataContainer();
	}

	public void onMessage(Message message) {
		
		System.out.println("========================================================");
		try {
			if (message instanceof TextMessage) {
				System.out.println("Queue: I received a TextMessage at "
						+ new Date());
				System.out.println("Message is : "
						+ ((TextMessage) message).getText());
			} else if (message instanceof ObjectMessage) {
				System.out.println("Queue: ObjectMessage received at "
						+ new Date());
				ObjectMessage msg = (ObjectMessage) message;

				if (msg.getObject() instanceof UserModel) {
					UserModel user = (UserModel) msg.getObject();

					System.out.println("User Details: ");
					System.out.println("login:" + user.getLogin());
					System.out.println("pwd:" + user.getPwd());

					Role currentTestRole = dataContainer.checkUser(user);
					if (Role.NONE == currentTestRole) {
					} else {
						user.setRole(currentTestRole);
					}
				}
			} else {
				System.out.println("Not valid message for this Queue MDB");
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
