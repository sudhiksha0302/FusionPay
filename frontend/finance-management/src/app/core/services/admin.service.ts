import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { tap } from 'rxjs/operators';

import { environment } from '../../../environments/environment';

import { Admin } from '../models/admin';
import { AdminLogin } from '../models/admin-login';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private adminUrl = `${environment.adminService}/admin`;

  constructor(private http: HttpClient) { }

  login(data: AdminLogin): Observable<Admin> {

    return this.http.post<Admin>(
      `${this.adminUrl}/login`,
      data
    ).pipe(

      tap((admin) => {

        localStorage.setItem(
          "adminId",
          admin.adminId.toString());

        localStorage.setItem(
          "adminName",
          admin.adminName);

        localStorage.setItem(
          "isAdmin",
          "true");

      })

    );

  }

  logout() {

    localStorage.removeItem("adminId");

    localStorage.removeItem("adminName");

    localStorage.removeItem("isAdmin");

  }

  isAdminLoggedIn(): boolean {

    return localStorage.getItem("isAdmin") === "true";

  }

  getAllUsers(): Observable<any[]> {

    return this.http.get<any[]>(
      `${this.adminUrl}/users`);

  }

  activateUser(id:number):Observable<any>{

    return this.http.put(
      `${this.adminUrl}/users/activate/${id}`,
      {});

  }

  getAllProducts():Observable<any[]>{

    return this.http.get<any[]>(
      `${this.adminUrl}/products`);

  }

  getAllPurchases():Observable<any[]>{

    return this.http.get<any[]>(
      `${this.adminUrl}/purchases`);

  }

}