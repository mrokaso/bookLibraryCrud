package web.endpoints;

import data.entities.PublicationPlace;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import business.PublicationPlaceService;

import java.util.List;

@RestController
public class PublicationPlaceController {

    @Autowired
    private PublicationPlaceService publicationPlaceService;

    @ApiOperation("Get all of publication places")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access Denied")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/publicationPlaces")
    List<PublicationPlace> getPublicationPlaces(){
        return publicationPlaceService.getPublicationPlaces();
    }

    @ApiOperation("Get publication place by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 404, message = "Publication place not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/publicationPlace/{id}")
    PublicationPlace getPublicationPlace(@PathVariable long id){
        return publicationPlaceService.getPublicationPlace(id);
    }

    @ApiOperation("Create publication place")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access Denied")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/publicationPlace")
    void addPublicationPlace(@RequestParam String name){
        publicationPlaceService.addPublicationPlace(name);
    }

    @ApiOperation("Edit publication place")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access Denied")
    })
    @RequestMapping(method = RequestMethod.PUT, value = "/publicationPlace")
    void editPublicationPlace(@RequestBody PublicationPlace publicationPlace){
        publicationPlaceService.editPublicationPlace(publicationPlace);
    }

    @ApiOperation("Delete publication place")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access Denied")
    })
    @RequestMapping(method = RequestMethod.DELETE, value = "/publicationPlace/{id}")
    void deletePublicationPlace(@PathVariable long id){
        publicationPlaceService.deletePublicationPlace(id);
    }
}
