import { AuthService } from './../shared/auth.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SignupRequestPayload } from './signup-request.payload';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupRequestPayload: SignupRequestPayload;
  signupForm: FormGroup;

  constructor(private authService: AuthService) {
    this.signupRequestPayload = {
      username : '',
      email: '',
      password: ''
    };
   }

  ngOnInit(): void {
    this.signupForm = new FormGroup({
      'email' : new FormControl(null, [Validators.required, Validators.email]),
      'username' : new FormControl(null, [Validators.required, Validators.minLength(4)]),
      'password' : new FormControl(null, [Validators.required, Validators.minLength(5)])
    });
  }

  onSubmit(): void {
    this.signupRequestPayload.email = this.signupForm.get('email').value;
    this.signupRequestPayload.username = this.signupForm.get('username').value;
    this.signupRequestPayload.password = this.signupForm.get('password').value;

    this.authService.singUp(this.signupRequestPayload)
      .subscribe(data => {
        console.log(data);
      });
  }

}
