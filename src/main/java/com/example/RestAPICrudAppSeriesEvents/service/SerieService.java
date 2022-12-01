package com.example.RestAPICrudAppSeriesEvents.service;

import com.example.RestAPICrudAppSeriesEvents.model.Serie;
import com.example.RestAPICrudAppSeriesEvents.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {
    @Autowired
    private SerieRepository serieRepository;

    public List<Serie> findUserById(Long id)
    {
        return serieRepository.findByUserId(id);
    }
    public Serie findByUserIdAndSerieId(Long id,Long uid)
    {
        return serieRepository.findByUserIdAndSerieId(id,uid);
    }
    public void deleteSerieById(Long id)
    {
        serieRepository.deleteById(id);
    }
    public Serie saveSerie(Serie serie)
    {
        return serieRepository.save(serie);
    }
    public Serie findSerieById(Long id){
        return serieRepository.findById(id).orElse(null);
    }
}
