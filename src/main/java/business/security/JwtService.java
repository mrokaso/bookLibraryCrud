package business.security;



public interface JwtService {

    String createModuleToken();

    String createUserToken(long id);

    <T> T parse(String token, Class<T> clazz);

}
