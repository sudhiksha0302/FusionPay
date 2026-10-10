import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  hidePassword = true;
  submitted = false;
  loading = false;
  successMessage = '';

errorMessage = '';

registerForm!: FormGroup;


  cardTypes = [
    { value: 'GOLD', label: 'Gold Card' },
    { value: 'TITANIUM', label: 'Titanium Card' }
  ];

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {

    this.registerForm = this.fb.group({

      name: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(40),
          Validators.pattern('^[a-zA-Z ]+$')
        ]
      ],

      dateOfBirth: [
        '',
        Validators.required
      ],

      email: [
        '',
        [
          Validators.required,
          Validators.email
        ]
      ],

      phoneNumber: [
        '',
        [
          Validators.required,
          Validators.pattern('^[6-9][0-9]{9}$')
        ]
      ],

      username: [
        '',
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(20)
        ]
      ],

      password: [
        '',
        [
          Validators.required,
          Validators.minLength(8),
          Validators.pattern(
            '^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$'
          )
        ]
      ],

      address: [
        '',
        [
          Validators.required,
          Validators.minLength(10)
        ]
      ],

      bankName: [
        '',
        Validators.required
      ],

      accountNumber: [
        '',
        [
          Validators.required,
          Validators.pattern('^[0-9]{9,18}$')
        ]
      ],

      ifscCode: [
        '',
        [
          Validators.required,
          Validators.pattern('^[A-Z]{4}0[A-Z0-9]{6}$')
        ]
      ],

      cardType: [
        '',
        Validators.required
      ]

    });

  }

  get f() {
    return this.registerForm.controls;
  }

  register() {

  this.submitted = true;

  this.successMessage = '';
  this.errorMessage = '';

  if (this.registerForm.invalid) {
    return;
  }

  console.log(this.registerForm.value);
  this.authService.register(this.registerForm.value).subscribe({

    next: () => {

      this.successMessage = "Registration Successful! Redirecting to Login...";

      setTimeout(() => {

        this.router.navigate(['/login']);

      }, 1500);

    },

    error: (err) => {

      console.error(err);

      if (err.error?.message) {
        this.errorMessage = err.error.message;
      } else {
        this.errorMessage = "Registration Failed. Please check your details.";
      }

    }

  });

}

  resetForm() {

    this.submitted = false;

    this.registerForm.reset();

  }

}