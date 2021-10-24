import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { User } from 'src/app/models/user';
import { QuizService } from 'src/app/services/quiz.service'; 
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http'; 
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
  responseStatus: number = 0; 
  responseText: string = ''; 
  response: any; 
  newDatum: any; 
  registered_user: any; 
  firstName = new FormControl("", [Validators.required]);
  lastName = new FormControl("", [Validators.required]);
  register_userName = new FormControl("", [Validators.required]);
  login_userName = new FormControl("", [Validators.required]);
  email = new FormControl("", [Validators.required, Validators.email]); 
  login_password = new FormControl("", [Validators.required, 
                                  Validators.minLength(5)]); 
  register_password = new FormControl("", [Validators.required, 
                                    Validators.minLength(5)]); 
                                  //Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]+$')
  login_creds_url =  'http://localhost:8000/user/login-attempt'; 

  register_url = 'http://localhost:8000/user/create-or-update'; 

  httpOptions = 
  {
    headers: new HttpHeaders
    ({
      'Content-Type': 'application/json', 
      'Accept': 'application/json',
      'Access-Control-Allow-Origin': '*'
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
    this.http.post(this.login_creds_url, {"username": this.login_userName.value, "password": this.login_password.value}, this.httpOptions).subscribe
    (
      (response) =>
        {
          this.response = response; 
          sessionStorage.setItem("userData", JSON.stringify(response)); 
          this.router.navigate(["/user"]); 
        },
      (error) =>
      {
        this.response="error"; 
      }
    ); 
  }

  register()
  {

    this.registered_user = {
                      "firstName": this.firstName.value, "lastName": this.lastName.value, 
                      "password": this.register_password.value, 
                      "email": this.email.value, "username": this.register_userName.value 
                          }

    this.http.post(this.register_url, this.registered_user, 
                  this.httpOptions).toPromise()
                  .then
                  (
                    (data: any) =>
                      {
                        this.response = 'successful-registration'; 
                        console.log(data)
                      }
                  ).catch(
                    (error: HttpErrorResponse) =>
                    {
                      this.response = 'registration-error'; 
                      console.log(error, "409 conflict"); 
                    }
                  )
  }

}
