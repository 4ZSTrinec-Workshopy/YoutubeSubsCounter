package workshop.counter.YoutubeCounter.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import workshop.counter.YoutubeCounter.models.Channel;
import workshop.counter.YoutubeCounter.services.ApiService;

@Controller
public class ChannelController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/channel")
    public String channel(String id, String img, String name, String desc, Model model)
    {
        Channel channel = new Channel(img, id, name, desc);

        model.addAttribute("channel", channel);
        int subs = apiService.getSubsById(id);
        model.addAttribute("subs", subs);

        return "channel";
    }

}
