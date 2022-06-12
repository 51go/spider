package de.shifen.financelive.eastmoney;

import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;

/**
 * @author ms404
 */
@Service
public class EastMoneyStarter implements CommandLineRunner {

    @Resource
    EastMoneyProcessor eastMoneyProcessor ;

    @Resource
    PersistentPipeline printPipeline;

    Spider spider;


    class Worker extends Thread {
        @Override
        public void run() {
           fire();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        Worker worker = new Worker();
        worker.start();
    }
    public void fire(){
        spider =
        Spider.create(eastMoneyProcessor);
        spider.addUrl("https://kuaixun.eastmoney.com/").addPipeline(printPipeline).thread(2).run();
    }

    /**
     * 为啥要加上时间戳？
     * 因为相同的url，再加上去，就不会再次访问了。要么重定义Scheduler,要么。。。在网址上搞个小hack.
     */
    @Scheduled(fixedDelay = 600000L)
    public void cron(){
        spider.addUrl("https://kuaixun.eastmoney.com/?"+System.currentTimeMillis());
    }
}
