package com.example.RestAPICrudAppSeriesEvents.service;

import com.example.RestAPICrudAppSeriesEvents.model.Event;
import com.example.RestAPICrudAppSeriesEvents.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public List<Event> findEventsOfValidatedSerie(long id)
    {
        return eventRepository.findEventsOfValidatedSerie(id);
    }
    public Event saveEvent(Event event)
    {
        return eventRepository.save(event);
    }
    public Event findBySerieAndEvent(long id,int eid)
    {
       return eventRepository.findBySerieAndEvent(id,eid);
    }
    public void deleteEvent(Event event)
    {
         eventRepository.delete(event);
    }
    public Event findById(int id)
    {
        return eventRepository.findById(id).orElse(null);
    }
}
