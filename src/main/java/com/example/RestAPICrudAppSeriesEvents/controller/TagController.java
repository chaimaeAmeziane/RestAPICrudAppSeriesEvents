package com.example.RestAPICrudAppSeriesEvents.controller;

import com.example.RestAPICrudAppSeriesEvents.model.Event;
import com.example.RestAPICrudAppSeriesEvents.model.Serie;
import com.example.RestAPICrudAppSeriesEvents.model.Tag;
import com.example.RestAPICrudAppSeriesEvents.repository.TagRepository;
import com.example.RestAPICrudAppSeriesEvents.service.EventService;
import com.example.RestAPICrudAppSeriesEvents.service.SerieService;
import com.example.RestAPICrudAppSeriesEvents.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8074")
@RestController
@RequestMapping("/api/users/{userId}/series/{serieId}/events")
public class TagController {
    @Autowired
    private TagService tagService;

    @Autowired
    private SerieService serieService;

    @Autowired
    private EventService eventService;

    @GetMapping("/{eventId}/tags")
    public ResponseEntity<List<Tag>> listeOfTagsOfEvent(@PathVariable(value = "userId") Long userId,
                                                  @PathVariable(value="serieId") Long serieId,
                                                  @PathVariable(value="eventId") int eventId)
    {
        Serie serie = serieService.findByUserIdAndSerieId(serieId,userId);
        Event event = eventService.findBySerieAndEvent(serie.getId(),eventId);
        List<Tag> liste = tagService.findTags(event.getId());
        return new ResponseEntity<>(liste, HttpStatus.CREATED);
    }

    @PostMapping("/{eventId}/tags")
    public ResponseEntity<Tag> createEvent(@PathVariable(value = "userId") Long userId,
                                             @PathVariable(value="serieId") Long serieId,
                                             @PathVariable(value="eventId") int eventId,
                                             @RequestBody Tag tagRequest)
    {
        Serie serie = serieService.findByUserIdAndSerieId(serieId,userId);
        Event event = eventService.findBySerieAndEvent(serie.getId(),eventId);
        tagRequest.setEvent(event);
        Tag tag = tagService.save(tagRequest);
        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }
    @PutMapping("/{eventId}/tags/{tagId}")
    public ResponseEntity<Tag> updateEvent(@PathVariable("userId") long userId,
                                             @PathVariable("serieId") long serieId,
                                             @PathVariable("eventId") int eventId,
                                             @PathVariable("tagId") int tagId,
                                             @RequestBody Tag tagRequest)
    {
        Serie serie = serieService.findByUserIdAndSerieId(serieId,userId);
        Event event = eventService.findBySerieAndEvent(serie.getId(),eventId);
        Tag tag = tagService.findTagAndSerie(event.getId(),tagId);
        tag.setName(tagRequest.getName());
        tag.setEvent(eventService.findById(eventId));
        return new ResponseEntity<>(tagService.save(tag), HttpStatus.OK);
    }
    @DeleteMapping("/{eventId}/tags/{tagId}")
    public String deleteTag(@PathVariable(value = "userId") Long userId,
                            @PathVariable(value="serieId") Long serieId,
                            @PathVariable(value="eventId") int eventId,
                            @PathVariable(value="tagId") int tagId)
    {
        Serie serie = serieService.findByUserIdAndSerieId(serieId,userId);
        Event event = eventService.findBySerieAndEvent(serie.getId(),eventId);
        Tag tag = tagService.findTagAndSerie(event.getId(),tagId);
        tagService.deleteTag(tag);
        return "Tag deleted !";
    }

}
