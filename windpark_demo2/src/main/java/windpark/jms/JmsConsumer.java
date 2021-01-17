package windpark.jms;

import windpark.model.WindengineData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.ArrayList;
import java.util.jar.JarException;

@Component
@Slf4j
public class JmsConsumer implements MessageListener {

    public static ArrayList<WindengineData> cache=new ArrayList();

    @Override
    @JmsListener(destination = "${active-mq.topic}")
    public void onMessage(Message message) {
        try{
            WindengineData windengine = (WindengineData)((ObjectMessage)message).getObject();
            cache.add(windengine);
        log.info("Received Message from Topic: "+ windengine.toString());
        } catch(Exception e) {
            log.error("Received Exception while processing message: "+ e);
        }
    }

}
