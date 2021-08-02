import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Author } from 'src/app/Models/models';
import { AuthorService } from 'src/app/Services/AuthorService/author.service';
import { ShareDataService } from 'src/app/Services/ShareDataService/share-data.service';
import { TokenStorageService } from 'src/app/Services/TokenStorageService/token-storage.service';
import { LoginComponent } from '../login/login.component';
import { AuthorAddFormComponent } from './forms/author-add-form/author-add-form.component';
import { AuthorDeleteFormComponent } from './forms/author-delete-form/author-delete-form.component';
import { AuthorUpdateFormComponent } from './forms/author-update-form/author-update-form.component';

@Component({
  selector: 'app-authors',
  templateUrl: './authors.component.html',
  styleUrls: ['./authors.component.css']
})
export class AuthorsComponent implements OnInit {
  declare authors: Array<Author>;
  constructor(private shareDataService: ShareDataService,private dialog: MatDialog, private authorService: AuthorService, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.getAll();
  }

  public isLogged() {
    return this.shareDataService.getLoggedIn();
  }

  public getRole() {
    return this.shareDataService.getRole();
  }

  public openAuthorAddForm() {
    if (this.tokenStorageService.getToken() == null) {
      const dialogRef = this.dialog.open(LoginComponent);
    } else {
      const dialogRef = this.dialog.open(AuthorAddFormComponent);
    }
  }

  public openAuthorDeleteForm() {
    if (this.tokenStorageService.getToken() == null) {
      const dialogRef = this.dialog.open(LoginComponent);
    } else {
      const dialogRef = this.dialog.open(AuthorDeleteFormComponent);
    }
  }

  public openAuthorUpdateForm() {
    if (this.tokenStorageService.getToken() == null) {
      const dialogRef = this.dialog.open(LoginComponent);
    } else {
      const dialogRef = this.dialog.open(AuthorUpdateFormComponent);
    }
  }

  public getAll() {
    this.authorService.getAll().subscribe(
      value => {
        this.authors = value;
      }, error => {
          console.log("blad w ladowaniu autorow");
      }
    )
  }
}
