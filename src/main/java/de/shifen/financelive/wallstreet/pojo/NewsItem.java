package de.shifen.financelive.wallstreet.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author ms404
 */
@Data
public class NewsItem {
    String title;
    String content;
    @JsonProperty("content_text")
    String contentText;
    long id;
    String uri;
    Author author;
    @JsonProperty("display_time")
    long displayTime;
}
