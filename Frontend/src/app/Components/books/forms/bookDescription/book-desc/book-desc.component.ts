import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {Book} from "../../../../../Models/models";

@Component({
  selector: 'app-book-desc',
  templateUrl: './book-desc.component.html',
  styleUrls: ['./book-desc.component.css']
})
export class BookDescComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public book: any) { }

  ngOnInit(): void {
  }

}
