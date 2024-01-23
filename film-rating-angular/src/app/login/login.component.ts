import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from "@angular/router";
import {UserLogin} from "./UserLogin";
import {AuthService} from "../services/AuthService";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user: UserLogin = { username: '', password: '' };
  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private router: Router, private authService: AuthService) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(50)]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(255)]],
    });
  }

  submitForm() {
      this.authService.signIn(this.user).subscribe({
        next: (response) => {
          this.authService.setToken(response.token);
          this.router.navigate(["/rating"])
        },
        error: (error) => {
          console.error('Error:', error);
        },
      });
    }

  registration() {
    this.router.navigate(["/registration"]);
  }
}
