package net.malevy.clock.publishing;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(Source.class)
public class TemporalEventsConfiguration {
}
