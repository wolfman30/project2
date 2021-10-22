import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router'; 
import { AppComponent } from './app.component';
import { QuestionComponent } from './components/question/question.component';
import { RegisterComponent } from './components/register/register.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { QuizComponent } from './components/quiz/quiz.component';
import { ResultComponent } from './components/result/result.component';
import { appRoutes } from './routes';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; 
import { AngularMaterialModule } from './modules/angular-material.module'; 
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { UserComponent } from './components/user/user.component';
import { MessagingComponent } from './components/messaging/messaging.component'; 
import { userList } from './models/userList'; 

@NgModule({
  declarations: [
    AppComponent,
    QuestionComponent,
    RegisterComponent,
    NavbarComponent,
    QuizComponent,
    ResultComponent,
    UserComponent,
    MessagingComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    BrowserAnimationsModule,
    AngularMaterialModule,
    FormsModule, 
    ReactiveFormsModule, 
    HttpClientModule
  ],
  providers: [
    { provide: "USERS", useValue: userList }
   ],
  bootstrap: [AppComponent]
})
export class AppModule { }
