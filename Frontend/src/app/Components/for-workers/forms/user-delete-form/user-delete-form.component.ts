import { Component, OnInit } from '@angular/core';
import {User} from "../../../../Models/models";
import {UserService} from "../../../../Services/UserService/user.service";

@Component({
  selector: 'app-user-delete-form',
  templateUrl: './user-delete-form.component.html',
  styleUrls: ['./user-delete-form.component.css']
})
export class UserDeleteFormComponent implements OnInit {

  declare selectedUser: User;
  declare users: Array<User>;
  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getAll();
  }

  public getAll() {
    this.userService.getAll().subscribe(
      value => {
        this.users = value;
      },
      error => {
        console.log(error);
      }
    );
  }

  public deleteUser(id: number | undefined) {
    this.userService.delete(id).subscribe(
      value => {
        console.log("deleted");
      },
      error => {
        console.log("not deleted, error");
      }
    )
  }

}
