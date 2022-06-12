package de.shifen.financelive.wallstreet.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author ms404
 */
@Data
public class Author {
    String avatar;
    @JsonProperty("display_name")
    String displayName;
    String uri;
    long id;
}
