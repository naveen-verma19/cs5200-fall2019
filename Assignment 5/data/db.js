const mongoose = require('mongoose');
module.exports = function() {
  mongoose.set('useNewUrlParser', true);
mongoose.set('useFindAndModify', false);
mongoose.set('useCreateIndex', true);
mongoose.set('useUnifiedTopology', true);
  const db = 'mongodb://naveen:naveen19@ds251849.mlab.com:51849/university';
  return mongoose.connect(db)
    .then(() => console.log(`Connected to mongoDB...`));
}