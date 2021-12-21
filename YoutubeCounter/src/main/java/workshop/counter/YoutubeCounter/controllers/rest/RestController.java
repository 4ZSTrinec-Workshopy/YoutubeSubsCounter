package workshop.counter.YoutubeCounter.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import workshop.counter.YoutubeCounter.services.ApiService;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/api/subs")
    public String getSubs(String id)
    {
        return String.valueOf(apiService.getSubsById(id));
    }

}
