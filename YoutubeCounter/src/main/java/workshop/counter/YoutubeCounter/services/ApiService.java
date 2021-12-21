package workshop.counter.YoutubeCounter.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import workshop.counter.YoutubeCounter.models.Channel;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiService {
    private final String KEY = ""; //Vlastní api klíč


    public List<Channel> fetchChannels(String name)
    {
        String url = "https://www.googleapis.com/youtube/v3/search?part=snippet&type=channel&maxResults=3&q=" + name + "&key=" + KEY ;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = null;


        List<Channel> channels = new ArrayList<>();

        try
        {

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            //System.out.println(response.body());
            JSONObject json = new JSONObject(response.body());
            JSONArray jsonArray = json.getJSONArray("items");

            for(int i = 0; i < jsonArray.length(); i++)
            {
                String id = jsonArray.getJSONObject(i).getJSONObject("id").getString("channelId");
                String channelName = jsonArray.getJSONObject(i).getJSONObject("snippet").getString("title");
                String description = jsonArray.getJSONObject(i).getJSONObject("snippet").getString("description");

                String image = jsonArray.getJSONObject(i)
                        .getJSONObject("snippet")
                        .getJSONObject("thumbnails")
                        .getJSONObject("default").getString("url");

                Channel channel = new Channel(image, id, channelName, description);
                channels.add(channel);
            }


        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return channels;
    }

    public Integer getSubsById(String id)
    {
        String url = "https://www.googleapis.com/youtube/v3/channels?part=statistics&id=" + id + "&key="+ KEY;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = null;

        int count = 0;

        try{

            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject jsonObject = new JSONObject(response.body());
            JSONArray jsonArray = jsonObject.getJSONArray("items");

            count = jsonArray.getJSONObject(0).getJSONObject("statistics").getInt("subscriberCount");


        }catch (Exception e){

            count = 0;

        }
        return count;
    }
}
