package data.repositories;


import data.entities.PublicationPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationPlaceRepository extends JpaRepository<PublicationPlace, Long> {
    PublicationPlace findByName(String name);
}
