package src.com.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import src.com.rest.responses.ErrorResponse;
import src.com.rest.responses.Response;
import src.com.rest.responses.SuccessResponse;
import src.com.services.NewsService;

@RestController
public class NewsController {

    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService){
        this.newsService = newsService;
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public Response getAllNews()
    {
        try{
            return new SuccessResponse(newsService.getAllNews());
        }
        catch (Exception e)
        {
            return new ErrorResponse(-1, e.getMessage());
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
}
