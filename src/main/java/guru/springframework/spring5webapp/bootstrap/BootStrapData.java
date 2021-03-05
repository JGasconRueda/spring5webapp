package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher = new Publisher("Juan", "Calle Marte 8","Sevilla","Andalucia","41700");
        publisherRepository.save(publisher);

        Author gabriel = new Author("Gabriel", "Garcia Marquez");
        Book soledad = new Book("Cien años de soledad","1234567");
        gabriel.getBooks().add(soledad);
        soledad.getAuthors().add(gabriel);

        soledad.setPublisher(publisher);
        publisher.getBooks().add(soledad);

        authorRepository.save(gabriel);
        bookRepository.save(soledad);
        publisherRepository.save(publisher);

        Author mario = new Author("Mario", "Vargas Llosa");
        Book ciudad = new Book("La ciudad y los perros","33445566");
        mario.getBooks().add(ciudad);
        ciudad.getAuthors().add(mario);

        ciudad.setPublisher(publisher);
        publisher.getBooks().add(ciudad);

        authorRepository.save(mario);
        bookRepository.save(ciudad);
        publisherRepository.save(publisher);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Publishers: " + publisherRepository.count());
        System.out.println("Publisher number of Books: "+publisher.getBooks().size());
    }
}
