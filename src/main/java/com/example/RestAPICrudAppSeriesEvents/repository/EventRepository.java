package com.example.RestAPICrudAppSeriesEvents.repository;

import com.example.RestAPICrudAppSeriesEvents.model.Event;
import com.example.RestAPICrudAppSeriesEvents.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Integer> {
    @Query( value = "SELECT * from events where serie_id = :serie_id",
            nativeQuery = true)
    public List<Event> findEventsOfValidatedSerie(@Param("serie_id") Long id);

    @Query( value = "SELECT * from events where id = :event_id AND serie_id = :serie_id",
            nativeQuery = true)
    public Event findBySerieAndEvent(@Param("serie_id") Long id,@Param("event_id") int event_id);
}
