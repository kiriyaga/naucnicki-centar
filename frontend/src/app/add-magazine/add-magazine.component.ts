import { Component, OnInit } from '@angular/core';
import { RepositoryService } from '../services/repository/repository.service';
import { UserService } from '../services/users/user.service';

@Component({
  selector: 'app-add-magazine',
  templateUrl: './add-magazine.component.html',
  styleUrls: ['./add-magazine.component.css']
})
export class AddMagazineComponent implements OnInit {

  private formFieldsDto = null;
  private formFields = [];
  private processInstance = "";
  private enumValues = [];

  constructor(private userService : UserService, private repositoryService : RepositoryService) { }

  ngOnInit() {

    let sessionUser = this.userService.getSessionUser();
    let x;
    if(sessionUser !=null && sessionUser.role == "ROLE_ADMIN"){
      x= this.userService.getTasks(sessionUser.username);
    }
    else {
      x = this.repositoryService.startProcessAddMagazine();
    }

    x.subscribe(
      res => {
        console.log(res);
        //this.categories = res;
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        this.processInstance = res.processInstanceId;
        this.formFields.forEach( (field) =>{
          
          if( field.type.name=='enum'){
            this.enumValues = Object.keys(field.type.values);
          }
        });
      },
      err => {
        console.log("Error occured");
      }
    );
  }

  onSubmit(value, form){
    let o = new Array();

    for (var property in value) {
      console.log(property);
      alert(value[property]);
      o.push({fieldId : property, fieldValue : value[property]});
    }

    console.log(o);
    let x = this.userService.registerUser(o, this.formFieldsDto.taskId);

    x.subscribe(
      res => {
        console.log(res);
        
        if(res != null){
          console.log(res);
        //this.categories = res;
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        this.processInstance = res.processInstanceId;
        this.formFields.forEach( (field) =>{
          
          if( field.type.name=='enum'){
           
            this.enumValues = Object.keys(field.type.values);
            alert(this.enumValues)
          }
        });
        }
        alert("Successfully! ");
      },
      err => {
        console.log("Error occured");
      }
    );
  }

}
