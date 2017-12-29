package net.malevy.clock.time;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

public interface DateTimeSupplier {

    LocalDateTime now();

}

