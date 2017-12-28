package net.malevy.clock.time;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface DateTimeSupplier {

    LocalDateTime now();

}

