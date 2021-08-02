import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { Author } from 'src/app/Models/models';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  private url: string = "http://localhost:8080/api";

  constructor(private http: HttpClient) { }

  public getAll(): Observable<Array<Author>> {
    return this.http.get<Array<Author>>(this.url + "/authors/");
  }

  public add(firstName: string, lastName: string, dateOfBirth: string): Observable<Author> {
    console.log(firstName);
    console.log(lastName);
    console.log(dateOfBirth);
    return this.http.post<Author>(this.url + "/authors/", {
      firstName: firstName,
      lastName: lastName,
      dateOfBirth: dateOfBirth
    });
  }

  public delete(id: number | undefined) {
    return this.http.delete<Author>(this.url + "/authors/" + id);
  }

  public update(id: number, firstName: string, lastName: string, dateOfBirth: string) : Observable<Author> {
    return this.http.put<Author>(this.url + "/authors/" + id, {firstName: firstName, lastName: lastName, dateOfBirth: dateOfBirth});
  }

  public getErrorMessages(field: string, controls: Array<FormControl>) {
    if (field == 'firstName') {
      if (controls[0].hasError('required')) {
        return "You must enter a value";
      } return !controls[0].hasError('minLength') ? 'min length 3' : '';
    } else {
      if (field == 'lastName') {
        if (controls[1].hasError('required')) {
          return "You must enter a value";
        } return !controls[1].hasError('minLength') ? 'min length 3' : '';
      } else {
        if (field == 'dateOfBirth') {
          if (controls[2].hasError('required')) {
            return "You must enter a value";
          } return !controls[2].hasError('minLength') ? 'min length 3' : '';
        } return '';
      };
  };
}

  
}


