export interface Trainee
{
    id?: number;
    firstName: string;
    lastName: string;
    userName: string;
    email: string;
    password?: string;
    class_id?: number;
    is_teacher?: boolean;
    date_joined?: any;
}
