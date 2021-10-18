export class TestHistory 
{
    id: number; 
    test_id: number; 
    user_id: number; 
    date_taken: Date = new Date(); 

    constructor(data: any)
    {
        data = data || {}; 
        this.id = data.id; 
        this.test_id = data.test_id; 
        this.user_id = data.user_id; 
    }

}