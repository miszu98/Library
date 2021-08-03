import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UserAddFormComponent } from './forms/user-add-form/user-add-form/user-add-form.component';
import {UserDeleteFormComponent} from "./forms/user-delete-form/user-delete-form.component";
import { UserDisplayFormComponent } from './forms/userDisplayForm/user-display-form/user-display-form.component';

@Component({
  selector: 'app-for-workers',
  templateUrl: './for-workers.component.html',
  styleUrls: ['./for-workers.component.css']
})
export class ForWorkersComponent implements OnInit {


  constructor(private dialog: MatDialog) { }


  ngOnInit(): void {}

  public openAddUserForm() {
    const dialogRef = this.dialog.open(UserAddFormComponent);
  }

  public openDeleteUserForm() {
    const dialogRef = this.dialog.open(UserDeleteFormComponent);
  }

  public openDisplayUsersForm() {
    const dialogRef = this.dialog.open(UserDisplayFormComponent);
  }


}
