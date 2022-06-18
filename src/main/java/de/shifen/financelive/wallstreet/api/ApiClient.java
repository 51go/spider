package de.shifen.financelive.wallstreet.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.shifen.financelive.wallstreet.pojo.WallstreetApiResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

/**
 * @author ms404
 */
@Service
public class ApiClient {
    public static final String SOURCE="wallstreet";
    ObjectMapper mapper = new ObjectMapper();

    public ApiClient() {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static final String API = "https://api-one.wallstcn.com/apiv1/content/lives?channel=global-channel&client=pc&limit=100&first_page=true&accept=live%2Cvip-live";
    public WallstreetApiResponse fetch() throws IOException {
        //创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API)
                .build();
        Response response = client.newCall(request).execute();
        String body = Objects.requireNonNull(response.body()).string();
        return mapper.readValue(body,WallstreetApiResponse.class);
    }
}
