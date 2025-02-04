const mongoose = require('mongoose');
const QuestionSchema = require('./question.schema.server');

const questionModel = mongoose.model('QuestionModel', QuestionSchema);

module.exports = questionModel;
