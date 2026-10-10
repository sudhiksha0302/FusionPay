import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-forgot-password',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './forgot-password.component.html',
  styleUrl: './forgot-password.component.css'
})
export class ForgotPasswordComponent {

  phoneNumber = '';

  otp = '';

  newPassword = '';

  message = '';

  otpSent = false;

  otpVerified = false;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  sendOtp() {

    this.authService.sendOtp(this.phoneNumber).subscribe({

      next: (response) => {

        this.message = response;
        this.otpSent = true;

      },

      error: (err) => {

        this.message = err.error || "Phone Number not found";

      }

    });

  }

  verifyOtp() {

    this.authService.verifyOtp(this.phoneNumber, this.otp).subscribe({

      next: (response) => {

        this.message = response;
        this.otpVerified = true;

      },

      error: (err) => {

        this.message = err.error || "Invalid OTP";

      }

    });

  }

  changePassword() {

    this.authService.changePassword(
      this.phoneNumber,
      this.newPassword
    ).subscribe({

      next: (response: any) => {

  this.message = response;

  setTimeout(() => {
    this.router.navigate(['/login']);
  }, 2000);

},

      error: (err) => {

        this.message = err.error || "Unable to change password";

      }

    });

  }

}