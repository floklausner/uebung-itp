package at.htl.control;

import at.htl.entity.Book;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class BookRepo implements PanacheRepository<Book> {

    @Inject
    EntityManager entityManager;

    @Transactional
    public Book save(Book book) {
        return entityManager.merge(book);
    }

}
