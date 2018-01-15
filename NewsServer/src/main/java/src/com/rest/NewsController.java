package src.com.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import src.com.domain.NewsObject;
import src.com.rest.responses.ErrorResponse;
import src.com.rest.responses.Response;
import src.com.rest.responses.SuccessResponse;
import src.com.services.NewsDTO;
import src.com.services.NewsService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class NewsController {

    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService){
        this.newsService = newsService;
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public List<NewsObject> getAllNews()
    {
        try{
            return newsService.getAllNews();
        }
        catch (Exception e)
        {
            return new ArrayList<>();
        }
    }

    @RequestMapping(value = "/news/{id}", method =  RequestMethod.GET)
    public Response getNewsById(@PathVariable("id") Integer id)
    {
        try{
            return new SuccessResponse(newsService.getById(id));
        }
        catch (Exception e)
        {
            return new ErrorResponse(-2, e.getMessage());
        }
    }

    @RequestMapping(value = "/news", method = RequestMethod.POST)
    public NewsObject addNews(@RequestBody NewsDTO newsDTO)
    {
        try{
            return this.newsService.add(newsDTO);
        }
        catch (Exception e) {
            return new NewsObject("", "", new Date());
        }
    }
}
