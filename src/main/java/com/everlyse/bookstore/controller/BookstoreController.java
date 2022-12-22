package com.everlyse.bookstore.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everlyse.bookstore.constants.UserAccess;
import com.everlyse.bookstore.entity.Author;
import com.everlyse.bookstore.entity.Book;
import com.everlyse.bookstore.entity.BookAuthor;
import com.everlyse.bookstore.request.BookDetailsRequest;
import com.everlyse.bookstore.request.FindBookRequest;
import com.everlyse.bookstore.service.AuthorService;
import com.everlyse.bookstore.service.BookAuthorService;
import com.everlyse.bookstore.service.BookService;
import com.everlyse.bookstore.service.UsersService;

@RestController
@RequestMapping("api/bookstore")
public class BookstoreController {
  
  @Autowired
  private final BookService bookService;
  @Autowired
  private final BookAuthorService bookAuthorService;
  @Autowired
  private final AuthorService authorService;
  @Autowired
  private final UsersService usersService;


  BookstoreController(BookService bookService, BookAuthorService bookAuthorService, AuthorService authorService, UsersService usersService) {
    this.bookService = bookService;
    this.bookAuthorService = bookAuthorService;
    this.authorService = authorService;
    this.usersService = usersService;
  }

  @GetMapping("/health")
  public ResponseEntity<String> health() {
    return ResponseEntity.ok("OK");
  }

  @PostMapping("/create")
  public ResponseEntity<String> createBook(@RequestBody BookDetailsRequest details) {

    Book book = new Book(details.isbn, details.title, details.publishedYear, details.price, details.genre);
    bookService.addBook(book);
    Long bookid = book.getId();

    for (Map.Entry<String, String> entry : details.getAuthors().entrySet()) {
      Author author = new Author(entry.getKey(), entry.getValue());
      Long authorId = authorService.saveAuthor(author);

      BookAuthor bookAuthor = new BookAuthor(bookid, authorId);
      bookAuthorService.save(bookAuthor);
    }
    return ResponseEntity.ok(details.title + " created");
  }

  @PostMapping("/update")
  public ResponseEntity<String>  updateBook(@RequestBody BookDetailsRequest details) {
    //get book id
    Book book = bookService.findByIsbn(details.getIsbn());
    if (book == null)
      return ResponseEntity.badRequest().body(details.title + " doesn't exist, please create a book");

    book.setTitle(details.title);
    book.setGenre(details.genre);
    book.setPrice(details.price);
    book.setPublishedYear(details.publishedYear);

    bookService.updateBook(book);
    
    List<BookAuthor> bookAuthorList = bookAuthorService.findByBookId(book.getId());
    List<Author> authorList = new ArrayList<>();
    List<String> authorName = new ArrayList<>();

    for (BookAuthor bookAuthor : bookAuthorList) {
      Author author = authorService.findById(bookAuthor.getAuthorId());
      authorList.add(author);
      authorName.add(author.getName());
    }

    for (Map.Entry<String, String> entry : details.getAuthors().entrySet()) {
      Author author = null;
      if (authorName.contains(entry.getKey())) {
        int index = authorName.indexOf(entry.getKey());
        author = authorList.get(index);
        author.setBirthday(entry.getValue());
        authorService.saveAuthor(author);
      } else {
        author = new Author(entry.getKey(), entry.getValue());
        authorService.saveAuthor(author);
        bookAuthorService.save(new BookAuthor(book.getId(), author.getId()));
      }
    }
    
    return ResponseEntity.ok(details.title + " updated");
  }

  @GetMapping("/find")
  public ResponseEntity<?> getBook(@RequestBody FindBookRequest findBookRequest) {
    if (findBookRequest.getBookTitle() != "" && (findBookRequest.getAuthorBirthday() != "" && findBookRequest.getAuthorName() != "")) 
      return getBook(findBookRequest.getBookTitle(), findBookRequest.getAuthorName(), findBookRequest.getAuthorBirthday());
    else if (findBookRequest.getBookTitle() != "") 
      return getBook(findBookRequest.getBookTitle());
    else if (findBookRequest.getAuthorName() != "" && findBookRequest.getAuthorBirthday() != "")
      return getBook(findBookRequest.getAuthorName(), findBookRequest.getAuthorBirthday());
    else 
      return ResponseEntity.ok().body("Please submit a request in the following format: " + new FindBookRequest());
  }

  //find via title
  public ResponseEntity<?> getBook(String title) {
    
    List<Book> bookList = bookService.findByTitle(title);
    if (bookList == null) {
      return ResponseEntity.ok().body("Do not have book with title");
    }

    List<BookDetailsRequest> output = new ArrayList<>();

    for (Book book: bookList) {
      HashMap<String, String> authors = new HashMap<>();
      List<BookAuthor> bookAuthorList = bookAuthorService.findByBookId(book.getId());
      for (BookAuthor bookAuthor: bookAuthorList) {
        Author author = authorService.findById(bookAuthor.getAuthorId());
        if (author != null)
          authors.put(author.getName(), author.getBirthday());
      }
      output.add(new BookDetailsRequest(book.getIsbn(), book.getTitle(), book.getPublishedYear(), book.getPrice(), book.getGenre(), authors));
    }
    return ResponseEntity.ok().body(output); //return all books with title name written via different authors
  }

  //find via author
  public ResponseEntity<?> getBook(String name, String birthday) {
    Author authorFromDb = authorService.findByNameAndBirthday(name, birthday);
    
    if(authorFromDb == null) {
      return ResponseEntity.ok().body("Cannot find any book written via Author: " + name);
    }

    List<Book> bookList = new ArrayList<>();
    List<BookAuthor> bookAuthorList = bookAuthorService.findByAuthorId(authorFromDb.getId());

    for (BookAuthor bookAuthor: bookAuthorList) {
      Book book = bookService.findById(bookAuthor.getbookId());
      if (book != null)
        bookList.add(book);
    }

    return ResponseEntity.ok().body(bookList); //return list of books written via author
  }

  //find via title & author
  public ResponseEntity<?> getBook(String bookTitle, String authorName, String authorBirthday) {
    List<Book> bookList = bookService.findByTitle(bookTitle);

    Author author = authorService.findByNameAndBirthday(authorName, authorBirthday);

    if (bookList != null) {
      for (Book book: bookList) {
        BookAuthor bookAuthor = bookAuthorService.findByBookIdAndAuthorId(book.getId(), author.getId());
        if (bookAuthor != null){
          return ResponseEntity.ok().body(book);
        }
      }
    }

    return ResponseEntity.ok().body("Unable to find book with title " + bookTitle +  " and Author: " + authorName + ", " + authorBirthday); //return 1 book with title & written via author
  }

  @PostMapping("/delete")
  public ResponseEntity<?> deleteBook(@RequestHeader(value = "username") String username, @RequestHeader(value = "password") String password, @RequestParam String isbn) {
    
    UserAccess access = usersService.findUserAccess(username, password);
    if (access != UserAccess.ADMIN) 
      return ResponseEntity.ok().body("User do not have access to delete function");
    else {
      Book book = bookService.findByIsbn(isbn);
      if (book == null) 
        return ResponseEntity.ok().body("book is not registered in store");
      
      List<BookAuthor> bookAuthorList = bookAuthorService.findByBookId(book.getId());

      for (BookAuthor bookAuthor: bookAuthorList) {
        //find out how many books the author have
        List<BookAuthor> list = bookAuthorService.findByAuthorId(bookAuthor.getAuthorId());
        if (list.size() == 1) {
          authorService.delete(authorService.findById(bookAuthor.getAuthorId()));
        }
        bookAuthorService.delete(bookAuthor);
      }
      bookService.deleteBook(book, access);
      return ResponseEntity.ok().body("Successfully removed book from store");
    } 
  }

}
