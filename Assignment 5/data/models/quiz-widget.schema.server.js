const mongoose = require('mongoose');

const QuizWidgetSchema = new mongoose.Schema({
    questions: [{
        type: mongoose.Schema.Types.ObjectId,
        ref: 'QuestionModel'
    }]
}, { collection: 'question-widgets' });

module.exports = QuizWidgetSchema;