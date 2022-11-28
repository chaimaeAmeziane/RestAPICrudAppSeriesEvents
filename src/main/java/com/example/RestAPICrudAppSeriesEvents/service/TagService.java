package com.example.RestAPICrudAppSeriesEvents.service;

import com.example.RestAPICrudAppSeriesEvents.model.Tag;
import com.example.RestAPICrudAppSeriesEvents.repository.EventRepository;
import com.example.RestAPICrudAppSeriesEvents.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public List<Tag> findTags(int eventid)
    {
        return tagRepository.findTags(eventid);
    }
    public Tag save(Tag tag)
    {
        return tagRepository.save(tag);
    }
    public Tag findTagAndSerie(int id,int iid)
    {
        return tagRepository.findTagAndSerie(id,iid);
    }
    public void deleteTag(Tag tag)
    {
        tagRepository.delete(tag);
    }
}
