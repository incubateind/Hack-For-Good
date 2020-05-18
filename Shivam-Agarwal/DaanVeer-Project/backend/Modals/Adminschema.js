const mongoose = require("mongoose");

const adminmongooseSchema = mongoose.Schema({
     user_email:{
        type:String,
        required:true,
        min:6
    },
    user_password:{
        type:String,
        required:true,
        min:6
    },

})

const adminmongooseModal = mongoose.model("admin",adminmongooseSchema)

module.exports = adminmongooseModal;