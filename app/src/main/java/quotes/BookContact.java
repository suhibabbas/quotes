package quotes;

import java.util.Arrays;

public class BookContact {

    String author;
    String text ;

    public BookContact(String author, String text) {

        this.author = author;
        this.text = text;
    }

    @Override
    public String toString() {
        return "BookContact{" +
                "author='" + author + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

