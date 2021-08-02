import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-add-form',
  templateUrl: './user-add-form.component.html',
  styleUrls: ['./user-add-form.component.css']
})
export class UserAddFormComponent implements OnInit {

  hide: boolean = false;
  
  constructor() { }

  ngOnInit(): void {
  }

}
