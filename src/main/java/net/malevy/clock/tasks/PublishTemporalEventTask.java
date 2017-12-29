package net.malevy.clock.tasks;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.malevy.clock.publishing.Publisher;
import net.malevy.clock.time.DateTimeSupplier;
import net.malevy.clock.time.TemporalEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.integration.leader.event.OnGrantedEvent;
import org.springframework.integration.leader.event.OnRevokedEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PublishTemporalEventTask {

    private static final Logger logger = LoggerFactory.getLogger(PublishTemporalEventTask.class);
    private final Publisher publisher;
    private final DateTimeSupplier dateTimeSupplier;
    private boolean canPublish = false;

    @Autowired
    public PublishTemporalEventTask(Publisher publisher, DateTimeSupplier dateTimeSupplier) {

        this.publisher = publisher;
        this.dateTimeSupplier = dateTimeSupplier;
        this.disablePublishing();
        logger.info("PublishTemporalEventTask has been created");
    }

    @Scheduled(cron = "${schedule}")
    public void publish() throws JsonProcessingException {

        if (!canPublish) {
            logger.debug("temporal event publishing disabled for this node");
            return;
        }

        final TemporalEvent eventToPublish = new TemporalEvent(this.dateTimeSupplier.now());
        this.publisher.publish(eventToPublish);
        logger.debug("temporal event published");
    }

    public void enablePublishing() {
        logger.info("publishing has been enabled");
        this.canPublish = true;
    }
    public void disablePublishing() {
        logger.warn("publishing has been disabled");
        this.canPublish = false;
    }

    @EventListener
    public void onLeadershipGranted(OnGrantedEvent grantedEvent) {
        this.enablePublishing();
    }

    @EventListener
    public void onLeadershipRevoked(OnRevokedEvent revokedEvent) {
        this.disablePublishing();
    }

}
