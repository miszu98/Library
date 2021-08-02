import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Image } from 'src/app/Models/models';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private _url: string = "http://localhost:8080/api";

  constructor(private http: HttpClient) { }

  public uploadImage(file: File) : Observable<Image> {
    const formData: FormData = new FormData();
    formData.append('file', file);

    return this.http.post<Image>(this._url + "/images/", formData);
  }
}
