package windpark.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;
import windpark.jms.JmsConsumer;
import windpark.jms.JmsProducer;
import windpark.model.WindengineData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@RestController
@Slf4j
public class ProduceMessageController {

    @Autowired
    JmsProducer jmsProducer;

    @PostMapping(value="/api/windengine")
    public WindengineData sendMessage(@RequestBody WindengineData windengine) {
        jmsProducer.sendMessage(windengine);
        return windengine;
    }
    @RequestMapping("/data")
    public String showWindengines(){
        String s = "{";
        for(int i = 0; i < JmsConsumer.cache.size(); i++){
             try{
                String json = JmsConsumer.cache.get(i).toJSON();
                s += json + ", \n";
            }catch(JsonProcessingException e){
                e.printStackTrace();
            }
        }
        return s+"}";
    }
}