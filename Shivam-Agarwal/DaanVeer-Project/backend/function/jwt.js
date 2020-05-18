const jwt = require("jsonwebtoken");
const cookie = require("cookie-parser")
const accesstoken = (id) =>{
     return auth = jwt.sign({id},process.env.ACCESS_TOKEN,{
         expiresIn:'15m'
     })
}

const refreshtoken = (id) =>{
    return jwt.sign({id},process.env.REFRESH_TOKEN,{
        expiresIn:'7d'
    })
}

const sendaccesstoken1 = (res,req,accesstoken) =>{
    res.status(201).send(
        accesstoken
    )   
}
const sendaccesstoken0 = (res,req,accesstoken) =>{
    res.send(
        accesstoken
    )   
}

const sendrefreshtoken = (res,refreshtoken) =>{
    res.cookie("refreshtoken",refreshtoken,{
        httpOnly:false,
        path:"/"
    })
    console.log("hello")
}
module.exports = {accesstoken,refreshtoken,sendaccesstoken0,sendaccesstoken1,sendrefreshtoken}