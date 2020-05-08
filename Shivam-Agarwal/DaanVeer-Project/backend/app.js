const express = require("express");
const cors = require("cors");
const dotenv = require("dotenv");
const mongoose = require("mongoose");
const cookie = require("cookie-parser")

dotenv.config();
const app = express();

app.use(express.json());
app.use(cors({
    origin: 'http://localhost:3000', credentials: true
}));
app.use(cookie())


app.use("/",require("./Routers/initial"));


app.use(function(req, res, next) {
    res.header('Access-Control-Allow-Credentials', true);
    res.header('Access-Control-Allow-Origin', req.headers.origin);
    res.header('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE,UPDATE,OPTIONS');
    res.header('Access-Control-Allow-Headers', 'X-Requested-With, X-HTTP-Method-Override, Content-Type, Accept');
    next();
  });

app.listen(process.env.LOCALHOST,()=>{
    console.log("your server is running at 4000");
})

mongoose.connect(process.env.MONGOOSE_CONNECT,{useNewUrlParser:true,useUnifiedTopology:true},(err)=>{
       if(err)console.log(err);
       else console.log("Mongoose server is ready");
})