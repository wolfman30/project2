export class User
{
    id: number; 
    firstName: string; 
    lastName: string;  
    userName: string;  
    password: string;  
    email: string; 

    constructor(data: any)
    {
        data = data || {}; 
        this.id = data.id; 
        this.firstName = data.firstName; 
        this.lastName = data.lastName; 
        this.userName = data.userName; 
        this.password = data.password; 
        this.email = data.email; 
    }
}