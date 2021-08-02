import { Component, OnInit } from '@angular/core';
import { Author } from 'src/app/Models/models';
import { AuthorService } from 'src/app/Services/AuthorService/author.service';

@Component({
  selector: 'app-author-delete-form',
  templateUrl: './author-delete-form.component.html',
  styleUrls: ['./author-delete-form.component.css']
})
export class AuthorDeleteFormComponent implements OnInit {

  declare selectedAuthor: Author
  declare authors: Array<Author>;
  constructor(private authorService: AuthorService) { }

  ngOnInit(): void {
      this.getAll();
  }

  public deleteAuthor(id: number | undefined) {
    this.authorService.delete(id).subscribe(
      value => {
        console.log("usuwanie autora dziala");
        window.location.reload();
      }, error => {
        console.log("usuwanie autora nie dziala");
      }
    )
  }

  public getAll() {
    this.authorService.getAll().subscribe(
      value => {
          this.authors = value;
      }, error => {
          console.log("blad w pobraniu uzytkowników")
      }
    )
  }



}
