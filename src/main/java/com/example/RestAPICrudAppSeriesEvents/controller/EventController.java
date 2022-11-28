package com.example.RestAPICrudAppSeriesEvents.controller;

import com.example.RestAPICrudAppSeriesEvents.model.Event;
import com.example.RestAPICrudAppSeriesEvents.model.Serie;
import com.example.RestAPICrudAppSeriesEvents.model.User;
import com.example.RestAPICrudAppSeriesEvents.repository.EventRepository;
import com.example.RestAPICrudAppSeriesEvents.repository.UserRepository;
import com.example.RestAPICrudAppSeriesEvents.service.EventService;
import com.example.RestAPICrudAppSeriesEvents.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8074")
@RestController
@RequestMapping("/api/users/{userId}")
public class EventController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SerieService serieService;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/series/{serieId}")
    public ResponseEntity<Serie> getUserById(@PathVariable("userId") long userId,@PathVariable("serieId") long serieId) {
        Serie serie = serieService.findByUserIdAndSerieId(serieId,userId);
        return new ResponseEntity<>(serie, HttpStatus.OK);
    }

    @GetMapping("/series/{serieId}/events")
    public ResponseEntity<List<Event>> getEventsOnlyOfSerieAndUser(@PathVariable(value = "userId") Long userId,
                                                             @PathVariable(value="serieId") Long serieId)
    {
        Serie serie = serieService.findByUserIdAndSerieId(serieId,userId);
        List<Event> liste = eventService.findEventsOfValidatedSerie(serie.getId());
        return new ResponseEntity<>(liste, HttpStatus.OK);
    }
    @GetMapping("/series/{serieId}/events/{eventId}")
    public ResponseEntity<Event> getEventDetails(@PathVariable(value = "userId") Long userId,
                                                 @PathVariable(value="serieId") Long serieId,
                                                 @PathVariable(value="eventId") int eventId)
    {
        Serie serie = serieService.findByUserIdAndSerieId(serieId,userId);
        Event event = eventService.findBySerieAndEvent(serieId,eventId);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PostMapping("/series/{serieId}/events")
    public ResponseEntity<Event> createEvent(@PathVariable(value = "userId") Long userId,@PathVariable(value="serieId") Long serieId,
                                             @RequestBody Event eventRequest)
    {
       Serie serie = serieService.findSerieById(serieId);
       eventRequest.setSerie(serie);
       Event event = eventService.saveEvent(eventRequest);
       return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @PutMapping("/series/{serieId}/events/{eventId}")
    public ResponseEntity<Event> updateEvent(@PathVariable("userId") long userId,
                                             @PathVariable("serieId") long serieId,
                                             @PathVariable("eventId") int eventId,
                                             @RequestBody Event eventRequest)
    {
        Serie serie = serieService.findByUserIdAndSerieId(serieId,userId);
        Event event = eventService.findBySerieAndEvent(serieId,eventId);
        event.setDate(eventRequest.getDate());
        event.setValue(eventRequest.getValue());
        event.setCommentaire(eventRequest.getCommentaire());
        return new ResponseEntity<>(eventService.saveEvent(event), HttpStatus.OK);
    }
    @DeleteMapping("/series/{serieId}/events/{eventId}")
    public String deleteSerieOfUser(@PathVariable("userId") long userId,
                                    @PathVariable("serieId") long serieId,
                                    @PathVariable("eventId") int eventId) {
        Serie serie  = serieService.findByUserIdAndSerieId(serieId,userId);
        Event event = eventService.findBySerieAndEvent(serieId,eventId);
        eventService.deleteEvent(event);
        return "Event "+eventId+ " is successfully deleted ! ";
    }
}
