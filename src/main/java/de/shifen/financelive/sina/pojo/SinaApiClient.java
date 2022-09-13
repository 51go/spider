package de.shifen.financelive.sina.pojo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author ms404
 */
@Component
public class SinaApiClient {
    public static final String SOURCE="sina";
    ObjectMapper mapper = new ObjectMapper();

    public SinaApiClient() {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    List<String> tags = Arrays.asList(
            "0","10","1","2","3","4","5","6","7","8"
    );
    public static final String API = "https://zhibo.sina.com.cn/api/zhibo/feed?callback=&page=1&page_size=20&zhibo_id=152&tag_id=__TAG_ID&dire=f&dpc=1&pagesize=100&_=";
    public Body fetch(String url) throws IOException {
        //创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        String bodyStr = Objects.requireNonNull(response.body()).string();
        Body body =  mapper.readValue(bodyStr,Body.class);
        for(FeedItem item:body.result.data.feed.list){
            item.parse();
        }
        return body;
    }
    public List<String> urls(){
        List<String> urls = new ArrayList<>();
        for(String id:tags){
            urls.add(API.replace("__TAG_ID",id));
        }
        return urls;
    }
}
