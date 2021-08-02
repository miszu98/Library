import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from 'src/app/Models/models';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private url: string = "http://localhost:8080/api"

  constructor(private http: HttpClient) { }

  public getAll() : Observable<Array<Book>> {
    return this.http.get<Array<Book>>(this.url + "/books/");
  }

  public add(book: Book) : Observable<Book> {
    return this.http.post<Book>(this.url + "/books/", book);
  }

  public delete(id: number | null | undefined) : Observable<Book> {
    return this.http.delete<Book>(this.url + "/books/" + id);
  }

  public update(id: number | null | undefined, book: Book) {
    return this.http.put<Book>(this.url + "/books/" + id, book);
  }

  public getBooksByAuthor(firstName: string, lastName: string) : Observable<Array<Book>> {
    return this.http.get<Array<Book>>(this.url + "/books/author=" + firstName + "," + lastName);
  }


}
