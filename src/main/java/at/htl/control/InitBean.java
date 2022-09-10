package at.htl.control;

import at.htl.entity.Author;
import at.htl.entity.Book;
import at.htl.entity.Bookstore;
import at.htl.entity.Buyer;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class InitBean {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void onStart(@Observes StartupEvent event) {
        Author author1 = new Author("Florian Klausner", 19);
        Author author2 = new Author("Rosalie Mandel", 19);
        Author author3 = new Author("Robert Klausner", 54);
        Author author4 = new Author("Jonas Dorfinger", 19);
        entityManager.persist(author1);
        entityManager.persist(author2);
        entityManager.persist(author3);
        entityManager.persist(author4);

        Bookstore bookstore1 = new Bookstore("Fridolin's Store", "Linz", "Fridolin Pumpernickel");
        Bookstore bookstore2 = new Bookstore("Rosalie's Buchgeschäft", "Zaubertal", "Rosalie Mandel");
        entityManager.persist(bookstore1);
        entityManager.persist(bookstore2);

        Buyer buyer1 = new Buyer("Sandra Bullock", 10);
        Buyer buyer2 = new Buyer("Ferdinand Purzelbaum", 89);
        entityManager.persist(buyer1);
        entityManager.persist(buyer2);

        Book book1 = new Book("Das Ende der Welt", 14.99, bookstore1, author1);
        Book book2 = new Book("Der Anfang der Welt", 16.99, bookstore2, author2);
        entityManager.persist(book1);
        entityManager.persist(book2);

    }

}
