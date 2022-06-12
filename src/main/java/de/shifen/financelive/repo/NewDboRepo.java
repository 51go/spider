package de.shifen.financelive.repo;

import de.shifen.financelive.dto.NewDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author ms404
 */
@Component
public interface NewDboRepo extends CrudRepository<NewDto,Long> {
    public Optional<NewDto> findFirstBySourceAndExternalUniqueId(String source,String uniqueId);
}
