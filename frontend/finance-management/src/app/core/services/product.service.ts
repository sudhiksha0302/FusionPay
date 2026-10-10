import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../environments/environment';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiUrl = `${environment.productService}/api/products`;

  constructor(private http: HttpClient) {}

  getAllProducts(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }

  getProductById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  searchProducts(name: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/search?name=${name}`);
  }

  getByCategory(category: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/category/${category}`);
  }

  purchase(request: any): Observable<string> {
  return this.http.post(`${this.apiUrl}/purchase`, request, {
    responseType: 'text'
  });
}

}