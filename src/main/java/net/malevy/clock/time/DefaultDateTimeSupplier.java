package net.malevy.clock.time;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DefaultDateTimeSupplier implements DateTimeSupplier {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
