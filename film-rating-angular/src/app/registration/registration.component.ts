import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {UserRegistration} from "./UserRegistration";
import {AuthService} from "../services/AuthService";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  user: UserRegistration = { username: '', password: '', email: ''};
  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private router: Router, private authService: AuthService) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(50)]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(255)]],
      email: ['', [Validators.required, Validators.email, Validators.maxLength(255)]]
    });
  }
  submitForm() {
      this.authService.signUp(this.user).subscribe({
        next: (response) => {
          this.authService.setToken(response.token);
          this.router.navigate([""]);
        },
        error: (error) => {
          console.error('Error:', error);
        },
      });
  }

  login() {
    this.router.navigate([""]);
  }
}
