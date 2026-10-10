import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PurchaseService {

  private apiUrl = `${environment.productService}/api/products`;

  constructor(private http: HttpClient) {}

  purchase(request: any): Observable<string> {

    return this.http.post(
      `${this.apiUrl}/purchase`,
      request,
      {
        responseType: 'text' as 'text'
      }
    );

  }

}