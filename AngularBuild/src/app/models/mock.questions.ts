import { Question } from './question'; 

export const QUESTIONS: Question[] = 
[
    {
        question: "What is Spring?",
        test_id: 1,
        difficulty: 1,
        correct_answer: 1,
        points: 3, 
        a1: "Spring is basically Hibernate on steroids.",
        a2: `Spring is an application framework written in Java used for Inversion of Control
            through Dependency Injection to alleviate the complex nature of developing 
            enterprise-grade applications.`, 
        a3: "Spring is used to connect a database to a web application. ",
        a4: "Spring automates the entire DevOps pipeline."
      },
      {
        question: "In Aspect Oriented Programming:",
        test_id: 1,
        difficulty: 1,
        correct_answer: 1,
        points: 3, 
        a1: `classes are used as the key component to drive creation of objects, which serve 
		    the purpose of representing concrete ideas or things with states and behaviors.`,
        a2: `dependencies are injected into models. `, 
        a3: `the key components modularize particular transactional concerns which can present across
		    multiple classes known as cross-cutting concerns.`,
        a4: "functions are objects that can serve as input to other functions."
      },
      {
        question: "Which API does Spring need to use to modify the behavior of methods and classes at runtime?",
        test_id: 1,
        difficulty: 1,
        correct_answer: 2,
        points: 3, 
        a1: "Fetch API",
        a2: "RxJS API", 
        a3: "Reflection API",
        a4: "Jackson API"
      },
      {
        question: "Which of the following is NOT an example of a cross-cutting concern:",
        test_id: 1, 
        difficulty: 1, 
        correct_answer: 0, 
        points: 3, 
	      a1: "Instance field - data member of an object",
	      a2: "Database Access - security for a database",
	      a3: "Data Entities - transactions to take place",
	      a4: "Error Handling - handling checked and unchecked exceptions and errors"
      },
      {
        question: "Weaving is:", 
        test_id: 1,
        difficulty: 1, 
        correct_answer: 1,
        points: 3,
        a1: "the chaining of objects",
        a2: `the process of linking aspects with other objects, such as beans, to create
            advised objects.`,
        a3: `a defintiion of which methods in our application advice ought to be injected into 
            or around`,
        a4: `declaration of new interfaces and corresponding implementations in subclasses of any
            advised object such that they are all functionally woven together`
      },
      {
        question: "What is the common goal between Lombok and Spring?", 
        test_id: 1,
        difficulty: 1, 
        correct_answer: 0,
        points: 3,
        a1: `To reduce the amount of boilerplate code by autogenerating the required code elements
            at compiletime.`,
        a2: `To make the workflow of a DevOps team more efficient.`,
        a3: `To abstract away the need to write connective code between a database and Java objects.`,
        a4: `To autogenerate the XML configuration file that configures servlets.`
      },
      {
        question: "Which of the following is used as the front controller in the Spring Web MVC Framework?",
        test_id: 1,
        difficulty: 1,
        correct_answer: 2,
        points: 3, 
        a1: "HttpServlet",
        a2: "HelperServlet", 
        a3: "DispatcherServlet",
        a4: "FrontControllerServlet"
      },
      {
        question: "Which of the following are not endpoints for a Spring Boot Actuator?",
        test_id: 1,
        difficulty: 1,
        correct_answer: 2,
        points: 3, 
        a1: "/health",
        a2: "/logfile", 
        a3: "/wiring",
        a4: "/shutdown"
      },
      {
        question: "In Spring, RestTemplate acts as a:", 
        test_id: 1,
        difficulty: 1, 
        correct_answer: 0,
        points: 3,
        a1: `web client to make requests to web services.`,
        a2: `a front controller to direct HTTP requests to their respective controllers.`,
        a3: `an aspect that controls the cross-cutting concern of security.`,
        a4: `an interface that facilitates the conversion of JSON to Java objects`
      },
      {
        question: ` Which of the following are the two major tools use to improve development workflow
        in Spring Boot applications?`, 
        test_id: 1,
        difficulty: 1, 
        correct_answer: 3,
        points: 3,
        a1: `Hibernate and Jackson`,
        a2: `Disabling caching and manual restarts`,
        a3: `Jenkings and Docker`,
        a4: `Disabling caching and automatic restarts`
      }

]