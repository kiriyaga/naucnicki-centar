import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { AddMagazineComponent } from './add-magazine/add-magazine.component';
import { NotregisterGuard } from './notregister-guard';
import { RegisterGuard } from './register-guard';


const routes: Routes = [
  { path: "register", component: RegisterComponent,canActivate: [NotregisterGuard] },
  { path: "login", component: LoginComponent,canActivate: [NotregisterGuard] },
  { path: "addmagazine", component: AddMagazineComponent,canActivate:[RegisterGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
