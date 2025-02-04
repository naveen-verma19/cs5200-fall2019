const mongoose = require('mongoose');
const AnswerSchema = require('./answer.schema.server');
const AnswerModel = mongoose.model('AnswerModel', AnswerSchema);

module.exports = AnswerModel;