package business.mapers;


import business.mapers.models.UserRequest;
import business.mapers.models.UserRequestAdd;
import data.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "SPRING")
public interface UserMapper {
    User toUser(UserRequestAdd userRequestAdd);
    @Mappings({
            @Mapping( target = "name", source = "userRequest.name"),
            @Mapping( target = "surname", source = "userRequest.surname"),
            @Mapping( target = "birthDate", source = "userRequest.birthDate"),
            @Mapping( target = "imageURL", source = "userRequest.imageURL"),
            @Mapping( target = "email", source = "userRequest.email")
    })
    User toUser(UserRequest userRequest, User user);
}
