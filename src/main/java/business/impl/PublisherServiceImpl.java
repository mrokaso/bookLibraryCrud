package business.impl;


import business.exceptions.ResourceNotExistingException;
import data.entities.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import data.repositories.PublisherRepository;
import business.PublisherService;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void addPublisher(String name) {
        Publisher publisher = new Publisher(name);
        publisherRepository.save(publisher);
    }

    @Override
    public void editPublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    @Override
    public void deletePublisher(long idPublisher) {
        publisherRepository.delete(idPublisher);
    }

    @Override
    public List<Publisher> getPublishers() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher getPublisher(long id) {
        Publisher publisher = publisherRepository.findOne(id);
        if(publisher == null) throw new ResourceNotExistingException();
        return publisher;
    }
}
