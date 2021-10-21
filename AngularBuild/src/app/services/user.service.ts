import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'; 

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  get(url: string)
  {
    return this.http.get(url); 
  }

  getAll()
  {
    return [
      {id: 'data/users.json', name: 'Users'}
    ]; 
  }
}
