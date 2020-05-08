const express = require("express");
const Route = express.Router();
const UserMongoosemodal = require("../Modals/Userschema");
const NGOMongoosemodal = require("../Modals/NGOschema");
const BlogMongoosemodal = require("../Modals/Blogschema");
const Joi = require("@hapi/joi");
const bcrypt = require("bcryptjs");
const Jwt = require("jsonwebtoken");
const {accesstoken,refreshtoken,sendaccesstoken0,sendaccesstoken1,sendrefreshtoken} = require("../function/jwt");
const {isAuth} = require('../function/isAuth')


const user_schema = Joi.object({
    user_name: Joi.string().required().min(6),
    user_email: Joi.string().required().min(6).email(),
    user_password: Joi.string().required().min(6),
    user_mobile_no : Joi.string().required().min(10).max(11)
})

const ngo_schema = Joi.object({
    ngo_name: Joi.string().required().min(6),
    ngo_email: Joi.string().required().min(6).email(),
    ngo_password: Joi.string().required().min(6),
    ngo_bio : Joi.string().required(),
    is_ngo: Joi.boolean().required()
})


Route.post("/userregister",async (req,res)=>{
    const {user_name,user_email,user_password,user_mobile_no} = req.body;
    const validation = user_schema.validate(req.body)

    if(validation.error){
        res.status(203).send(validation.error.details[0].message)
    }
    else{
        const email_duplicate = await UserMongoosemodal.findOne({user_email:user_email});
        if(email_duplicate){
            res.status(203).send("email already exist")
                }
        else{
            
            const salt = await bcrypt.genSalt(10);
            const hash = await bcrypt.hash(user_password,salt);
            
            const new_user = new UserMongoosemodal({
                user_name,
                user_email,
                user_password:hash,
                user_mobile_no:user_mobile_no,
                refreshtoken:null
            })
            try{
                   const savepost = await new_user.save();
                   res.send(savepost._id)
            }catch(error){
                console.log(error)
                res.status(203).send("Something wrong occured")
            }
        }   
    }
})


Route.post("/ngoregister",async (req,res)=>{
    const {ngo_name,ngo_email,ngo_password,ngo_bio} = req.body;
    const validation = ngo_schema.validate(req.body)

    if(validation.error){
        res.status(203).send(validation.error.details[0].message)
    }
    else{
        const email_duplicate = await NGOMongoosemodal.findOne({ngo_email:ngo_email});
        if(email_duplicate){
            res.status(203).send("email already exist")
                }
        else{
            
            const salt = await bcrypt.genSalt(10);
            const hash = await bcrypt.hash(ngo_password,salt);
            
            const new_ngo = new NGOMongoosemodal({
                ngo_name,
                ngo_email,
                ngo_password:hash,
                ngo_bio,
                refreshtoken:null,
                is_ngo:true
            })
            try{
                   const savepost = await new_ngo.save();
                   res.send(savepost._id)
            }catch(error){
                console.log(error)
                res.status(203).send("Something wrong occured")
            }
        }   
    }
})



Route.post("/login",async(req,res)=>{
    const {email,password} = req.body
    const got_useremail = await UserMongoosemodal.findOne({user_email:email})
    const got_ngoemail = await NGOMongoosemodal.findOne({ngo_email:email})

    if(got_useremail){
          const password_compare = await bcrypt.compare(password,got_useremail.user_password);
          if(password_compare){
              try{
                 const access = accesstoken(got_useremail._id)
                 const refresh = refreshtoken(got_useremail._id)
                //  store refresh in db
                const set= await UserMongoosemodal.updateOne({_id:got_useremail._id},{$set : {refreshtoken: refresh}})
                // send refrestoken as a cookie and accesscookie as a regular expression
                sendrefreshtoken(res,refresh)
                 sendaccesstoken0(res,req,access,got_useremail)              
              }catch(error){
                  console.log(error)
                  res.status(203).send("Something went wrong!!")
              }
          } 
          else{
              res.status(203).send("Your password is incorrect");
          }
    }

    else if(got_ngoemail){
              const password_compare = await bcrypt.compare(password,got_ngoemail.ngo_password);
              if(password_compare){
                  try{
                     const access = accesstoken(got_ngoemail._id)
                     const refresh = refreshtoken(got_ngoemail._id)
                    //  store refresh in db
                    const set= await NGOMongoosemodal.updateOne({_id:got_ngoemail._id},{$set : {refreshtoken: refresh}})
                    // send refrestoken as a cookie and accesscookie as a regular expression
                    sendrefreshtoken(res,refresh)
                     sendaccesstoken1(res,req,access,got_ngoemail)     
                     res.send("hello")         
                  }catch(error){
                      res.status(203).send("Something went wrong!!")
                  }
              } 
              else{
                  res.status(203).send("Your password is incorrect");
              }
    }
    else{
        res.status(203).send("id not exist either in ngo or user register now");
    }
})


Route.post('/logout',(req,res)=>{
    res.clearCookie("refreshtoken",{path:'/'})
    return res.send("")
})

Route.post('/protected', async(req,res)=>{
    try{
                 const id = isAuth(req)
                 if(id){
                     res.send("This is protected data")
                 }
                 else{
                     res.status(203).send("User not logged in")
                 }

    }catch(err){
        res.status(203).send("not permitted")
    }
})




Route.post('/refresh_token',async(req,res)=>{
    const r = req.cookies.refreshtoken;
    console.log(r)
    if(!r) return res.status(203).send("")
    let payload = null
    try{
         payload = Jwt.verify(r,process.env.REFRESH_TOKEN)
    }catch(err){
        return res.status(203).send("")
    }
    const user = await UserMongoosemodal.find({_id:payload.id})
    const ngo = await NGOMongoosemodal.find({_id:payload.id})
console.log(user)
    if(user.length!=0){
        console.log("user")
        if(!user) return res.status(203).send("")
    if(!user.refreshtoken==r) return res.status(203).send("")

    const access = accesstoken(user._id)

        const set= await UserMongoosemodal.updateOne({_id:user._id},{$set : {refreshtoken: r}})
                return res.status(200).send(access)
    }
    else{
        console.log("ngo")
        if(!ngo) return res.status(203).send("")
    if(!ngo.refreshtoken==r) return res.status(203).send("")

    const access = accesstoken(ngo._id)

        const set= await NGOMongoosemodal.updateOne({_id:ngo._id},{$set : {refreshtoken: r}})
                return res.status(201).send(access)
    }
       
})

Route.post("/createblog",async(req,res)=>{
     const {Blog_title,Blog_content,Blog_amount,Blog_wallet,Blog_author} = req.body;
    const got_blogauthor = await NGOMongoosemodal.findOne({refreshtoken:Blog_author})
     
     const new_blog = new BlogMongoosemodal({
        Blog_title,
        Blog_content,
        Blog_amount,
        Blog_wallet,
        Blog_author:got_blogauthor._id
    })
    try{
           const saveblog = await new_blog.save();
           res.send(saveblog._id)
    }catch(error){
       res.status(203).send("Something wrong occured")
    }
})

Route.get("/blogbyngo/:id",async(req,res)=>{
     const refresh = req.params.id
     const got_blogauthor = await NGOMongoosemodal.findOne({refreshtoken:refresh})
     const got_blog = await BlogMongoosemodal.find({Blog_author:got_blogauthor._id})
     res.send(got_blog)
})

Route.get("/blog",async(req,res)=>{
    const got_blogauthor = await BlogMongoosemodal.find()
    res.send(got_blogauthor)
})

Route.get("/blogauthor/:id",async(req,res)=>{
    const id = req.params.id
    const got_blogauthor = await NGOMongoosemodal.findOne({_id:id})
    res.send(got_blogauthor)
})

module.exports = Route