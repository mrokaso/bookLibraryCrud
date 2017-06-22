package business;


import data.entities.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PublisherService {
    void addPublisher(String name);
    void editPublisher(Publisher publisher);
    void deletePublisher(long idPublisher);
    List<Publisher> getPublishers();
    Publisher getPublisher(long id);
}
