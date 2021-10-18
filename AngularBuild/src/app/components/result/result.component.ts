import { Component, OnInit, Input } from '@angular/core';
import { QuizAltService } from 'src/app/services/quiz-alt.service';
import { Answer, Question, Quiz, QuizConfiguration } from 'src/app/models/index';
import { QuizComponent } from '../quiz/quiz.component';
import { Router } from '@angular/router'; 

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css'],
  providers: [QuizAltService]
})

export class ResultComponent implements OnInit 
{

  quizzes: any[] = []; 
  quiz: Quiz = new Quiz(null); 
  mode = 'result'; 
  quizName: string = ''; 
  pager = 
  {
    index: 0,
    size: 1, 
    count: 1
  }; 

  timer: any = null; 
  startTime: Date = new Date(); 
  endTime: Date = new Date(); 
  elapsedTime = '00:00'; 
  duration = ''; 

  config: QuizConfiguration = 
  {
    'allowBack': true, 
    'allowReview': true, 
    'autoMove': false, 
    'duration': 300, 
    'pageSize': 1, 
    'requiredAll': false, 
    'richText': false, 
    'shuffleQuestions': false,
    'shuffleAnswers': false,
    'showClock': false,
    'showPager': true,
    'theme': 'none' 
  }

  constructor(private quizService: QuizAltService) { }

   ngOnInit(): void 
  {
    this.quizzes = this.quizService.getAll(); 
    this.quizName = this.quizzes[0].id; 
    this.loadQuiz(this.quizName);
  }

  loadQuiz(quizName: string)
  {
    this.quizService.get(quizName).subscribe
    (res =>
      {
        this.quiz = new Quiz(res); 
        this.pager.count = this.quiz.questions.length; 
        this.startTime = new Date(); 
        this.elapsedTime = '00:00'; 
        this.timer = setInterval(() => {this.tick(); }, 1000); 
        this.duration = this.parseTime(this.config.duration); 
      }
    ); 
    this.mode = 'quiz'; 
  }

  tick() 
  {
    const now = new Date(); 
    const diff = (now.getTime() - this.startTime.getTime()) / 1000;
    if (diff >= this.config.duration)
    {
      this.onSubmit(); 
    } 
    this.elapsedTime = this.parseTime(diff); 
  }

  parseTime(totalSeconds: number)
  {
    let mins: string | number = Math.floor(totalSeconds / 60); 
    let secs: string | number = Math.round(totalSeconds % 60); 
    mins  = (mins < 10 ? '0' : '') + mins; 
    secs = (secs < 10 ? '0' : '') + secs; 
    return `${mins}:${secs}`; 
  }

  onSubmit()
  {
    let answers = []; 
    this.quiz.questions.forEach(x => answers.push({'quizId': this.quiz.id, 'questionId': x.id, 'answered': x.is_answered})); 

  // Post your data to the server here. answers contain the questionId and the users' answer.
    console.log(this.quiz.questions); 
    this.mode = 'result'; 
    
    return this.quiz.questions; 
  }

  isCorrect(question: Question)
  {
    return question.answers.every(x => x.selected === x.is_correct) ? 'correct' : 'wrong'; 
  }; 


}
