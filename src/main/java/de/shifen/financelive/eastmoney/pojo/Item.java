package de.shifen.financelive.eastmoney.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author ms404
 */
@Data
public class Item {
    String content;
    String uri;
    Date time;
    String title;
    String from;
    String uniqueId;

}
