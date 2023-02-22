package com.restful.springBoot.event;

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
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_VALUE)
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/api/events")
    public ResponseEntity createEvnet(@RequestBody @Valid Event event, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        Event newEvent = this.eventRepository.save(event);
        URI createUrl = linkTo(EventController.class).slash(newEvent.getId()).toUri();
        return ResponseEntity.created(createUrl).build();
    }
}
