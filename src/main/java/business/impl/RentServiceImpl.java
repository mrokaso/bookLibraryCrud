package business.impl;


import business.exceptions.ResourceExistingException;
import business.exceptions.ResourceNotExistingException;
import business.security.JwtService;
import data.entities.Book;
import data.entities.Rent;
import data.entities.User;
import business.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import data.repositories.BookRepository;
import data.repositories.RentRepository;
import data.repositories.UserRepository;
import business.RentService;
import utils.http.HttpMethod;
import utils.http.HttpRequest;
import utils.http.HttpRequestBuilder;
import utils.http.HttpResponse;

import java.util.Date;
import java.util.List;

@Service
public class RentServiceImpl implements RentService{

    private final RentRepository rentRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Value("${modules.clientAPI}")
    private String clientAPI;

    @Autowired
    public RentServiceImpl(RentRepository rentRepository, BookRepository bookRepository, UserRepository userRepository, JwtService jwtService) {
        this.rentRepository = rentRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public void rentBook(long userId, long bookId) {
        Rent rent = operationOfRentBook(userId, bookId);
        rentRepository.save(rent);
    }

    @Override
    public void rentBook(long userId, long bookId, boolean taken) {
        Rent rent = operationOfRentBook(userId, bookId);
        rent.setTaken(taken);
        rentRepository.save(rent);
    }


    @Override
    public void returnBook(long userId, long bookId) {
        List<Rent> rents = rentRepository.findByBookAndUserAndDateReturnIsNull(bookRepository.findOne(bookId), userRepository.findOne(userId));
        if(rents != null && rents.size() > 0)
        {
            rents.get(0).setDateReturn(new Date());
            rentRepository.save(rents.get(0));

            HttpRequest request = new HttpRequestBuilder()
                    .withMethod(HttpMethod.POST)
                    .withUri(clientAPI + "/book/" + bookId + "/notify/")
                    .withHeader("Authorization", jwtService.createModuleToken())
                    .build();

            request.send();
        } else {
            throw new ResourceNotExistingException();
        }

    }

    private Rent operationOfRentBook(long userId, long bookId){
        User user = userRepository.findOne(userId);
        if(user == null) throw new ResourceNotFoundException();
        Book book = bookRepository.findOne(bookId);
        if(book == null) throw new ResourceNotFoundException();

        List<Rent> rents = rentRepository.findByBookAndUserAndDateReturnIsNull(book, user);
            if(book.getCount() <= rentRepository.countByBookAndDateReturnIsNull(book))
                throw new ResourceNotExistingException();

            Rent rent = new Rent();
            rent.setBook(book);
            rent.setDateRent(new Date());
            rent.setUser(user);

            return rent;

    }
}
