package es.netmind.banana_invoices.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class SimpleProcessor implements ItemProcessor<String,String> {
    final static Logger logger = LoggerFactory.getLogger(SimpleProcessor.class);

    @Override
    public String process(String text) throws Exception {
        logger.info("SimpleReader process()...:"+text.toString());
        return text.toUpperCase();
    }
}
