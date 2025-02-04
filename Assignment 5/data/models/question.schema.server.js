const mongoose = require("mongoose");
const TrueFalseSchema = require("./true-false.schema.server.js");
const MultipleChoiceSchema = require("./multiple-choice.schema.server");

const QuestionSchema = mongoose.Schema(
  {
    _id: {
      type: Number,
      required: true
    },
    question: {
      type: String,
      required: true
    },
    points: {
      type: Number,
      required: true,
      min: 0
    },
    questionType: {
      type: String,
      enum: ["MULTIPLE_CHOICE", "TRUE_FALSE"]
    },
    multipleChoice: MultipleChoiceSchema,
    trueFalse: TrueFalseSchema
  },
  { collection: "questions" }
);

module.exports = QuestionSchema;
