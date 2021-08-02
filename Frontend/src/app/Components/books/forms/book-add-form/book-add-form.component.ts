import { BookService } from './../../../../Services/BookService/book.service';
import { TypeService } from './../../../../Services/TypeService/type.service';
import { BookType } from './../../../../Models/models';
import { Component, OnInit } from '@angular/core';
import { Author, Book } from 'src/app/Models/models';
import { AuthorService } from 'src/app/Services/AuthorService/author.service';
import { ImageService } from 'src/app/Services/ImageService/image.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-book-add-form',
  templateUrl: './book-add-form.component.html',
  styleUrls: ['./book-add-form.component.css']
})
export class BookAddFormComponent implements OnInit {

  declare selectedAuthor: Author;
  declare authors: Array<Author>;
  declare types: Array<BookType>;
  declare selectedType: BookType;
  declare image: File;
  declare url: string;
  loading: boolean = false;

  constructor(private imageService: ImageService, private authorService: AuthorService, private typeService: TypeService, private bookService: BookService) { }

  ngOnInit(): void {
    this.getAll();
    this.getAllTypes();
  }

  public runLoading() {
    this.loading = true;
  }

  public stopLoading() {
    this.loading = false;
  }

  public getAll() {
    this.authorService.getAll().subscribe(
      value => {
        this.authors = value;
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

  public onFileChanged(event: any) {
    this.image = event.target.files[0];
  }


  public add(author: Author, type: BookType, title: string, publicationDate: string, quantity: string, description: string) {
    this.runLoading();
    this.imageService.uploadImage(this.image).subscribe(
      value => {
        this.url = value.url;
        console.log(this.url);
      }, 
      error => {
        console.log(error);
      }
    )
    setTimeout(() => {
      let book: Book = {
        id: null,
        author: author,
        title: title,
        bookType: type,
        publicationDate: publicationDate,
        quantity: (quantity as unknown) as number,
        description: description,
        imageUrl: this.url
      };
      this.bookService.add(book).subscribe(
        value => {
          console.log("udalo sie");
        }, error => {
          console.log("nie udalo sie");
        }
      )
      this.stopLoading();
      window.location.reload();
    }, 5000);

   

  

    
  }




}
