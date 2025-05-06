package dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RegisterResponse {
    @JsonProperty("userID")
    private String userId;
    private String username;
    private List<Book> books;

    @Data
    public static class Book {
        private String isbn;
        private String title;
        private String subTitle;
        private String author;
        private String publish_date;
        private String publisher;
        private Integer pages;
        private String description;
        private String website;
    }
}
