package src.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.com.domain.Label;
import src.com.domain.NewsObject;
import src.com.persistence.NewsRepository;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NewsService {
    private NewsRepository repository;

    @Autowired
    public NewsService(NewsRepository repository) {
        this.repository = repository;
    }

    public List<NewsObject> getAllNews()
    {
        return repository.findAll();
    }

    public NewsObject getById(Integer id)
    {
        return repository.findOne(id);
    }
}