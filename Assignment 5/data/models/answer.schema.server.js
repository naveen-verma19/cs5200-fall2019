const mongoose = require("mongoose");

const AnswerSchema = mongoose.Schema(
  {
    _id: { 
      type: Number,
       required: true },
    trueFalseAnswer: {
      type: Boolean,
    },
    multipleChoiceAnswer: {
      type: Number,
      min: 1,
      max: 10
    },
    student: {
      type: Number,
      ref: "StudentModel"
    },
    question: {
      type: Number,
      ref: "QuestionModel"
    }
  },
  { collection: "answers" }
);

module.exports = AnswerSchema;
