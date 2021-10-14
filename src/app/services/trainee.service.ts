import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TraineeService {

  url: string = "http://localhost:4200"
  
  constructor() { }
}
