import { BookType } from './../../Models/models';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TypeService {

  private url: string = "http://localhost:8080/api";

  constructor(private http: HttpClient) { }


  public getAll() : Observable<Array<BookType>> {
    return this.http.get<Array<BookType>>(this.url + "/types/");
  }


}
