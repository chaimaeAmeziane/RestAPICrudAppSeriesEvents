package com.example.RestAPICrudAppSeriesEvents;

import com.example.RestAPICrudAppSeriesEvents.model.Serie;
import com.example.RestAPICrudAppSeriesEvents.model.User;
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
public class SerieTestUnitaire {
    @Autowired
    public UserService userservice;

    @Autowired
    public SerieService serieService;

    @Test
    public void testCreateSerie()
    {
        User user = userservice.findUserById(1L);
        Serie serie = new Serie("Serie1","Description 1",user);
        assertThat(serieService.saveSerie(serie));
    }
    @Test
    public void testListSeries()
    {
        User user = userservice.findUserById(1L);
        List<Serie> listeSerie = serieService.findUserById(user.getId());
        assertThat(listeSerie);
        for(int i=0 ; i<listeSerie.size();i++)
        {
            System.out.println(listeSerie.get(i).getTitle());
            System.out.println(listeSerie.get(i).getDescription());
        }
    }
    @Test
    public void testUpdateSerie()
    {
        Long id = 1L;
        Serie serie1 = serieService.findSerieById(id);
        serie1.setTitle("New Title Serie1");
        serie1.setDescription("New Description Serie1");
        assertThat(serieService.saveSerie(serie1));
    }
    @Test
    public void testDeleteSerie()
    {
        Long id = 1L;
        serieService.deleteSerieById(id);
        Optional<Serie> optionalSerie = Optional.ofNullable(serieService.findSerieById(id));
        assertThat(optionalSerie).isNotPresent();
    }
}
