import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UserAddFormComponent } from './forms/user-add-form/user-add-form/user-add-form.component';

@Component({
  selector: 'app-for-workers',
  templateUrl: './for-workers.component.html',
  styleUrls: ['./for-workers.component.css']
})
export class ForWorkersComponent implements OnInit {

  constructor(private dialog: MatDialog) { }


  ngOnInit(): void {
  }

  public openAddUserForm() {
    const dialogRef = this.dialog.open(UserAddFormComponent);
  }



}
