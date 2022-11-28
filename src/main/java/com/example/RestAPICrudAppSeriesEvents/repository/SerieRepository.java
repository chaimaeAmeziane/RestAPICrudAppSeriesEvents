package com.example.RestAPICrudAppSeriesEvents.repository;

import com.example.RestAPICrudAppSeriesEvents.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SerieRepository extends JpaRepository<Serie,Long> {
    @Query( value = "SELECT * from series where id = :serie_id AND user_id = :uid",
            nativeQuery = true)
    public Serie findByUserIdAndSerieId(@Param("serie_id") Long id,@Param("uid") Long uid);

    List<Serie> findByUserId(Long postId);
}
