import { AuthorService } from './../../../../Services/AuthorService/author.service';
import { TypeService } from './../../../../Services/TypeService/type.service';
import { BookService } from './../../../../Services/BookService/book.service';
import { Author, BookType, Book } from './../../../../Models/models';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-book-update-form',
  templateUrl: './book-update-form.component.html',
  styleUrls: ['./book-update-form.component.css']
})
export class BookUpdateFormComponent implements OnInit {
  
  declare authors: Array<Author>;
  declare types: Array<BookType>;
  declare books: Array<Book>;
  declare selectedAuthor: Author;
  declare selectedType: BookType;
  declare selectedBook: Book;

  constructor(private bookService: BookService, private typeService: TypeService, private authorService: AuthorService) { }

  ngOnInit(): void {
    this.getAllBooks();
    this.getAllAuthors();
    this.getAllTypes();
  }

  public setAuthorAndType() {
    for (let author of this.authors) {
      if (author.id == this.selectedBook.author.id) {
        this.selectedAuthor = author;
        break;
      }
    }

    for(let type of this.types) {
      if (type.id == this.selectedBook.bookType.id) {
        this.selectedType = type;
        break;
      }
    }
  }

  public update(book: Book, author: Author, type: BookType, title: string, date: string, quantity: string, description: string, imageUrl: string) {
    book.author = author;
    book.bookType = type;
    book.title = title;
    book.publicationDate = date;
    book.quantity =  Number(quantity);
    book.description = description;
    book.imageUrl = imageUrl;
    this.bookService.update(book.id, book).subscribe(
      value => {
        window.location.reload();
        console.log("update dziala");
      }, 
      error => {
        console.log("update nie dziala");
      }
    )

  } 


  public getAllBooks() {
    this.bookService.getAll().subscribe(
      value => {
        this.books = value;
      },
      error => {
        console.log(error);
      }
    )
  }

  public getAllTypes() {
    this.typeService.getAll().subscribe(
      value => {
        this.types = value;
      },
      error => {
        console.log(error);
      }
    )
  }

  public getAllAuthors() {
    this.authorService.getAll().subscribe(
      value => {
        this.authors = value;
      },
      error => {
        console.log(error);
      }
    )
  }

}
