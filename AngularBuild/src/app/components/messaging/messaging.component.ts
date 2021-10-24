import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-messaging',
  templateUrl: './messaging.component.html',
  styleUrls: ['./messaging.component.css']
})
export class MessagingComponent implements OnInit {

  constructor(private http: HttpClient) { }

  ngOnInit(): void 
  {
  }

  messageLex()
  {
    let url = `http://localhost:8000/bot/converse${sessionId}`; 
    this.http.post(url, {'greeting': "hello"})
  }

}
