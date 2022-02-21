package platform.data;

import org.springframework.data.repository.CrudRepository;
import platform.entities.Code;

import java.util.List;

public interface CodeRepository extends CrudRepository<Code, String> {
    List<Code> findAll();
}
