const mongoose = require("mongoose");

const usermongooseSchema = mongoose.Schema({
     user_name:{
         type:String,
         required:true,
         min:2
     },
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
    refreshtoken:{
        type:String
    },
    user_mobile_no:{
        type:Number,
        required:true,
    }
})

const usermongooseModal = mongoose.model("users",usermongooseSchema)

module.exports = usermongooseModal;