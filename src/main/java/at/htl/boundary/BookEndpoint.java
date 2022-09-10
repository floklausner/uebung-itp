package at.htl.boundary;

import at.htl.control.BookRepo;
import at.htl.entity.Book;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/book")
public class BookEndpoint {

    //addToBookstore
    //deleteFromBookstore
    //buyBook
    //changePriceOfBook

    @Inject
    BookRepo bookRepo;

    @GET
    @Path("/getAllBooks")
    public Response getAllBooks() {
        return Response.ok(bookRepo.findAll().list()).build();
    }

    @Path("/addBook")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addToBookStore(Book book) {
        bookRepo.save(book);
        return Response.ok(book).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteBookById(@PathParam("id") long id) {
        Book book = bookRepo.findById(id);
        bookRepo.delete(book);
        return Response.ok(book).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") long id) {
        return Response.ok(bookRepo.findById(id)).build();
    }

}
