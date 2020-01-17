import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class NotregisterGuard {
  private sessionUser:any
  constructor(private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
  
    this.sessionUser = JSON.parse(localStorage.getItem('sessionUser'));
    if (this.sessionUser == null){

          return true;
        }
        return false;
    }
   
  }