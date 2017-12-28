package net.malevy.clock.tasks;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.malevy.clock.publishing.Publisher;
import net.malevy.clock.time.DateTimeSupplier;
import net.malevy.clock.time.TemporalEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PublishTemporalEventTask {

    private static final Logger logger = LoggerFactory.getLogger(PublishTemporalEventTask.class);
    private final Publisher publisher;
    private final DateTimeSupplier dateTimeSupplier;

    @Autowired
    public PublishTemporalEventTask(Publisher publisher, DateTimeSupplier dateTimeSupplier) {

        this.publisher = publisher;
        this.dateTimeSupplier = dateTimeSupplier;
        logger.info("PublishTemporalEventTask has been created");
    }

    @Scheduled(cron = "${schedule}")
    public void publish() throws JsonProcessingException {

        final TemporalEvent eventToPublish = new TemporalEvent(this.dateTimeSupplier.now());
        this.publisher.publish(eventToPublish);
        logger.debug("temporal event published");
    }

}
