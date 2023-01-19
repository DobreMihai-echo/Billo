import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

const AUTH_API = 'http://localhost:8070/api/v1';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {}

  // searchOrders(query: string) {
  //   return this.http.post<{payload: Array<>}>(AUTH_API+'/order/all',{payload: query},
  //   {headers: new HttpHeaders({'Content-Type': 'application/json'})
  // }).pipe(
  //   map(data => data.payload)
  // );
  // }


  
}
