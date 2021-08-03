import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { User } from 'src/app/Models/models';
import { UserService } from 'src/app/Services/UserService/user.service';

@Component({
  selector: 'app-user-display-form',
  templateUrl: './user-display-form.component.html',
  styleUrls: ['./user-display-form.component.css']
})
export class UserDisplayFormComponent implements OnInit {

  declare users: Array<User>;
  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getAll();
    console.log(this.users);
  }

  public getAll() {
    this.userService.getAll().subscribe(
      value => {
        console.log(value);
        this.users = value;
      }, 
      error => {
        console.log("BLAD PRZY POBIERANIU UZYTKOWNIKÓW Z BAZY");
      }
    )
  }

}
