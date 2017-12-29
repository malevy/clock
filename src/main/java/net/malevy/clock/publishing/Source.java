package net.malevy.clock.publishing;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Source {
    String SOURCE_CHANNEL = "temporalEvents";

    @Output(Source.SOURCE_CHANNEL)
    MessageChannel temporalEvents();
}
