import { AuthService } from './../shared/auth.service';
import { LoginRequestPayload } from './loginRequest.payload';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  LoginRequestPayload: LoginRequestPayload;

  constructor(private authService: AuthService) {
    this.LoginRequestPayload = {
      username: '',
      password: ''
    };
   }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      'username': new FormControl(null, [Validators.required]),
      'password': new FormControl(null, [Validators.required])
    });
  }

  onLogin(): void{
    this.LoginRequestPayload.username = this.loginForm.get('username').value;
    this.LoginRequestPayload.password = this.loginForm.get('password').value;
    this.authService.login(this.LoginRequestPayload)
        .subscribe(data => {
          console.log('Login successfull');
        });
  }

}
