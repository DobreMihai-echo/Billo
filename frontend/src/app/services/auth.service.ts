import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StorageService } from './storage.service';

const AUTH_API = 'http://localhost:8070/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json','No-Auth':'True'})
};
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient,
    private storageService: StorageService) {}

  login(username: string, password: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'signin',
      {
        username,
        password,
      },
      httpOptions
    );
  }

  register(registerForm:any): Observable<any> {
    return this.http.post(
      AUTH_API + 'signup',
      registerForm,
      httpOptions
    );
  }
}
