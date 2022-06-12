package de.shifen.financelive.wallstreet.pojo;

import lombok.Data;

/**
 * @author ms404
 */
@Data
public class WallstreetApiResponse {
    public static final  int SUCCEED = 20000;
    int code;
    String message;
    ApiDataBody data;
}
