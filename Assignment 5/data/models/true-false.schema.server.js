const mongoose = require('mongoose');

const trueFalseSchema = mongoose.Schema({
    isTrue: {
        type:Boolean,
        required:true
    }
});

module.exports=trueFalseSchema;