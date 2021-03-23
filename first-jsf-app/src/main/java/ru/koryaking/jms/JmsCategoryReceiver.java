package ru.koryaking.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.koryaking.service.CategoryRepr;
import ru.koryaking.service.CategoryService;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/categoryQueue")
        }
)
public class JmsCategoryReceiver implements MessageListener {

    private final Logger logger = LoggerFactory.getLogger(JmsCategoryReceiver.class);

    @EJB
    private CategoryService categoryService;

    @Override
    public void onMessage(Message message) {
        logger.info("New JMS message");

        if (message instanceof ObjectMessage) {
            ObjectMessage om = (ObjectMessage) message;
            try {
                categoryService.saveOrUpdate((CategoryRepr) om.getObject());
            } catch (JMSException e) {
                logger.error("", e);
            }
        }
    }
}