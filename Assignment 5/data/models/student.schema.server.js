const mongoose = require('mongoose');

const studentSchema = new mongoose.Schema({
    _id: Number,
    firstName: {
        type:String,
        required: true,
        maxlength: 50
    },
    lastName: {
        type:String,
        required: true,
        maxlength: 50
    },
    userName: {
        type:String,
        required: true,
        maxlength: 50
    },
    password: {
        type:String,
        required: true,
        maxlength: 1024
    },
    gradYear: {
        type:Number,
        required: true,
        min: 1970,
        max: 2026
    },
    scholarship: {
        type:Number,
        required: true,
        min: 0,
    }
}, { collection: 'students' })

module.exports = studentSchema;