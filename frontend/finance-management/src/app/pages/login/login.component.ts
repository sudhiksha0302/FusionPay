import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginForm!: FormGroup;

  message = '';
  successMessage = '';
  errorMessage = '';
  submitted = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {

    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      role: ['USER', Validators.required]
    });

  }

  login() {

    this.submitted = true;

    this.successMessage = '';
    this.errorMessage = '';

    if (this.loginForm.invalid) {
      return;
    }

    const role = this.loginForm.value.role;

    // ================= ADMIN LOGIN =================

    if (role === 'ADMIN') {

      this.authService.adminLogin(this.loginForm.value).subscribe({

        next: (response) => {

          this.successMessage = `Welcome ${response.adminName}! Redirecting...`;

          setTimeout(() => {

            this.router.navigate(['/admin']);

          }, 1500);

        },

        error: (err) => {

          console.error(err);

          if (err.error?.message) {
            this.errorMessage = err.error.message;
          } else {
            this.errorMessage = "Invalid Admin Username or Password";
          }

        }

      });

    }

    // ================= USER LOGIN =================

    else {

      this.authService.login(this.loginForm.value).subscribe({

        next: (response) => {

          this.successMessage = `Welcome ${response.name}! Redirecting...`;

          setTimeout(() => {

            this.router.navigate(['/dashboard']);

          }, 1500);

        },

        error: (err) => {

          console.error(err);

          if (err.error?.message) {
            this.errorMessage = err.error.message;
          } else {
            this.errorMessage = "Invalid Username or Password";
          }

        }

      });

    }

  }

}