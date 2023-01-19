import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { StorageService } from '../services/storage.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css'],
})
export class NavigationComponent implements OnInit {
  constructor(private authService: StorageService,private router: Router) {}
  ngOnInit(): void {
  }

  


  public isLoggedIn() {
    console.log(this.authService.isLoggedIn());
    return this.authService.isLoggedIn();
  }

  public logout() {
    this.authService.clean();
    this.router.navigate(['/login'])
  }

  public roleMatch(allowedRoles:any):boolean {
      return this.authService.roleMatch(allowedRoles);
  }
}
