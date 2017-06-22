package business;


public interface RentService {
    void rentBook(long userId, long bookId);
    void rentBook(long userId, long bookId, boolean taken);
    void returnBook(long userId, long bookId);
}
