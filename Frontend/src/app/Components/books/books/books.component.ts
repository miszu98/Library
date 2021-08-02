import { BookUpdateFormComponent } from './../forms/book-update-form/book-update-form.component';
import { BookDeleteFormComponent } from './../forms/book-delete-form/book-delete-form.component';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Book } from 'src/app/Models/models';
import { BookService } from 'src/app/Services/BookService/book.service';
import { BookAddFormComponent } from '../forms/book-add-form/book-add-form.component';
import { ActivatedRoute } from '@angular/router';
import { ShareDataService } from 'src/app/Services/ShareDataService/share-data.service';
import { TokenStorageService } from 'src/app/Services/TokenStorageService/token-storage.service';
import { LoginComponent } from '../../login/login.component';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {

  declare books: Array<Book>;
  declare firstName: string;
  declare lastName: string;
  constructor(private shareDataService: ShareDataService, private tokenStorageService: TokenStorageService, private bookService: BookService, private dialog: MatDialog, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(
      params => {
        this.firstName = params['firstName'];
        this.lastName = params['lastName'];
      }
    )
    console.log(this.firstName);
    if (this.firstName == undefined && this.lastName == undefined) {
      this.getAll();
    } else {
      this.getBooksByAuthor(this.firstName, this.lastName);
    }
    
  }

  public isLogged() {
    return this.shareDataService.getLoggedIn();
  }

  public getRole() {
    return this.shareDataService.getRole();
  }

  public getBooksByAuthor(firstName: string, lastName: string) {
    this.bookService.getBooksByAuthor(firstName, lastName).subscribe(
      values => {
        this.books = values;
      }, 
      error => {
        console.log(error);
      }
    )
  }

  public getAll() {
    this.bookService.getAll().subscribe(
      value => {
        this.books = value;
      }, error => {
        console.log(error);
      }
    )
  }

  public add(book: Book) {
      this.bookService.add(book).subscribe(
        value => {
          console.log("dodalo ksiazke");
        }, 
        error => {
          console.log("nie dodalo ksiazki - blad");
        }
      )
   
  }

  public delete(id: number) {
    this.bookService.delete(id).subscribe(
      value => {
        console.log("usunelo ksiazke");
      },
      error => {
        console.log("nie usunelo - blad");
      }
    )
  }

  public update(id: number, book: Book) {
    this.bookService.update(id, book).subscribe(
      value => {
        console.log("aktualizuje ksiazke");
      },
      error => {
        console.log("nie aktualizuje - blad");
      }
    )
  }


  public openAddBookForm() {
    if (this.tokenStorageService.getToken() == null) {
      const dialogRef = this.dialog.open(LoginComponent).afterClosed(

      );
    } else {
      const dialogRef = this.dialog.open(BookAddFormComponent, {
        width: "620px",
        height: "750px"
      });
    }
   
  }

  public openDeleteBookForm() {
    if (this.tokenStorageService.getToken() == null) {
      const dialogRef = this.dialog.open(LoginComponent);
    } else {
      const dialogRef = this.dialog.open(BookDeleteFormComponent);

    }
  }

  public openUpdateBookForm() {
    if (this.tokenStorageService.getToken() == null) {
      const dialogRef = this.dialog.open(LoginComponent);
    } else {
      const dialogRef = this.dialog.open(BookUpdateFormComponent, {
        width: "600px",
        height: "900px"
      });
    }

  }


}
