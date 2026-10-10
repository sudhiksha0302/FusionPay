import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

import { environment } from '../../../environments/environment';

import { Login } from '../models/login';
import { LoginResponse } from '../models/login-response';
import { User } from '../models/user';
import { Bank } from '../models/bank';
import { Card } from '../models/card';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private userUrl = `${environment.userService}/users`;
  private adminUrl = `${environment.adminService}/admin`;

  constructor(private http: HttpClient) { }

  // ================= REGISTER =================

  register(user: User): Observable<User> {

    return this.http.post<User>(
      `${this.userUrl}/register`,
      user
    );

  }

  // ================= USER LOGIN =================

  login(login: Login): Observable<LoginResponse> {

    return this.http.post<LoginResponse>(
      `${this.userUrl}/login`,
      login
    ).pipe(

      tap((response) => {

        localStorage.setItem("userId", response.userId.toString());
        localStorage.setItem("username", response.username);
        localStorage.setItem("name", response.name);

        localStorage.setItem("role", "USER");
        localStorage.setItem("isLoggedIn", "true");

      })

    );

  }

  // ================= ADMIN LOGIN =================

  adminLogin(login: Login): Observable<any> {

    return this.http.post<any>(
      `${this.adminUrl}/login`,
      login
    ).pipe(

      tap((response) => {

        localStorage.setItem("adminId", response.adminId.toString());
        localStorage.setItem("username", response.username);
        localStorage.setItem("name", response.adminName);

        localStorage.setItem("role", "ADMIN");
        localStorage.setItem("isLoggedIn", "true");

      })

    );

  }

  // ================= PROFILE =================

  getUserById(userId: number): Observable<User> {

    return this.http.get<User>(
      `${this.userUrl}/${userId}`
    );

  }

  getBankDetails(id: number): Observable<Bank> {

    return this.http.get<Bank>(
      `${this.userUrl}/${id}/bank`
    );

  }

  getCardDetails(id: number): Observable<Card> {

    return this.http.get<Card>(
      `${this.userUrl}/${id}/card`
    );

  }

  // ================= FORGOT PASSWORD =================

  sendOtp(phoneNumber: string): Observable<string> {

    return this.http.post(
      `${this.userUrl}/send-otp`,
      {
        phoneNumber
      },
      {
        responseType: 'text'
      }
    );

  }

  verifyOtp(phoneNumber: string, otp: string): Observable<string> {

    return this.http.post(
      `${this.userUrl}/verify-otp`,
      {
        phoneNumber,
        otp
      },
      {
        responseType: 'text'
      }
    );

  }

  changePassword(phoneNumber: string, newPassword: string): Observable<string> {

    return this.http.put(
      `${this.userUrl}/change-password`,
      {
        phoneNumber,
        newPassword
      },
      {
        responseType: 'text'
      }
    );

  }

  // ================= COMMON =================

  logout() {

    localStorage.clear();

  }

  isLoggedIn(): boolean {

    return localStorage.getItem("isLoggedIn") === "true";

  }

  isAdmin(): boolean {

    return localStorage.getItem("role") === "ADMIN";

  }

  getRole(): string {

    return localStorage.getItem("role") || "";

  }

  getUserId(): number {

    return Number(localStorage.getItem("userId"));

  }

  getUserName(): string {

    return localStorage.getItem("name") || "";

  }

}