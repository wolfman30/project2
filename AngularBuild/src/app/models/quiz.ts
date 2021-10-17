import { QuizConfiguration } from './quiz-configuration'; 
import { Question } from './questionClass'; 

export class Quiz 
{
    id: number = 0; 
    subject: string = ''; 
    test_name: string = ''; 
    config: QuizConfiguration = new QuizConfiguration(null); 
    questions: Question[] = []; 

    constructor(data: any)
    {
        if (data)
        {
            this.id = data.id; 
            this.subject = data.subject; 
            this.test_name = data.test_name; 
            this.config = new QuizConfiguration(data.config); 
            this.questions = []; 
            data.questions.forEach( (query:any) =>
            {
                this.questions.push(new Question(query)); 
            }); 
        }
    }
}