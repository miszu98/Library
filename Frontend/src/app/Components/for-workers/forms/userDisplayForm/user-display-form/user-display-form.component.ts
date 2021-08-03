import {Component, OnInit, ViewChild} from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { User } from 'src/app/Models/models';
import { UserService } from 'src/app/Services/UserService/user.service';
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-user-display-form',
  templateUrl: './user-display-form.component.html',
  styleUrls: ['./user-display-form.component.css']
})
export class UserDisplayFormComponent implements OnInit {

  declare users: Array<User>;
  displayedColumns: string[] = ['email', 'firstName', 'lastName', 'dateJoined', 'role'];
  constructor(private userService: UserService) { }



  ngOnInit(): void {
    this.getAll();
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
