import { Answer } from './answerClass'; 

export class Question
{
    id: number; 
    content: string; 
    test_id: number; 
    questionTypeId: number; 
    difficulty: number;  
    answers: Answer[]; 
    points: number; 
    is_answered: boolean; 

    constructor(data: any)
    {
        data = data || {}; 
        this.id = data.id; 
        this.content = data.content; 
        this.test_id = data.test_id; 
        this.questionTypeId = data.questionTypeId; 
        this.difficulty = data.difficulty; 
        this.answers = []; 
        data.answers.forEach( (ans: any) => 
            {
                this.answers.push(new Answer(ans));
            }); 
        this.points = data.points; 
        this.is_answered = data.is_answered;  
    }
    
    
}