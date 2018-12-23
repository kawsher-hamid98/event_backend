package EventManagement.repository;

import EventManagement.model.SpecialEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpecialEventRepository extends MongoRepository<SpecialEvent, String> {
    SpecialEvent findSpecialEventById(String id);
}
