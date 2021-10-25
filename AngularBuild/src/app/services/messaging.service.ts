import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'; 

@Injectable({
  providedIn: 'root'
})
export class MessagingService {
lexResponse: any; 
  constructor(private http: HttpClient) { }

  lexHttpOptions = 
  {
    headers: new HttpHeaders
    (
      {
        'Content-type': 'application/json'
      }
    )
  }
  messageLex()
  {
    let url = "http://localhost:8000/bot/converse/"; 
    this.http.post(url, {"userMessages": ["hello"]}, this.lexHttpOptions).subscribe
    (
      (response) =>
      {
        sessionStorage.setItem("lex-response", JSON.stringify(response)); 
        this.lexResponse = sessionStorage.getItem('lex-response'); 
        return this.lexResponse; 
      }
    ); 
  }
}
