package net.malevy.clock.time;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class TemporalEvent {

    @JsonIgnore
    private final LocalDateTime moment;

    @JsonIgnore
    private static DateTimeFormatter dateFormat = DateTimeFormatter.ISO_LOCAL_DATE;

    public TemporalEvent(LocalDateTime moment) {
        this.moment = moment;
    }

    @JsonProperty("date")
    public String getDate() { return moment.format(dateFormat); }

    @JsonProperty("year")
    public int getYear() { return moment.getYear(); }

    @JsonProperty("month")
    public int getMonth() { return moment.getMonthValue(); }

    @JsonProperty("monthLabel")
    public String getMonthLabel() {return moment.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH).toLowerCase();}

    @JsonProperty("day")
    public int getDay() { return moment.getDayOfMonth();}

    @JsonProperty("hour")
    public int getHour() { return moment.getHour(); }

    @JsonProperty("minute")
    public int getMinute() { return moment.getMinute(); }

    @JsonProperty("dayOfWeek")
    public String getDayOfWeekLabel() { return moment.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).toLowerCase();}

    @JsonProperty("dayOfYear")
    public int getDayOfYear() { return moment.getDayOfYear(); }

}
