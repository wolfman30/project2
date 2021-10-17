export class Answer 
{
    id: number; 
    content: string; 
    question_id: number; 
    is_correct: boolean;
    selected: boolean = false; 

    constructor(data: any) 
    {
        data = data || {}; 
        this.id = data.id; 
        this.content = data.content; 
        this.question_id = data.question_id; 
        this.is_correct = data.is_correct; 
        
    }; 
}