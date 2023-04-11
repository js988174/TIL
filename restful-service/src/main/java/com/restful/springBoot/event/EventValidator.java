package com.restful.springBoot.event;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

@Component
public class EventValidator {

    public void validate(Event event, Errors errors) {
        if (event.getBasePrice() > event.getMaxPrice() && event.getMaxPrice() != 0) {
            errors.rejectValue("basePrice", "wrongValue", "basePrice is Wrong");
        }

        LocalDateTime localDateTime = event.getEndEventDateTime();
        if (localDateTime.isBefore(event.getBeginEventDateTime()) ||
        localDateTime.isBefore(event.getCloseEnrollmentDateTime()) ||
        localDateTime.isBefore(event.getBeginEnrollmentDateTime())) {
            errors.rejectValue("eventDateTime", "wrongValue", "is Wrong");
        }
    }
}
