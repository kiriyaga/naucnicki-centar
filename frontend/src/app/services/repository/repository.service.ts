import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RepositoryService {

  categories = [];
  languages = [];
  books = [];

  constructor(private httpClient: HttpClient) {
  }
  startProcessRegistration(){
    return this.httpClient.get('http://localhost:8080/register/get') as Observable<any>
  }
  startProcessAddMagazine(){
    return this.httpClient.get('http://localhost:8080/magazine/get') as Observable<any>
  }

 

}
