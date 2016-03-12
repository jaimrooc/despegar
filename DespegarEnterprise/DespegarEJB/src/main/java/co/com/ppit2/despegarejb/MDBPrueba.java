/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ppit2.despegarejb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

/**
 *
 * @author root
 */
@MessageDriven(mappedName = "jms/TestQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "connectionFactoryLookup", propertyValue = "jms/TestConnectionFactory")
})
public class MDBPrueba implements MessageListener {
    
    @Resource
    private MessageDrivenContext mdc;
    
    public MDBPrueba() {
    }
    
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                TextMessage msg = (TextMessage) message;
                System.out.println(msg.getText());
            } catch (JMSException ex) {
                mdc.setRollbackOnly(); // Mensaje no puede ser leido, se regresa a la cola
                Logger.getLogger(MDBPrueba.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (message instanceof ObjectMessage) {
            ObjectMessage msg = (ObjectMessage) message;
            try {
                System.out.println(msg.getObject());
            } catch (JMSException ex) {
                mdc.setRollbackOnly(); // Mensaje no puede ser leido, se regresa a la cola
                Logger.getLogger(MDBPrueba.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("No soportado");
        }
    }
    
}
