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
  
  question : Question = 
  {
    question: "What is Spring?",
    test_id: 1,
    difficulty: 1,
    correct_answer: 1,
    points: 3, 
    a1: "Spring is basically Hibernate on steroids.",
    a2: `Spring is an application framework written in Java used for Inversion of Control
		through Dependency Injection to alleviate the complex nature of developing 
		enterprise-grade applications.`, 
    a3: "Spring is used to connect a database to a web application. ",
    a4: "Spring automates the entire DevOps pipeline."
  }
  constructor() { }

  ngOnInit(): void {
  }

}
