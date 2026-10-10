import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../environments/environment';

@Injectable({
  providedIn:'root'
})
export class TransactionService {

  private apiUrl=`${environment.financeService}/api/purchases`;

  constructor(private http:HttpClient){}

  getAllTransactions():Observable<any>{

    return this.http.get<any>(this.apiUrl);

  }

}