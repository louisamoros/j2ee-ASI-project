package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class MessageSender
 */
@Stateless
@LocalBean
public class MessageSender implements MessageSenderLocal {

    /**
     * Default constructor. 
     */
    public MessageSender() {
        // TODO Auto-generated constructor stub
    }

}
