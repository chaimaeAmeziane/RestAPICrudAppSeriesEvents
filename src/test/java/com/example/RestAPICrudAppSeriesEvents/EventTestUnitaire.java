package com.example.RestAPICrudAppSeriesEvents;

import com.example.RestAPICrudAppSeriesEvents.model.Event;
import com.example.RestAPICrudAppSeriesEvents.model.Serie;
import com.example.RestAPICrudAppSeriesEvents.model.User;
import com.example.RestAPICrudAppSeriesEvents.service.EventService;
import com.example.RestAPICrudAppSeriesEvents.service.SerieService;
import com.example.RestAPICrudAppSeriesEvents.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class EventTestUnitaire {
    @Autowired
    public UserService userservice;

    @Autowired
    public SerieService serieService;

    @Autowired
    public EventService eventService;

    @Test
    public void testCreateEvent()
    {
        Serie serie = serieService.findByUserIdAndSerieId(1L,2L);
        Event event = new Event("English",17,"Good mark !",serie);
        assertThat(eventService.saveEvent(event));

    }
    @Test
    public void testListEvents()
    {
        Serie serie = serieService.findByUserIdAndSerieId(1L,1L);
        List<Event> listeEvent = eventService.findEventsOfValidatedSerie(serie.getId());
        assertThat(listeEvent);
        for(int i=0 ; i<listeEvent.size();i++)
        {
            System.out.println(listeEvent.get(i).getDate()+" "+listeEvent.get(i).getValue());
        }
    }
    @Test
    public void testUpdateEvent()
    {
        Serie serie = serieService.findByUserIdAndSerieId(1L,1L);
        Event event = eventService.findBySerieAndEvent(serie.getId(),1);
        event.setDate("date updated !");
        event.setValue(2);
        event.setSerie(serie);
        assertThat(eventService.saveEvent(event));
    }
    @Test
    public void testDeleteEvent()
    {
        int id = 1 ;
        Event event = eventService.findById(id);
        eventService.deleteEvent(event);
        Optional<Event> optionalEvent = Optional.ofNullable(eventService.findById(id));
        assertThat(optionalEvent).isNotPresent();
    }
}
