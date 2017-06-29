package java.utils;


import data.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuthorUtils {

    private static String[] names = {"Jan", "Marcin"};
    private static String[] surnames = {"Kowalski", "Jankowski"};
    private static Date[] birthDates = {
            new Date(2000,10,10),
            new Date(1990, 12,12)
    };

    public static Author getAuthor(boolean i){
        if(i) return new Author((long) 1, names[0], surnames[0], birthDates[0]);
        else return new Author((long) 2, names[1], surnames[1], birthDates[1]);
    }

    public static Author getAuthorWithoutId(){
        return new Author(names[0], surnames[0], birthDates[0]);
    }

    public static Page<Author> getAuthors(Pageable pageable){
        List<Author> authorList = new ArrayList<Author>();
        authorList.add(getAuthor(true));
        authorList.add(getAuthor(false));

        return new PageImpl<>(authorList, pageable, authorList.size());
    }

}
