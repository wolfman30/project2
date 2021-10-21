import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { User } from 'src/app/models/user';
import { QuizService } from 'src/app/services/quiz.service'; 
import { HttpClient, HttpHeaders } from '@angular/common/http'; 
 

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  firstName = new FormControl('', [Validators.required]);
  lastName = new FormControl('', [Validators.required]);
  userName = new FormControl("", [Validators.required]);
  email = new FormControl('', [Validators.required, Validators.email]); 
  password = new FormControl('', [Validators.required, 
                                  Validators.minLength(5)]); 
                                  //Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]+$')
  login_creds_url =  'http://localhost:8000/user/login-attempt'; 

  httpOptions = 
  {
    headers: new HttpHeaders
    ({
      'Content-Type': 'application/json'
    })
  }

  constructor(private quizService: QuizService, private http: HttpClient) { }

  ngOnInit(): void {
  }

  register()
  {
    const newTrainee: User =
    {
      firstName: this.firstName.value,
      lastName: this.lastName.value,
      userName: this.userName.value,
      email: this.email.value,
      password: this.password.value
    };
    console.log(newTrainee);
  }

  login() 
  {
    this.http.post(this.login_creds_url, {"username": this.userName.value, "password": this.password.value}, this.httpOptions).subscribe
    (
      (response) =>
        {
          console.log(response)
        }
    ); 
  }

}
