import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/Services/AuthService/auth.service';
import { ShareDataService } from 'src/app/Services/ShareDataService/share-data.service';
import { TokenStorageService } from 'src/app/Services/TokenStorageService/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  hide: boolean = true;
  isLoggedIn: boolean = false;
  isLoginFailed: boolean = false;
  errorMessage = '';
  declare role: string;

  loginForm = new FormGroup({
    email : new FormControl('', [Validators.required, Validators.email]),
    password : new FormControl('', Validators.required)
  });

  constructor(private shareDataService: ShareDataService, private authService: AuthService, private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.role = this.tokenStorage.getUser().role;
    }
  }

  public getErrorMessageEmail() {
    if (this.loginForm.get('email')?.hasError('required')) {
      return 'You must enter a value';
    }
    return this.loginForm.get('email')?.hasError('email') ? 'Not a valid email' : '';
  }

  public getErrorMessagePassword() {
    if (this.loginForm.get('password')?.hasError('required')) {
      return 'You must enter a value';
    } return '';
  }

  public onSubmit() {
    let email = this.loginForm.get('email')?.value;
    let password = this.loginForm.get('password')?.value;
    
    this.authService.login({email: email, password: password}).subscribe(
      value => {
        this.tokenStorage.saveToken(value.token);
        this.tokenStorage.saveUser(value);
        this.shareDataService.setLoggedIn(true);
        this.shareDataService.setLoginFailed(false);
        this.shareDataService.setRole(this.tokenStorage.getUser().role);
        window.location.reload();
      },
      err => {
        this.shareDataService.setLoggedIn(false);
        this.shareDataService.setLoginFailed(true);
        this.shareDataService.setErrorMessage(err.toString());
      }
    )
  }

  public reloadPage() {
    window.location.reload();
  }

}
