package com.example.RestAPICrudAppSeriesEvents.repository;

import com.example.RestAPICrudAppSeriesEvents.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Integer> {
    @Query( value = "SELECT * from tags where event_id = :eventId",
            nativeQuery = true)
    public List<Tag> findTags(@Param("eventId") int eventid);

    @Query( value = "SELECT * from tags where id = :tag_id AND event_id = :event_id",
            nativeQuery = true)
    public Tag findTagAndSerie(@Param("event_id") int id, @Param("tag_id") int iid);
}
