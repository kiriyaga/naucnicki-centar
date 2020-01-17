import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/users/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private username;
  private password;

  constructor(private userService : UserService) { }

  ngOnInit() {
  }

  onSubmit(value, form){
    var o  = {"username":value['username'],"password":value['password']};
    console.log(o);
    let x = this.userService.loginUser(o);
    x.subscribe(
      user => {
        if (user && user.token) {
          localStorage.setItem('sessionUser', JSON.stringify(user));
        }
        alert("Successfully!");
      },
      err => {
        console.log("Error occured");
      }
    );
  }

}
