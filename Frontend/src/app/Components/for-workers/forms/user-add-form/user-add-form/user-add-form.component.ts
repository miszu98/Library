import { Component, OnInit } from '@angular/core';
import {RoleService} from "../../../../../Services/RoleService/role.service";
import {Role, User} from "../../../../../Models/models";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../../../Services/UserService/user.service";
import {RegisterService} from "../../../../../Services/RegisterService/register.service";

@Component({
  selector: 'app-user-add-form',
  templateUrl: './user-add-form.component.html',
  styleUrls: ['./user-add-form.component.css']
})
export class UserAddFormComponent implements OnInit {

  hide: boolean = false;
  declare roles: Array<Role>;
  declare selectedRole: Role;

  registerUserForm = new FormGroup(
    {
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required]),
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      role: new FormControl('', [Validators.required]),
    }
  )

  constructor(private roleService: RoleService, private registerService: RegisterService) { }

  ngOnInit(): void {
    this.getAll();
  }


  public getAll() {
    this.roleService.getAll().subscribe(
      value => {
        this.roles = value;
        console.log(value);
      },
      error => {
        console.log(error)
        console.log("nie moge pobrac danych o rolach");
      }
    );
  }

  public onSubmit() {
    let email = this.registerUserForm.get('email')?.value;
    let password = this.registerUserForm.get('password')?.value;
    let role = this.registerUserForm.get('role')?.value.id;
    let firstName = this.registerUserForm.get('firstName')?.value;
    let lastName = this.registerUserForm.get('lastName')?.value;

    this.registerService.register({
      id: 0,
      email: email,
      password: password,
      firstName: firstName,
      lastName: lastName,
      role: {
        id: role,
        roleName: ""
      }
    }).subscribe(
      value => {
        console.log(value);
        window.location.reload();
      },
      error => {
        console.log(error);
      }
    )

  }


}
