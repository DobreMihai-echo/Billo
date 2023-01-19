import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  isToggle:boolean;
  type:string;
  form: any = {
    username: null,
    email: null,
    password: null,
    role:Array
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService, private router:Router) { }

  ngOnInit(): void {
    this.isToggle = false;
    this.type='consumer';
  }

  onSubmit(registerForm:any): void {
    const { username, email, password } = this.form;
    this.form.role[0] = this.type;
    console.log('ROLES',this.form);
    console.log(registerForm.value)
    this.authService.register(this.form).subscribe({
      next: data => {
        console.log("DATA",data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        this.router.navigate(['/login']);

      },
      error: err => {
        this.errorMessage = err.error;
        this.isSignUpFailed = true;
        this.router.navigate(['/login']);
      }
    });
  }

  checkCheckBoxvalue(event:any) {
    console.log("CLICKD",event);
    this.isToggle = !this.isToggle;
    if(this.isToggle === true) {
      this.type='provider';
    } else {
      this.type='consumer';
    }
  }
}
