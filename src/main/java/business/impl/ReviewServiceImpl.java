package business.impl;


import data.entities.Book;
import data.entities.Review;
import business.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import data.repositories.BookRepository;
import data.repositories.ReviewRepository;
import business.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Page<Review> getReviews(long bookId, Pageable pageable) {
        Book book = bookRepository.getOne(bookId);
        if(book == null) throw new ResourceNotFoundException();
        return reviewRepository.findAllByBook(book, pageable);
    }

    @Override
    public void createReview(long bookId, String content, int rate) {
        Book book = bookRepository.getOne(bookId);
        if(book == null) throw new ResourceNotFoundException();
        Review review = new Review(rate, content, book);
        reviewRepository.save(review);
    }

    @Override
    public Review getReview(long reviewId) {
        Review review = reviewRepository.findOne(reviewId);
        if(review == null) throw new ResourceNotFoundException();
        return reviewRepository.findOne(reviewId);
    }

    @Override
    public void deleteReview(long reviewId) {
        reviewRepository.delete(reviewId);
    }
}
