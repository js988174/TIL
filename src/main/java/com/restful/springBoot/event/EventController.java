package com.restful.springBoot.event;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_VALUE)
public class EventController {

    private final EventRepository eventRepository;

    private final EventValidator eventValidator;

    public EventController(EventRepository eventRepository, EventValidator eventValidator) {
        this.eventRepository = eventRepository;
        this.eventValidator = eventValidator;
    }

    @GetMapping("/api/events")
    public ResponseEntity createEvnet(@RequestBody @Valid Event event, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        eventValidator.validate(event, errors);
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Event newEvent = this.eventRepository.save(event);
        URI createUrl = linkTo(EventController.class).slash(newEvent.getId()).toUri();

        EntityModel eventResource = EntityModel.of(newEvent);
        eventResource.add(linkTo(EventController.class).slash(newEvent.getId()).withSelfRel());
        eventResource.add(linkTo(EventController.class).withRel("query-events"));


        return ResponseEntity.created(createUrl).body(eventResource);
    }
}
