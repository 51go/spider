package de.shifen.financelive.eastmoney;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.shifen.financelive.dto.NewDto;
import de.shifen.financelive.eastmoney.pojo.Item;
import de.shifen.financelive.repo.NewDboRepo;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author ms404
 */
@Service
public class PersistentPipeline implements Pipeline {
    @Resource
    NewDboRepo newDboRepo;
    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void process(ResultItems resultItems, Task task) {
       List<Item> items =  resultItems.get("items");
       for(Item item:items){
            persistence(item);
       }
    }
    public void persistence(Item item){
        Optional<NewDto> optionalNewDto = newDboRepo.findFirstBySourceAndExternalUniqueId(EastMoneyProcessor.SOURCE,
                String.valueOf(item.getUniqueId()) );
        if(!optionalNewDto.isPresent()){
            NewDto newDto = new NewDto();
            newDto.setContent(item.getContent());
            newDto.setContentText(item.getContent());
            newDto.setExternalUniqueId(item.getUniqueId());
            newDto.setSource(EastMoneyProcessor.SOURCE);
            newDto.setTitle(item.getTitle());
            newDto.setDisplayTime(item.getTime().getTime()/1000);
            newDto.setFromWhere(item.getFrom());
            newDboRepo.save(newDto);
        }
    }


}
