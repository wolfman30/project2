import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Trainee } from 'src/app/models/trainee';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  firstName = new FormControl('', [Validators.required]);
  lastName = new FormControl('', [Validators.required]);
  userName = new FormControl("", [Validators.required]);
  email = new FormControl('', [Validators.required, Validators.email])

  constructor() { }

  ngOnInit(): void {
  }

  register()
  {
    const newTrainee: Trainee =
    {
      firstName: this.firstName.value,
      lastName: this.lastName.value,
      userName: this.userName.value,
      email: this.email.value
    };
    console.log(newTrainee);
  }

}
