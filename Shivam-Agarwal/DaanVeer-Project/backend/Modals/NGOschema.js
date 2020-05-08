const mongoose = require("mongoose");

const NGOmongooseSchema = mongoose.Schema({
     ngo_name:{
         type:String,
         required:true,
         min:2
     },
     ngo_email:{
        type:String,
        required:true,
        min:6
    },
    ngo_password:{
        type:String,
        required:true,
        min:6
    },
    refreshtoken:{
        type:String
    },
    ngo_bio:{
        type:String
    },
    is_ngo:{
        type:Boolean,
        required:true
    }
})

const NGOmongooseModal = mongoose.model("NGO's",NGOmongooseSchema)

module.exports = NGOmongooseModal;