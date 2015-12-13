package ejb;

import javax.ejb.Local;

import common.UserModel;

@Local
public interface MessageReceiverSyncLocal {
	public UserModel receiveMessageUser();
	public String receiveMessage();
}
