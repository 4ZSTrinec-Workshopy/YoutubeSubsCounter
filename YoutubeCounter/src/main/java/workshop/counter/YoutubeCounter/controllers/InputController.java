package workshop.counter.YoutubeCounter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import workshop.counter.YoutubeCounter.services.ApiService;

@Controller
public class InputController {
    @Autowired
    private ApiService apiService;

    @PostMapping("/")
    public String submit(String channel, Model model)
    {

        model.addAttribute("channels", apiService.fetchChannels(channel));

        return "index";
    }

}

