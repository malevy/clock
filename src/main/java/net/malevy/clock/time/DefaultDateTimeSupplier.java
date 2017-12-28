package net.malevy.clock.time;

import java.time.LocalDateTime;

public class DefaultDateTimeSupplier implements DateTimeSupplier {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
