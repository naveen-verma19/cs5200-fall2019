const moongose = require('mongoose');
const studentSchema = require('./student.schema.server');
const studentModel = new moongose.model('StudentModel', studentSchema);
module.exports = studentModel;