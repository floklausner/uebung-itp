package at.htl.boundary;

import at.htl.entity.Author;
import at.htl.entity.Book;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;

import io.restassured.http.ContentType;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class BookEndpointTest {

    @Inject
    AgroalDataSource dataSource;

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
                .extracting(book -> book.getAuthor().getName())
                .contains("Florian Klausner","Rosalie Mandel");

    }

    @Test
    void addToBookStore() {

        //Arrange
        Book book = new Book("Mamamia", 16.99, null, null);

        Table table = new Table(dataSource, "BOOK");
        org.assertj.db.output.Outputs.output(table).toConsole();

        int rowCount = table.getRowsList().size();

        //Act
        given()
                .contentType(ContentType.JSON)
                .and()
                .body(book)
                .when()
                .post("/book/addBook")
                .then()
                .statusCode(200);

        //Assert
        table = new Table(dataSource, "BOOK");
        int newRowCount = table.getRowsList().size();
        org.assertj.db.output.Outputs.output(table).toConsole();

        assertThat(rowCount + 1).isEqualTo(newRowCount);

//        org.assertj.db.api.Assertions.assertThat(table)
//                .column("BOOKTITLE")
//                .value().isEqualTo("Das Ende der Welt")
//                .value().isEqualTo("Der Anfang der Welt")
//                .value().isEqualTo("Mamamia");

        org.assertj.db.api.Assertions.assertThat(table)
                .row(1)
                .value().isEqualTo(1)
                .value().isEqualTo(1)
                .value().isEqualTo("Das Ende der Welt")
                .value().isEqualTo(14.99)
                .value().isEqualTo(1)
                .value().isEqualTo(1)
                .value().is(null);


    }

    @Test
    void deleteBookById() {
    }

    @Test
    void findById() {
    }
}
