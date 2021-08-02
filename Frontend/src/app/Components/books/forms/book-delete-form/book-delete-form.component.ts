import { BookService } from 'src/app/Services/BookService/book.service';
import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/Models/models';

@Component({
  selector: 'app-book-delete-form',
  templateUrl: './book-delete-form.component.html',
  styleUrls: ['./book-delete-form.component.css']
})
export class BookDeleteFormComponent implements OnInit {

  declare books: Array<Book>;
  declare selectedBook: Book;
  constructor(private bookService: BookService) { }

  ngOnInit(): void {
    this.getAll();
  }

  public getAll() {
    this.bookService.getAll().subscribe(
      value => {
        this.books = value;
      },
      error => {
        console.log(error);
      }
    )
  }

  public deleteBook(id: number | null | undefined) {
    this.bookService.delete(id).subscribe(
      value => {      
      
        console.log("usuwam ksiazke");
      },
      error => {
          console.log("nie uwusam - blad");
          console.log(error);
      }
    )
    window.location.reload();
  }

}
