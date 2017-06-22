package web.endpoints;


import data.entities.Publisher;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import business.PublisherService;

import java.util.List;

@RestController
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @ApiOperation("Get all of publishers")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access Denied")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/publishers")
    public List<Publisher> getPublishers(){
        return publisherService.getPublishers();
    }

    @ApiOperation("Get publisher by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 404, message = "Publisher not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/publisher/{id}")
    public Publisher getPublisher(@PathVariable long id) { return publisherService.getPublisher(id); }

    @ApiOperation("Create publisher")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access Denied")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/publisher")
    public void addPublisher(@RequestParam String name){
        publisherService.addPublisher(name);
    }

    @ApiOperation("Edit publisher")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access Denied")
    })
    @RequestMapping(method = RequestMethod.PUT, value = "/publisher")
    public void editPublisher(@RequestBody Publisher publisher){
        publisherService.editPublisher(publisher);
    }

    @ApiOperation("Delete publisher by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access Denied")
    })
    @RequestMapping(method = RequestMethod.DELETE, value = "/publisher/{id}")
    public void deletePublisher(long idPublisher){
        publisherService.deletePublisher(idPublisher);
    }
}
