import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service'; 
import { MessagingService } from 'src/app/services/messaging.service'; 
import { User } from 'src/app/models/userClass'; 
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router'; 
import users from 'src/data/users.json'; 


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'], 
  providers: [UserService, MessagingService]
})
export class UserComponent implements OnInit 
{
  count: number = 0; 
  display: boolean = false; 
  userMessage = new FormControl("", [Validators.required])
  lexResponse: any = sessionStorage.getItem("lexMessage"); 
  parsedLexResponse: any = JSON.parse(this.lexResponse);
  lexResponseList: any[] = [];
  userMessageList: any[] = [];   
  loading: boolean = false; 
  userData: any = sessionStorage.getItem("userData"); 
  parsedUserData: any = JSON.parse(this.userData); 
  testHistory: any; 
  usersContainer: any; 
  userName: string = ''; 
  user: User = new User(null); 
  public userList:{id:number, firstName:string, lastName: string}[] = users; 
  httpOptions = 
  {
    headers: new HttpHeaders
    ({
      'Content-Type': 'text/plain'
    })
  }

  lexHttpOptions = 
  {
    headers: new HttpHeaders
    (
      {
        'Content-type': 'application/json'
      }
    )
  }

  lex_url = "http://localhost:8000/bot/converse/75983749823"; 
  insert_user_url = 'http://localhost:8000/user/create-or-update'; 
  get_login_creds_url =  'http://localhost:8000/user/login-attempt'; 

  constructor(private userService: UserService, private http:HttpClient, 
              private router: Router, private messageService: MessagingService) { }

  ngOnInit(): void 
  {
    this.usersContainer = this.userService.getAll(); 
    this.userName = this.usersContainer[0].id; 
    this.loadUsers(this.userName);
  }

  talkToLex(message: string)
  {
    if (sessionStorage.getItem("lexResponse") == null)
      this.count = 0; 

    this.count++; 
    let userMessage: any; 

    if (this.count === 1)
      userMessage = {"message": message}; 
    else
      userMessage = {"sessionId": this.parsedLexResponse.sessionId, "message": message}; 

    this.userMessageList.push(userMessage); 
    let url = "http://localhost:8000/bot/converse/"; 
    this.http.post(url, userMessage, this.lexHttpOptions)
      .subscribe
      (
        async (response) =>
        {
          
          sessionStorage.setItem("lexMessage", JSON.stringify(response));
          this.lexResponse = sessionStorage.getItem("lexMessage"); 
          this.parsedLexResponse = JSON.parse(this.lexResponse);
          this.display = true; 
          
        }
      );
       
      
  }

  loadUsers(userName: string)
  {
    this.userService.get(userName).subscribe
    (
      res =>
        {
          this.user = new User(res);
          console.log(this.user);  
        }
    )
  }

  takeTest()
  {
    this.router.navigate(['/quiz']); 
  }

  getTestHistory()
  {
    
    this.loading = true; 
    let url = `http://localhost:8000/test/get_history/user/${this.parsedUserData.id}`; 
    this.http.get(url, this.httpOptions).subscribe
    (
      (response) =>
      {
        sessionStorage.setItem("test-history", JSON.stringify(response));
        this.testHistory = sessionStorage.getItem("test-history");  
        this.router.navigate(['/test-history']); 
      }
    ); 
  }

}
