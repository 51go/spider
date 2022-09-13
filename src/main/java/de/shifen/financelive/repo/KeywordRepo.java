package de.shifen.financelive.repo;

import de.shifen.financelive.dto.KeyWordDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * @author ms404
 */
@Component
public interface KeywordRepo extends CrudRepository<KeyWordDto,Long> {
}
