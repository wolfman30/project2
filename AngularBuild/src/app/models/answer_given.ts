import * as internal from "stream";

export class AnswersGiven
{
    id: number;  
    test_history_id: number; 
    answer_id: number; 

    constructor(data: any)
    {
        data = data || {}; 
        this.id = data.id; 
        this.test_history_id = data.test_history_id; 
        this.answer_id = data.answer_id; 
    }
}