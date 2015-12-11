package ejb;

import javax.ejb.Local;
import javax.jms.Message;

import common.UserModel;

@Local
public interface MessageReceiverSyncLocal {
	public UserModel receiveMessageUser();
	public Message receiveMessage();
}
