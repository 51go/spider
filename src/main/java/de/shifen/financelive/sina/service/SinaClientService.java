package de.shifen.financelive.sina.service;

import de.shifen.financelive.dto.NewDto;
import de.shifen.financelive.nlp.TextService;
import de.shifen.financelive.repo.NewDboRepo;
import de.shifen.financelive.sina.pojo.Body;
import de.shifen.financelive.sina.pojo.FeedItem;
import de.shifen.financelive.sina.pojo.SinaApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author ms404
 */
@Service
@Slf4j
public class SinaClientService {
    private static final String SOURCE="sina";
    @Resource
    SinaApiClient sinaApiClient;
    @Resource
    NewDboRepo newDtoRepo;
    @Resource
    TextService textService;
    @Scheduled(fixedDelay = 600000L)
    public void handle(){
        List<String> urls = sinaApiClient.urls();
        for(String url:urls){
            try {
                Body body = sinaApiClient.fetch(url);
                for(FeedItem item:body.getResult().getData().getFeed().getList()){
                    try {
                        persistence(item);
                    }catch (Exception e){
                        log.error("持久化sina finance 快讯时出错了",e);
                    }
                }
            }catch (Exception e2){
                log.error("抓取新浪快讯时出错了",e2);
            }
        }

    }

    public void persistence(FeedItem newsItem){
        Optional<NewDto> optionalNewDto = newDtoRepo.findFirstBySourceAndExternalUniqueId(SOURCE,
                String.valueOf(newsItem.getId()) );
        if(!optionalNewDto.isPresent()){
            NewDto newDto = new NewDto();
            TextService.Model model = textService.guess(newsItem.getRichText());
            newDto.setContent(model.getContent());
            newDto.setContentText(newsItem.getRichText());
            newDto.setExternalUniqueId(String.valueOf(newsItem.getId()));
            newDto.setSource(SOURCE);
            newDto.setTitle(model.getTitle());
            newDto.setDisplayTime(newsItem.getCreateAt().getTime());
            newDtoRepo.save(newDto);
        }
    }

}
