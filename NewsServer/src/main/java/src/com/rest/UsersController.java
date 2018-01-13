package src.com.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import src.com.domain.User;
import src.com.rest.requests.LoginRequest;
import src.com.rest.responses.ErrorResponse;
import src.com.rest.responses.LoginResponse;
import src.com.rest.responses.Response;
import src.com.rest.responses.SuccessResponse;
import src.com.services.NewsService;
import src.com.services.UsersService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:9955")
public class UsersController {
    private final UsersService usersService;
    private final NewsService newsService;

    @Autowired
    public UsersController(UsersService usersService, NewsService newsService) {
        this.usersService = usersService;
        this.newsService = newsService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(
            @RequestBody LoginRequest request
    ) {
        try {
            usersService.doLogin(request.getEmail(), request.getPassword());
            return new LoginResponse("1");
        } catch (Exception e) {
            return new ErrorResponse(-1, e.getMessage());
        }
    }
}
