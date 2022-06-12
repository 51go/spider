package de.shifen.financelive.wallstreet.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author ms404
 */
@Data
public class ApiDataBody {
    List<NewsItem> items;
    @JsonProperty("polling_cursor")
    String pollingCursor;
    @JsonProperty("op_cursor")
    String op_cursor;
    @JsonProperty("next_cursor")
    String next_cursor;
}
