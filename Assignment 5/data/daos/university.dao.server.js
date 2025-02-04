const mongoose = require("mongoose");

const studentModel = require("../models/student.model.server");
const questionModel = require("../models/question.model.server");
const answerModel = require("../models/answer.model.server");
const quizWidgetModel = require("../models/quiz-widget.model.server");

async function populateDatabase() {
  // Inserting the Students
  console.log("Started populating database...");
  const student1 = {
    _id: 123,
    firstName: "Alice",
    lastName: "Wonderland",
    userName: "alice",
    password: "alice",
    gradYear: 2020,
    scholarship: 15000
  };

  const student2 = {
    _id: 234,
    firstName: "Bob",
    lastName: "Hope",
    userName: "bob",
    password: "bob",
    gradYear: 2021,
    scholarship: 12000
  };

  await createStudent(student1);
  await createStudent(student2);

  console.log("Students Inserted");

  const q1 = {
    _id: 321,
    question: "Is the following schema valid?",
    points: 10,
    questionType: "TRUE_FALSE",
    trueFalse: {
      isTrue: false
    }
  };

  const q2 = {
    _id: 432,
    question: "DAO stands for Dynamic Access Object?",
    points: 10,
    questionType: "TRUE_FALSE",
    trueFalse: {
      isTrue: false
    }
  };

  const q3 = {
    _id: 543,
    question: "What does JPA stand for?",
    points: 10,
    questionType: "MULTIPLE_CHOICE",
    multipleChoice: {
      choices:
        "Java Persistence API,Java Persisted Application,JavaScript Persistence API,JSON Persistent Associations",
      correct: 1
    }
  };

  const q4 = {
    _id: 654,
    question: "What does ORM stand for?",
    points: 10,
    questionType: "MULTIPLE_CHOICE",
    multipleChoice: {
      choices:
        "Object Relational Model,Object Relative Markup,Object Reflexive Model,Object Relational Mapping",
      correct: 4
    }
  };

  var qresponse = await createQuestion(q1);
  qresponse = await createQuestion(q2);

  qresponse = await createQuestion(q3);

  qresponse = await createQuestion(q4);

  console.log("Questions Inserted");

  const alice = await findStudentByFirstName("Alice");

  const bob = await findStudentByFirstName("Bob");

  const question1 = await findQuestionByString(
    "Is the following schema valid?"
  );

  const question2 = await findQuestionByString(
    "DAO stands for Dynamic Access Object?"
  );

  const question3 = await findQuestionByString("What does JPA stand for?");

  const question4 = await findQuestionByString("What does ORM stand for?");

  const answer1 = await answerQuestion({
    _id: 123,
    trueFalseAnswer: true,
    student: alice._id,
    question: question1._id
  });

  await answerQuestion({
    _id: 234,
    trueFalseAnswer: false,
    student: alice._id,
    question: question2._id
  });

  await answerQuestion({
    _id: 345,
    multipleChoiceAnswer: 1,
    student: alice._id,
    question: question3._id
  });

  await answerQuestion({
    _id: 456,
    multipleChoiceAnswer: 2,
    student: alice._id,
    question: question4._id
  });

  await answerQuestion({
    _id: 567,
    trueFalseAnswer: false,
    student: bob._id,
    question: question1._id
  });

  await answerQuestion({
    _id: 678,
    trueFalseAnswer: true,
    student: bob._id,
    question: question2._id
  });

  await answerQuestion({
    _id: 789,
    multipleChoiceAnswer: 3,
    student: bob._id,
    question: question3._id
  });

  await answerQuestion({
    _id: 890,
    multipleChoiceAnswer: 4,
    student: bob._id,
    question: question4._id
  });

  console.log("Answers Inserted");
  console.log("Databse populated!");
}

//Helper methods

async function truncateDatabase() {
  console.log("Truncating database...");

  await studentModel.deleteMany({});
  await questionModel.deleteMany({});
  await answerModel.deleteMany({});
  await quizWidgetModel.deleteMany({});
  console.log("database truncated!");
}


//CREATE METHODS
function createStudent(student) {
  var st = new studentModel(student);
  return st.save();
}

function createQuestion(question) {
  var q = new questionModel(question);
  return q.save();
}

function answerQuestion(answer) {
  var a = new answerModel(answer);
  return a.save();
}

//finders
function findAllStudents() {
  return studentModel.find({});
}
function findStudentById(id) {
  return studentModel.findById(id);
}
function findStudentByFirstName(fname) {
  return studentModel.findOne({ firstName: fname });
}
function findAllQuestions() {
  return questionModel.find({});
}

function findQuestionById(id) {
  return questionModel.findById(id);
}

function findAllAnswers() {
  return answerModel.find({});
}

function findAnswerById(id) {
  return answerModel.findById(id);
}

function findQuestionByString(ques) {
  return questionModel.findOne({ question: ques });
}

function findAnswersByStudent(studentId) {
  return answerModel.find({ student: studentId });
}

function findAnswersByQuestion(questionId) {
  return answerModel.find({ question: questionId });
}

//deletes
function deleteStudent(studentId) {
  return studentModel.findByIdAndDelete(studentId);
}

function deleteQuestion(questionId) {
  return questionModel.findByIdAndDelete(questionId);
}

function deleteAnswer(answerId) {
  return answerModel.findByIdAndDelete(answerId);
}

//updates

function updateStudent(id, student) {
  return studentModel.update({ _id: id }, { $set: student });
}

function updateQuestion(id, question) {
  return questionModel.update({ _id: id }, { $set: question });
}

function updateAnswer(id, answer) {
  return answerModel.update({ _id: id }, { $set: answer });
}

module.exports = {
  populateDatabase,
  truncateDatabase,
  deleteAnswer,
  deleteQuestion,
  deleteStudent,
  findAllStudents,
  findStudentById,
  findStudentByFirstName,
  findAllAnswers,
  findAllQuestions,
  findQuestionById,
  findQuestionByString,
  findAnswerById,
  findAnswersByQuestion,
  findAnswersByStudent,
  updateStudent,
  updateQuestion,
  updateAnswer
};
