const mongoose = require("mongoose");

const MCQSchema = new mongoose.Schema({
  choices: {
    type: String,
    required: true,
    minlength: 5
  },
  correct: {
    type: Number,
    required: true,
    min: 1,
    max: 10
  }
});

module.exports = MCQSchema;
