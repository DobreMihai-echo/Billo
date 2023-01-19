import { Injectable } from '@angular/core';
const USER_KEY = 'auth-user';
@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() {}


  // public setRoles(roles:[]) {
  //   window.localStorage.setItem("roles",JSON.stringify(roles));
  // }

  // public getRoles(): [] {
  //   return JSON.parse(window.localStorage.getItem("roles"));
  // }

  // public setToken(jwtToken:string) {
  //   localStorage.setItem("jwtToken", jwtToken);
  // }

  // public getToken() {
  //   return localStorage.getItem("jwtToken");
  // }

  // public clear() {
  //   localStorage.clear();
  // }

  // public isLoggedIn() {
  //   return this.getRoles() && this.getToken();
  // }

  clean(): void {
    window.sessionStorage.clear();
  }

  public isLoggesInM = false;

  public saveUser(user: any): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): any {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }

    return {};
  }

  public isLoggedIn(): boolean {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      this.isLoggesInM = true;
      return this.isLoggesInM;
    }

    this.isLoggesInM = false;
    return this.isLoggesInM;
  }

  public logout() {
    this.isLoggesInM = false;
    window.sessionStorage.removeItem(USER_KEY);
  }

  public getRole() {
    return JSON.parse(window.sessionStorage.getItem(USER_KEY) || '{}');
  }

  public getToken() {
    let obj = JSON.parse(window.sessionStorage.getItem(USER_KEY) || '')
    return obj.token;
  }

  public roleMatch(allowedRoles:any): boolean {
    const userRoles: any = this.getRole();
    if (userRoles != null && allowedRoles!=null) {
      if(userRoles.roles[0] === allowedRoles[0]) {
        return true;
      }
    }
    return false;
  }
}
