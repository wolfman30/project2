import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'; 

@Component({
  selector: 'app-test-history',
  templateUrl: './test-history.component.html',
  styleUrls: ['./test-history.component.css']
})
export class TestHistoryComponent implements OnInit 
{
  columnsToDisplay = ['time', 'test-name', 'score-ratio', 'score-percentage']; 
  testHistory: any = sessionStorage.getItem("test-history"); 
  parsedTestHistory: any = JSON.parse(this.testHistory); 
  userData: any = sessionStorage.getItem("userData"); 
  parsedUserData: any = JSON.parse(this.userData); 
  totalQuestions: number = 0; 
  totalCorrectAnswers: number = 0; 
  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goHome()
  {
    this.router.navigate(['/user'])
  }

  getTotalQuestions(): number[]
  {
    let numList = []; 
    for (let test of this.parsedTestHistory)
    {

      numList.push(test.test.testQuestions.length); 
      
    }
    return numList; 
  }

  getTotalCorrectAnswers(): number[]
  {
    let count = 0; 
    let countList = []; 
    for (let test of this.parsedTestHistory)
    {
      let correctAnswerCount = 0; 
      for (let answer of test.answers)
      {
      
        if (answer.testAnswer.isCorrect)
        {
          correctAnswerCount++; 
        }
      }
      count = correctAnswerCount; 
      countList.push(count); 
    }
    return countList; 
  }

  getPercentageScoreList(): string[]
  {
    
    let pointsList = []; 
    let correctAnswerPointsList = []; 
    let percentageList = []; 
    let index = 0; 
    for (let test of this.parsedTestHistory)
    {
      let totalPoints = 0; 
      
      let correctAnswerPoints = 0; 
      for (let question of test.test.testQuestions)
      {
        for (let answer of question.testAnswerList)
        {
          for (let given_answer of test.answers)
          {
            if (given_answer.testAnswer.isCorrect && given_answer.testAnswer.answerText == answer.answerText)
            {
              correctAnswerPoints += question.points; 
            }
          }
        }
      }
      correctAnswerPointsList.push(correctAnswerPoints); 

      for (let question of test.test.testQuestions)
      {
        
        totalPoints += question.points; 
      }
      pointsList.push(totalPoints); 
    }
    for (let num of correctAnswerPointsList)
    {
      let percentage = (num / pointsList[index]) * 100;
      index++; 
      percentageList.push(percentage.toFixed(1)); 
    }
    return percentageList;  
  }
  
}
