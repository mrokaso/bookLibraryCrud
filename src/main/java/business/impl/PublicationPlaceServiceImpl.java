package business.impl;


import business.exceptions.ResourceNotExistingException;
import business.mapers.PublicationPlaceMapper;
import data.entities.PublicationPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import data.repositories.PublicationPlaceRepository;
import business.PublicationPlaceService;

import java.util.List;

@Service
public class PublicationPlaceServiceImpl implements PublicationPlaceService {

    private PublicationPlaceRepository publicationPlaceRepository;
    private PublicationPlaceMapper publicationPlaceMapper;

    @Autowired
    public PublicationPlaceServiceImpl(
            PublicationPlaceRepository publicationPlaceRepository,
            PublicationPlaceMapper publicationPlaceMapper){
        this.publicationPlaceRepository = publicationPlaceRepository;
        this.publicationPlaceMapper = publicationPlaceMapper;
    }

    @Override
    public void addPublicationPlace(String name) {
        PublicationPlace publicationPlace = publicationPlaceMapper.toPublicationPlace(name);
        publicationPlaceRepository.save(publicationPlace);
    }

    @Override
    public void editPublicationPlace(PublicationPlace publicationPlace) {
        publicationPlaceRepository.save(publicationPlace);
    }

    @Override
    public void deletePublicationPlace(long id) {
        publicationPlaceRepository.delete(id);
    }

    @Override
    public List<PublicationPlace> getPublicationPlaces() {
        return publicationPlaceRepository.findAll();
    }

    @Override
    public PublicationPlace getPublicationPlace(long id) {
        PublicationPlace publicationPlace = publicationPlaceRepository.findOne(id);
        if(publicationPlace == null) throw new ResourceNotExistingException();
        return publicationPlace;
    }
}
