import {Routes} from '@angular/router'; 
import {RegisterComponent} from './components/register/register.component'; 
import {QuizComponent} from './components/quiz/quiz.component'; 
import { ResultComponent } from './components/result/result.component';
import { UserComponent } from './components/user/user.component'; 
import { TestHistoryComponent } from './components/test-history/test-history.component';

export const appRoutes : Routes = 
[
    {path: 'register', component:RegisterComponent}, 
    {path: 'quiz', component:QuizComponent},
    {path: 'result', component:ResultComponent}, 
    {path: 'user', component:UserComponent},
    {path: 'test-history', component:TestHistoryComponent},
    {path:'', redirectTo:'/register', pathMatch:'full'}
]