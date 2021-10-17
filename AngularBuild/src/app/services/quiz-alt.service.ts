import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'; 

@Injectable(

)

export class QuizAltService 
{
  constructor(private http: HttpClient) { }

  get(url: string)
  {
    return this.http.get(url); 
  }

  getAll()
  {
    return [
        {id: 'data/spring.json', name: 'Spring'},
    ]; 
  }
}
