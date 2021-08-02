import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Author } from 'src/app/Models/models';
import { AuthorService } from 'src/app/Services/AuthorService/author.service';

@Component({
  selector: 'app-author-add-form',
  templateUrl: './author-add-form.component.html',
  styleUrls: ['./author-add-form.component.css']
})
export class AuthorAddFormComponent implements OnInit {

  popup: boolean = false;
  validation: boolean = true;


  // Form controls
  firstName = new FormControl('', [Validators.required, Validators.minLength(3)])
  lastName = new FormControl('', [Validators.required, Validators.minLength(3)])
  dateOfBirth = new FormControl('', [Validators.required])
  
  constructor(private authorService: AuthorService) { }

  ngOnInit(): void {
  }

  public addAuthor(firstName: string, lastName: string, dateOfBirth: string) {
    this.authorService.add(firstName, lastName, dateOfBirth).subscribe(
      value => {
        window.location.reload();
        console.log("dodawanie autora dziala");
      }, error => {
        console.log("dodawanie autora nei dziala");
      }
    )
  }

  public getErrorMessages(field: string) {
    return this.authorService.getErrorMessages(field,
      [
        this.firstName,
        this.lastName,
        this.dateOfBirth
      ]);
  };

    

  

}
     


