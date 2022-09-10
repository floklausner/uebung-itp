package at.htl.boundary;

import at.htl.entity.Author;
import at.htl.entity.Book;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class BookEndpointTest {

    @Test
    void getAllBooks() {
        List<Book> bookList = new ArrayList<>();

        bookList = given()
                .when()
                    .get("/book/getAllBooks")
                .then()
                    .log().body()
                    .statusCode(200)
                    .extract().body().jsonPath().getList(".", Book.class);

        System.out.println(bookList);

        assertThat(bookList)
                .isNotEmpty()
                .hasSize(2)
                .extracting(book -> book.getAuthor().getAge())
                .contains(19,19);

    }

    @Test
    void addToBookStore() {
    }

    @Test
    void deleteBookById() {
    }

    @Test
    void findById() {
    }
}
