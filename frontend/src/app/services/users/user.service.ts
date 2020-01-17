import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  getTasks(username){
    return this.httpClient.get("http://localhost:8080/register/get/".concat(username)) as Observable<any>;
  }

  registerUser(user, taskId) {
    return this.httpClient.post("http://localhost:8080/register/post/".concat(taskId), user) as Observable<any>;
  }

  loginUser(data) {
    return this.httpClient.post("http://localhost:8080/register/login/", data) as Observable<any>;
  }

  logout() {
    localStorage.removeItem('sessionUser');
    localStorage.removeItem('order');
  }

  checkSessionUser() {
    let sessionUser = JSON.parse(localStorage.getItem('sessionUser'));
    if (sessionUser && sessionUser.token) {
      return true;
    }

    return false;
  }

  getSessionUser() {
    let sessionUser = JSON.parse(localStorage.getItem('sessionUser'));
    if (sessionUser) {
      return sessionUser;
    }

    return null;
  }

}
