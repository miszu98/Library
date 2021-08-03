import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { RegisterService } from 'src/app/Services/RegisterService/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  hide: boolean = true;

  registerForm = new FormGroup({
    firstName : new FormControl('', [Validators.minLength(3), Validators.required]),
    lastName : new FormControl('', [Validators.minLength(3), Validators.required]),
    email : new FormControl('', [Validators.email, Validators.required]),
    password1 : new FormControl('', [Validators.required]),
    password2 : new FormControl('', [Validators.required]),
  });

  constructor(private registerService: RegisterService) { }

  ngOnInit(): void {
  }

  public getErrorMessage(value: string) {
    let field = this.registerForm.get(value);
    if (field?.hasError('required')) {
      return 'You must enter a value';
    } else if (field?.hasError('email')) {
      return 'Wrong email format';
    } else if (!field?.hasError('min')) {
      return 'Min length is 3';
    } return '';
  }



  public register(registerForm: FormGroup) {
    this.registerService.register({
      id: 0,
      email: registerForm.get('email')?.value,
      password: registerForm.get('password1')?.value,
      firstName: registerForm.get('firstName')?.value,
      lastName: registerForm.get('lastName')?.value,
      role: {
        id: 1,
        roleName: ""
      }
    }).subscribe(
      value => {
        window.location.reload();
        console.log(value);
      },
      error => {
        console.log(error);
      }
    )
  }

}
