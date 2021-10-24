import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'; 

@Injectable({
  providedIn: 'root'
})
export class MessagingService {

  constructor(private http: HttpClient) { }

  lexHttpOptions = 
  {
    headers: new HttpHeaders
    (
      {
        'Content-type': 'application/json', 
        'SessionId': '75983749823'
      }
    )
  }
  messageLex()
  {
    let url = `http://localhost:8000/bot/converse/75983749823`; 
    this.http.post(url, {'greeting': "hello"}, this.lexHttpOptions).toPromise(); 
  }
}
