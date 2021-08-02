import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/app/Models/models';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private _url: string = "http://localhost:8080/api";

  constructor(private http: HttpClient) { }

  public register(user: User) {
    return this.http.post(this._url + "/users/register", user);
  }


}
