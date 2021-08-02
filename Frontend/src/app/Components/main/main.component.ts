import { LoginComponent } from './../login/login.component';
import { MatDialog } from '@angular/material/dialog';
import { Component, OnInit } from '@angular/core';
import { RegisterComponent } from '../register/register/register.component';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  constructor(private dialog: MatDialog) { }

  ngOnInit(): void {
  }

  public openLoginForm() {
    const dialogRef = this.dialog.open(LoginComponent);
  }

  public openRegisterForm() {
    const dialogRe = this.dialog.open(RegisterComponent);
  }

}
