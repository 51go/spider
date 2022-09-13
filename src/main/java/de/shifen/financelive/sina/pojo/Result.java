package de.shifen.financelive.sina.pojo;

import lombok.Data;

/**
 * @author ms404
 */
@Data
public class Result {
    de.shifen.financelive.sina.pojo.Data data;
    String timestamp;
    Status status;
}
