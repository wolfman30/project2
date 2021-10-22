import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { User } from 'src/app/models/user';
import { QuizService } from 'src/app/services/quiz.service'; 
import { HttpClient, HttpHeaders } from '@angular/common/http'; 
import { ɵparseCookieValue } from '@angular/common';
import { Router } from '@angular/router'; 
import users from 'src/data/users.json'; 


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit 
{
  newId: number = 0; 
  newDatum: any; 
  firstName = new FormControl('', [Validators.required]);
  lastName = new FormControl('', [Validators.required]);
  userName = new FormControl("", [Validators.required]);
  email = new FormControl('', [Validators.required, Validators.email]); 
  password = new FormControl('', [Validators.required, 
                                  Validators.minLength(5)]); 
                                  //Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]+$')
  login_creds_url =  'http://localhost:8000/user/login-attempt'; 
  register_url = 'http://localhost:8000/user/create-or-update'; 

  httpOptions = 
  {
    headers: new HttpHeaders
    ({
      'Content-Type': 'application/json'
    })
  }

  constructor(private quizService: QuizService, 
              private http: HttpClient, 
              private router: Router, 
              @Inject('USERS') public userList: any[]) { }

  ngOnInit(): void {
  }

  login() 
  {
    this.http.post(this.login_creds_url, {"username": this.userName.value, "password": this.password.value}, this.httpOptions).subscribe
    (
      (response) =>
        {
          sessionStorage.setItem("userData", JSON.stringify(response)); 
          this.router.navigate(["/user"]); 
        }
    ); 
  }

  register()
  {
    this.newId = this.userList[this.userList.length - 1].id++; 
    this.firstName.value; 
    this.lastName.value; 
    this.newDatum = {"id": this.newId, "firstName": this.firstName, "lastName": this.lastName };
    users.push(this.newDatum); 

    //this.http.post(this.register_url, JSON.stringify({"username": this.userName.value, "password": this.password.value, "email": this.email.value})); 
  }

}
