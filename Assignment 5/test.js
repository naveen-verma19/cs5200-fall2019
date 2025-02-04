require('./data/db')();// initialising mongo connection
const dao=require('./data/daos/university.dao.server');


    startOperations=async ()=>{
       await dao.truncateDatabase();    
       await dao.populateDatabase();
       await testStudentsInitialCount();
       await testQuestionsInitialCount();
       await testAnswersInitialCount();
       await testDeleteAnswer();
       await testDeleteQuestion();
       await testDeleteStudent();  
    }

 async function testStudentsInitialCount(){
    const allStudents= await dao.findAllStudents();
    console.log("\nNumber of Students:"+allStudents.length);
 }  

 async function testQuestionsInitialCount(){
    const allQuestions= await dao.findAllQuestions();
    console.log("\nNumber of Questions:"+allQuestions.length);
 } 

 async function testAnswersInitialCount(){
    const allAnswers= await dao.findAllAnswers();
    console.log("\nNumber of Answers:"+allAnswers.length);
 } 

 async function testDeleteAnswer(){
     console.log("\n-----------testing-testDeleteAnswer--------------")
    const bob = await dao.findStudentByFirstName("Bob");
    const question4 = await dao.findQuestionByString("What does ORM stand for?");

   const answers=await dao.findAnswersByQuestion(question4._id);
  const answerByBob=answers.filter(x=>x.student==bob._id)[0];
   await dao.deleteAnswer(answerByBob._id);
   console.log("deleted bob's answer:\n"+answerByBob);

   const currentAnswers=await dao.findAllAnswers();
   console.log("total answers updated to:"+currentAnswers.length);

   const answersByBob=await dao.findAnswersByStudent(bob._id);
   console.log("Bob's total answers updated to:"+answersByBob.length);
   console.log("\n-----------testing-finished--------------")
 }

 async function testDeleteQuestion(){
    console.log("\n-----------testing-testDeleteQuestion--------------")

    const question1 = await dao.findQuestionByString(
        "Is the following schema valid?"
      );
    await dao.deleteQuestion(question1._id);
    console.log("Deleted question:\n"+question1);

    const allQuestions= await dao.findAllQuestions();
    console.log("\nUpdated Number of Questions:"+allQuestions.length);
    console.log("\n-----------testing-finished--------------")

 }

 async function testDeleteStudent(){
    console.log("\n-----------testing-testDeleteStudent--------------");
    const bob = await dao.findStudentByFirstName("Bob");
    await dao.deleteStudent(bob._id);
    console.log("deleting\n:"+bob);

    const updated= await dao.findAllStudents();
    console.log("updated students:"+updated.length);

    console.log("\n-----------testing-finished--------------");

 }
 

    startOperations();

   


