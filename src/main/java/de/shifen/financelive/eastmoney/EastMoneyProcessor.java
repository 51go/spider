package de.shifen.financelive.eastmoney;

import de.shifen.financelive.eastmoney.pojo.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.HtmlNode;
import us.codecraft.webmagic.selector.Selectable;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author ms404
 */
@Service
@Slf4j
public class EastMoneyProcessor implements PageProcessor {
    public static final String SOURCE="easymoney";
    protected Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        log.error("page {} handling", page.getUrl().toString());

        List<Selectable> nodes = page.getHtml().xpath("//[@id='livenews-list']/[@class='livenews-media']").nodes();

        List<Item> items = new ArrayList<>();
        for (Selectable node : nodes) {

            String time = node.xpath("//span[@class='time']/text()").get();
            String full = node.xpath("//h2/a/text()").get();
            String link = node.xpath("//h2/a/@href").get();
            Item item = new Item();
            try {
                String title1 = full.substring(0, full.indexOf("】")).replace("【", "");
                item.setTitle(title1);
                String content1 = full.substring(full.indexOf("】") + 1,
                        full.lastIndexOf("。") + 1);
                item.setContent(content1);
                String from = full.substring(full.lastIndexOf("。") + 1).replace("（", "")
                        .replace("）", "");
                item.setFrom(from);
            }catch (Exception e){
                item.setTitle(full);
                item.setContent(full);
            }
            Date at = parseDate(time);
            item.setTime(at);
            item.setUri(link);
            item.setUniqueId(md5(link+at.getTime()));
            items.add(item);

        }
        page.putField("items", items);
    }


    @Override
    public Site getSite() {
        return site;
    }
    public  String md5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            throw new RuntimeException("MD5 wrong");
        }
    }
    public Date parseDate(String timeStr){
        Calendar calendar = Calendar.getInstance();
        String hour = timeStr.substring(0,timeStr.indexOf(":"));
        String minute = timeStr.substring(timeStr.indexOf(":")+1);
        calendar.set(Calendar.HOUR,Integer.parseInt(hour));
        calendar.set(Calendar.MINUTE,Integer.parseInt(minute));
        return calendar.getTime();
    }


}
