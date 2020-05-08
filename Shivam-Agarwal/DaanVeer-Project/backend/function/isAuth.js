const jwt = require('jsonwebtoken')

const isAuth = (req)=>{
        const author = req.headers['authorization']
        if(author){
            try{
                const token = author.split(' ')[1];
                console.log(token)
                const userID = jwt.verify(token,process.env.ACCESS_TOKEN)
                return userID.id
            }catch(err){
                console.log(err)
            }
        }
}
module.exports={
    isAuth
}