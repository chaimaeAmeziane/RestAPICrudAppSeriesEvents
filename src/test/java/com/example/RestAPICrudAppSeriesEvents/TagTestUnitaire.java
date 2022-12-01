package com.example.RestAPICrudAppSeriesEvents;

import com.example.RestAPICrudAppSeriesEvents.model.Event;
import com.example.RestAPICrudAppSeriesEvents.model.Serie;
import com.example.RestAPICrudAppSeriesEvents.model.Tag;
import com.example.RestAPICrudAppSeriesEvents.model.User;
import com.example.RestAPICrudAppSeriesEvents.service.EventService;
import com.example.RestAPICrudAppSeriesEvents.service.SerieService;
import com.example.RestAPICrudAppSeriesEvents.service.TagService;
import com.example.RestAPICrudAppSeriesEvents.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class TagTestUnitaire {
    @Autowired
    public UserService userservice;

    @Autowired
    public SerieService serieService;

    @Autowired
    public EventService eventService;

    @Autowired
    public TagService tagservice;

    @Test
    public void testCreateTag()
    {
        User user = userservice.findUserById(1L);
        Serie serie = serieService.findByUserIdAndSerieId(1L,user.getId());
        Event event = eventService.findBySerieAndEvent(serie.getId(),1);
        List<Tag> listeTags = new ArrayList<>();
        Tag tag1 = new Tag(1,"Tag1");
        Tag tag2 = new Tag(2,"Tag2");
        assertThat(tagservice.save(tag1));
        assertThat(tagservice.save(tag2));
        listeTags.add(new Tag(1,"Tag1"));
        listeTags.add(new Tag(2,"Tag2"));
        event.setTags(listeTags);
        assertThat(eventService.saveEvent(event));
    }

    @Test
    public void updateTag()
    {
        int idTag = 1 ;
        Serie serie = serieService.findByUserIdAndSerieId(1L,1L);
        Tag tag = tagservice.findTagAndSerie(idTag, Math.toIntExact(serie.getId()));
        tag.setName("Tag updated !");
        assertThat(tagservice.save(tag));
    }

}
