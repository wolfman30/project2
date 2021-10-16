export class QuizConfiguration
{
    allowBack: boolean; 
    allowReview: boolean; 
    autoMove: boolean; 
    duration: number; 
    pageSize: number; 
    requiredAll: boolean; 
    richText: boolean; 
    shuffledQuestions: boolean; 
    shuffleOptions: boolean; 
    showClock: boolean; 
    showPager: boolean; 
    theme?: string; 

    constructor(data: any)
    {
        data = data || {}; 
        this.allowBack = data.allowBack; 
        this.allowReview = data.allowReview; 
        this.autoMove = data.autoMove; 
        this.duration = data.duration; 
        this.pageSize = data.pageSize; 
        this.requiredAll = data.requiredAll; 
        this.richText = data.richText; 
        this.shuffledQuestions = data.shuffledQuestions; 
        this.shuffleOptions = data.shuffleOptions;
        this.showClock = data.showClock; 
        this.showPager = data.showPager;  
    }
}