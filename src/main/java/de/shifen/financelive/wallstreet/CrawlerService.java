package de.shifen.financelive.wallstreet;

import de.shifen.financelive.dto.NewDto;
import de.shifen.financelive.repo.NewDboRepo;
import de.shifen.financelive.wallstreet.api.ApiClient;
import de.shifen.financelive.wallstreet.pojo.NewsItem;
import de.shifen.financelive.wallstreet.pojo.WallstreetApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author ms404
 */
@Service
@Slf4j
public class CrawlerService {
    @Resource
    ApiClient apiClient;

    @Resource
    NewDboRepo newDtoRepo;

    @Scheduled(fixedDelay = 600000L)
    public void cron(){
        try {
            WallstreetApiResponse response = apiClient.fetch();
            if(response.getCode()!=WallstreetApiResponse.SUCCEED){
                throw new Exception("返回code 不是20000");
            }
            for(NewsItem item : response.getData().getItems()){
                persistence(item);
            }
        }catch (Exception e){
            log.error("定时取数失败",e);
        }
    }

    public void persistence(NewsItem newsItem){
        Optional<NewDto> optionalNewDto = newDtoRepo.findFirstBySourceAndExternalUniqueId(ApiClient.SOURCE,
                String.valueOf(newsItem.getId()) );
        if(!optionalNewDto.isPresent()){
            NewDto newDto = new NewDto();
            newDto.setContent(newsItem.getContent());
            newDto.setContentText(newsItem.getContentText());
            newDto.setExternalUniqueId(String.valueOf(newsItem.getId()));
            newDto.setSource(ApiClient.SOURCE);
            newDto.setTitle(newsItem.getTitle());
            newDto.setDisplayTime(newsItem.getDisplayTime());
            newDto.setAuthorAvatar((newsItem.getAuthor().getAvatar()));
            newDto.setAuthorName(newsItem.getAuthor().getDisplayName());
            newDto.setAuthorId(String.valueOf(newsItem.getAuthor().getId()));
            newDtoRepo.save(newDto);
        }
    }
}
