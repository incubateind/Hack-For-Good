const mongoose = require("mongoose");

const BlogmongooseSchema = mongoose.Schema({
     Blog_title:{
         type:String,
         required:true,
         min:2
     },
     Blog_content:{
        type:String,
        required:true,
        min:6
    },
    Blog_amount:{
        type:Number,
        required:true
    },
    Blog_author:{
        type:String,
        required:true,
        min:2
    },
    Blog_wallet:{
        type:String,
        required:true
    },
    Blog_donater:{
        type:Array
    },
})

const BlogmongooseModal = mongoose.model("blog",BlogmongooseSchema)

module.exports = BlogmongooseModal;