package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthoRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap  implements ApplicationListener<ContextRefreshedEvent> {

    private AuthoRepository authoRepository;
    private BookRepository  bookRepository;
    private PublisherRepository  publisherRepository;

    public DevBootstrap(AuthoRepository authoRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authoRepository = authoRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        Publisher pub = new Publisher();
        pub.setName("foo");
        publisherRepository.save(pub);

        Author eric = new Author("Eric","Evans");
        Book ddd = new Book( "Domain Drive designo","1234",pub);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        authoRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod","Johnson");
        Book noEJB= new Book( "J2EE Deveolpment EJB","1345435234",pub);
        rod.getBooks().add(noEJB);
        authoRepository.save(rod);
        bookRepository.save(noEJB);


    }
}
