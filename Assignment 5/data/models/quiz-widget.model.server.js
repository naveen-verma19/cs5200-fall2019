const mongoose = require('mongoose');
const QuizWidgetSchema = require('./quiz-widget.schema.server');
const QuizWidgetModel = mongoose.model('quiz-widgetModel', QuizWidgetSchema);

module.exports = QuizWidgetModel;