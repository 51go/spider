package de.shifen.financelive.sina.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ms404
 */
@Data
public class FeedItem {
    long id;
    @JsonProperty("rich_text")
    String richText;
    @JsonProperty("create_time")
    String createTime;
    @JsonProperty("docurl")
    String doUrl;
    Date createAt;
    public void parse(){
        try{
            Date date = fromString(this.createTime);
            this.setCreateAt(date);
        }catch (Exception e){

        }
    }
    /**
     *
     * @param str string like 2022-06-19 21:47:59
     * @return
     */
    public Date fromString(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        return sdf.parse(str);
    }
}
