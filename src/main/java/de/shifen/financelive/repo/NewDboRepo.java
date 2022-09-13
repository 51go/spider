package de.shifen.financelive.repo;

import de.shifen.financelive.dto.NewDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author ms404
 */
@Component
public interface NewDboRepo extends CrudRepository<NewDto,Long> {
    /**
     * 根据来源和原始id看有没有相同的存在
     * @param source String
     * @param uniqueId String
     * @return
     */
    public Optional<NewDto> findFirstBySourceAndExternalUniqueId(String source,String uniqueId);

    /**
     * 找出没有经过nlp处理的;
     * @param nlpHandledAt
     * @return
     */
    public List<NewDto> findAllByNlpHandledAt(Long nlpHandledAt);
}
