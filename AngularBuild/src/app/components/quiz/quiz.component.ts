import { Component, OnInit } from '@angular/core';
import { QuizAltService } from 'src/app/services/quiz-alt.service';
import { Answer, Question, Quiz, QuizConfiguration } from 'src/app/models/index';
import { HttpClient, HttpHeaders } from '@angular/common/http'; 
import { Observable } from 'rxjs';
import { Router } from '@angular/router'; 

/*
@CrossOrigin(origins = "http://localhost:4200")
*/

@Component
({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css'],
  providers: [QuizAltService]
})
 
export class QuizComponent implements OnInit 
{
  
  Tests: any; 
  Answers: any; 
  
  json?: any; 
  quizzes: any[] = []; 
  quiz: Quiz = new Quiz(null); 
  mode = 'quiz'; 
  quizName: string = ''; 
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


  constructor(private quizService: QuizAltService, private http: HttpClient, private router: Router) { }

  ngOnInit(): void 
  {
    this.quizzes = this.quizService.getAll(); 
    this.quizName = this.quizzes[0].id; 
    this.loadQuiz(this.quizName); 
    
    /*
    let request = this.http.post("http://localhost:8000/test", this.Answers)
    request.subscribe((data) =>this.Tests=data)
    */
  }

  url = 'http://localhost:8000/test/submit'; 

  httpOptions = 
  {
    headers: new HttpHeaders
    ({
      'Content-Type': 'application/json', 
      'Access-Control-Allow-Origin': ' null'
    })
  }

//attempting to create a function to connect to Asher's testController 
  postTestAnswers(data: any): Observable<any>
  {
    return this.http.post<any>(this.url, JSON.stringify(data), this.httpOptions)
    .pipe(
      retry(1), 
      catchError(this.errorHandl)
    )
  }

  errorHandl(errorHandl: any): import("rxjs").OperatorFunction<unknown, Answer> {
    throw new Error('Method not implemented.');
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

  get filteredQuestions() 
  {
    return (this.quiz.questions) ? 
    this.quiz.questions.slice(this.pager.index, this.pager.index + this.pager.size) : []; 
  }

  onSelect(question: Question, option: Answer)
  {
    question.is_answered = true; 
    if (question.questionTypeId === 1)
    {
      question.answers.forEach((x) => {if (x.id !== option.id) x.selected = false; })
    }
    
    if (this.config.autoMove)
    {
      this.goTo(this.pager.index + 1); 
    }
  }

  goTo(index: number) 
  {
    if (index >= 0 && index < this.pager.count)
    {
      this.pager.index = index; 
      this.mode = 'quiz'; 
    }
  }

  isAnswered(question: Question)
  {
    return question.answers.find(x => x.selected) ? 'Answered' : 'Not Answered'; 
  }; 

  isCorrect(question: Question)
  {
    return question.answers.every(x => x.selected === x.is_correct) ? 'correct' : 'wrong'; 
  }; 

  onSubmit()
  {
    let answers: any[] = []; 
    this.quiz.questions.forEach(x => answers.push(x.id)); 

    let testSubmission = {'userId': 7, 'testId': 4, 'answers': [42, 43]}

  // Post your data to the server here. answers contain the questionId and the users' answer.
    
    //this.http.post(this.url, testSubmission, this.httpOptions).toPromise();  

    
    localStorage.setItem('completed-quiz', JSON.stringify(this.quiz.questions)); 

    this.mode = 'result'; 

    this.router.navigate(["/result"]); 
    
    return answers; 
  }
 
}

function retry(arg0: number): import("rxjs").OperatorFunction<any, unknown> {
  throw new Error('Function not implemented.');
}

function catchError(errorHandl: any): import("rxjs").OperatorFunction<unknown, Answer> {
  throw new Error('Function not implemented.');
}

