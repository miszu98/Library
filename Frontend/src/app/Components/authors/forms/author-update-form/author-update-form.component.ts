import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Author } from 'src/app/Models/models';
import { AuthorService } from 'src/app/Services/AuthorService/author.service';

@Component({
  selector: 'app-author-update-form',
  templateUrl: './author-update-form.component.html',
  styleUrls: ['./author-update-form.component.css']
})
export class AuthorUpdateFormComponent implements OnInit {

  validation: boolean = true;
  declare authors: Array<Author>;
  declare selectedAuthor: Author;
  select: boolean = false;

  // Form controls
  firstName = new FormControl('', [Validators.required, Validators.minLength(3)])
  lastName = new FormControl('', [Validators.required, Validators.minLength(3)])
  dateOfBirth = new FormControl('', [Validators.required])

  constructor(private authorService: AuthorService) { }

  ngOnInit(): void {
    this.loadAll();
  }

  public selectOption() {
    this.select = true;
  }

  public loadAll() {
    this.authorService.getAll().subscribe(
      value => {
        this.authors = value;
      }, error => {
        console.log("nie dziala ladowanie autorów");
      }
    )
  }


  public updateAuthor(id: number, firstName: string, lastName: string, dateOfBirth: string) {
    this.authorService.update(id, firstName, lastName, dateOfBirth).subscribe(
      value => {
        console.log("aktualizacja dziala");
        window.location.reload();
      }, error => {
        console.log(error);
        console.log("aktualizacja nie dziala");
      }
    )
  }

  public getErrorMessages(field: string) {
    return this.authorService.getErrorMessages(field,
      [
        this.firstName,
        this.lastName,
        this.dateOfBirth
      ])
  }

}
