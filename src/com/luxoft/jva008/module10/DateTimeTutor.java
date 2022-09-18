package com.luxoft.jva008.module10;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.TimeZone;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

public class DateTimeTutor {

    public static void main(String[] args) {

        // 1) print current time in London in format "HH:mm dd/MM/yyyy"
        // use ZoneId.of("Europe/London") and then ZonedDateTime to retrieve London time
        // use format() method and DateTimeFormatter.ofPattern()

        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Europe/London"));
        System.out.println(zonedDateTime.format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")));

        // 2) calculate your age in days, months and years
        LocalDate myB = LocalDate.of(1987, Month.MARCH, 11);
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(myB, currentDate);
        System.out.println("Years: " + period.getYears() + " Months: " + period.getMonths() + " Days: " + period.getDays());

        // 3) calculate and show the date of your next birthday in format dd.mm.yyyy

        LocalDate nextB = myB.plusYears(0);
        Period periodForT3 = Period.between(myB, nextB);
        while (periodForT3.getYears() != 36) {
            int i = 1;
            nextB = nextB.plusYears(i);
            periodForT3 = Period.between(myB, nextB);
            i++;

        }
        System.out.println("My next B " + nextB.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));

        // 4) calculate your age in seconds and in hours
        LocalDateTime myB2 = LocalDateTime.of(1987, 3, 11, 16, 22, 13);
        LocalDateTime currentDateTime = LocalDateTime.now();

        Duration duration = Duration.between(currentDateTime, myB2);
        System.out.format("Your age in hours: %s, Your age in seconds: %s", duration.toHours() * -1, duration.toSeconds() * -1);

        // 5) show day of the week of the next birthday
        System.out.format("\nDay of the week of the next birthday: %s", nextB.getDayOfWeek());

        // 6) check if your birthday this year is ahead or behind
        LocalDateTime currentYB = myB2.plusYears(period.getYears());
        Duration durationT6 = Duration.between(currentDateTime, currentYB);
        System.out.format("\nyour birthday this year is %s", durationT6.toDays() > 0 ? "ahead" : "behind\n");

        // 7) calculate time difference of local time and New York time
        // use TimeZone.getRawOffset() to calculate difference in time in milliseconds
        // use TimeZone.getTimeZone("America/New_York") to get New York time zone
        // use TimeZone().getDefault() to get local TimeZone
        // use TimeUnit.MILLISECONDS.toHours() to convert it to hours
        LocalDateTime nYTime = LocalDateTime.now(ZoneId.of("America/New_York"));
        System.out.println("NY time: " + nYTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
        long diff = TimeUnit.MILLISECONDS.toHours(TimeZone.getTimeZone("America/New_York").getRawOffset());
        System.out.format("time difference of local time and New York time is %sh ",diff);

        // 8) let us have New York time "02.04.2016 22:11"; calculate local time and print it in the same format
        // use ZonedDateTime.withZoneSameInstant() method and ZoneOffset.systemDefault() to get local ZoneId
        String time = "02.04.2016 22:11";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime nYTimeT8 = LocalDateTime.parse(time,formatter);

        ZonedDateTime nyT8 = nYTimeT8.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime localT8 = nyT8.withZoneSameInstant(ZoneOffset.systemDefault());

        ZoneId id = nyT8.getZone();

        System.out.println("\nCalculate local time: " + localT8.format(formatter));
        System.out.println("Calculate NY time: " + nyT8.format(formatter));

    }

}
