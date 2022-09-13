package de.shifen.financelive.nlp;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.shifen.financelive.dto.KeyWordDto;
import de.shifen.financelive.dto.NewDto;
import de.shifen.financelive.repo.KeywordRepo;
import de.shifen.financelive.repo.NewDboRepo;
import lombok.extern.slf4j.Slf4j;
import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author ms404
 */
@Slf4j
@Service
public class NlpService {
    @Resource
    NewDboRepo newDboRepo;

    @Resource
    KeywordRepo keywordRepo;

    KeyWordComputer kwc = new KeyWordComputer(5);
    @Scheduled(fixedDelay = 30000L)
    public void nlp(){
        List<NewDto> all = newDboRepo.findAllByNlpHandledAt(0L);
        for(NewDto dto:all){
            Collection<Keyword> keywords = kwc.computeArticleTfidf(dto.getTitle(),dto.getContent());

            for(Keyword keyword:keywords){
                KeyWordDto keyWordDto = new KeyWordDto();
                keyWordDto.setDtoId(dto.getId());
                keyWordDto.setFreq(keyword.getFreq());
                keyWordDto.setTerm(keyword.getName());
                keyWordDto.setScore(keyword.getScore());
                try{
                    keywordRepo.save(keyWordDto);
                }catch (Exception e){
                    log.error("保存keyword时发生了错误",e);
                }
            }
            dto.setNlpHandledAt(System.currentTimeMillis());
            newDboRepo.save(dto);
        }
    }
}
