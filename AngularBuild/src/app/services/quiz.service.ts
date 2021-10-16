import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'; 

@Injectable({
  providedIn: 'root'
})
export class QuizService {
  
//---------Properties-----------
readonly rootURL = 'http://localhost:4200'; 

//--------Helper Methods---------
  constructor(private http : HttpClient) {}

  insertUser(firstName: string, lastName: string, email: string)
  {
    let body =
    {
      FirstName: firstName, 
      LastName: lastName, 
      Email: email, 
    }
    return this.http.post(this.rootURL + '/api/InsertUser', body); 
  }
}
