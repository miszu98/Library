import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Role} from "../../Models/models";

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  private url: string = "http://localhost:8080/api";

  constructor(private http: HttpClient) { }

  public getAll() : Observable<Array<Role>> {
    return this.http.get<Array<Role>>(this.url + "/roles/");
  }
}
