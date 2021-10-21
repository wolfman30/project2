import {Routes} from '@angular/router'; 
import {RegisterComponent} from './components/register/register.component'; 
import {QuizComponent} from './components/quiz/quiz.component'; 
import { ResultComponent } from './components/result/result.component';
import { UserComponent } from './components/user/user.component'; 

export const appRoutes : Routes = 
[
    {path: 'register', component:RegisterComponent}, 
    {path: 'quiz', component:QuizComponent},
    {path: 'result', component:ResultComponent}, 
    {path: 'user', component:UserComponent},
    {path:'', redirectTo:'/register', pathMatch:'full'}
]