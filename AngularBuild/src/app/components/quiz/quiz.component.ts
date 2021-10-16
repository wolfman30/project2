import { Component, OnInit } from '@angular/core';
import { Question } from 'src/app/models/question'; 
import { QUESTIONS } from 'src/app/models/mock.questions';
import { Answer } from 'src/app/models/answer';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent implements OnInit 
{

  questions = QUESTIONS; 

  constructor() { }

  ngOnInit(): void 
  {
  }

}
