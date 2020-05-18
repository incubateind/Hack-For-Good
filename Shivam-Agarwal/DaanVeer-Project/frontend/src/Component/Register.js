import React, { useContext, useState, useEffect } from 'react';
import { LoadContext } from '../Context/LoadContext';
import { AuthContext } from '../Context/AuthContext';
import {Link, Redirect} from 'react-router-dom'
import {Card,CardContent,Typography,makeStyles,TextField,Button,Radio,FormControl,FormLabel,FormControlLabel,RadioGroup} from '@material-ui/core'
import Axios from 'axios'
import Loading from '../Structure/Loading'
import Navbar2 from '../Structure/Navbar2';

const useStyles = makeStyles(theme => ({
    root: {
      display: 'flex',
      maxWidth: 500,
      marginLeft:'auto',
      marginRight:'auto',
      marginTop:50,
      height:550
    },
    details: {
      display: 'flex',
      flexDirection: 'column',
    },
    content: {
      flex: '1 0 auto',
      textAlign:"center"
    },
    text: {
        marginLeft: 60,
        width:350
    },
    texterror: {
        marginLeft: 60,
        width:350,
        color:'red'
    }

  }));

const Register = () => {

    const {Load,setloadTrue,setloadFalse} = useContext(LoadContext)
    const {Auth,setauth,statuss,setstatuss} = useContext(AuthContext)

    const [select,setSelect] = useState(false)
    const [status,setStatus] = useState(false)
    const [error,setError] = useState()
    const [regis,setRegis] = useState()

    const classes = useStyles();

    const submituser = (event) =>{
        event.preventDefault()
        setloadTrue()
        Axios.post('http://localhost:4000/userregister',{
            user_name: event.target.username.value,
            user_email: event.target.useremail.value,
            user_password: event.target.password.value,
            user_mobile_no: event.target.usermobile.value
        }).then((res)=>{
            if(res.status==200){
                setStatus(false)
                setRegis(true)
            }else{
                setStatus(true)
                setError(res.data)
            }   
            setloadFalse()         
        }).catch((err)=>{
            console.log(err)
            setloadFalse()
        })
    }

    const submitngo = (event) =>{
      event.preventDefault()
      setloadTrue()
      Axios.post('http://localhost:4000/ngoregister',{
          ngo_name: event.target.username.value,
          ngo_email: event.target.useremail.value,
          ngo_password: event.target.password.value,
          ngo_bio: event.target.bio.value,
          is_ngo : true
      }).then((res)=>{
          if(res.status==200){
              setStatus(false)
              setRegis(true)
          }else{
              setStatus(true)
              setError(res.data)
          }   
          setloadFalse()         
      }).catch((err)=>{
          console.log(err)
          setloadFalse()
      })
  }

  const selectstateuser=()=>{
    setSelect(false)
  }

  const selectstatengo=()=>{
    setSelect(true)
  }
console.log(Auth,regis,)
if(!Auth&&!regis){
  return ( 
    <div>
        {Load? <div><Loading/></div>
        : 
        <div>
        <Navbar2/><br/>
<center>               <FormControl component="fieldset">
<FormLabel component="legend"></FormLabel>
<RadioGroup row aria-label="position" name="position" defaultValue="start">
<FormControlLabel
  value="start"
  control={<Radio color="primary" onClick={selectstateuser}/>}
  label="Register as a User"
  labelPlacement="start"
/>
        <FormControlLabel
  value="bottom"
  control={<Radio color="primary" onClick={selectstatengo}/>}
  label="Register as a NGO's"
  labelPlacement="start"
/>
</RadioGroup>
</FormControl>
</center>
      {  select?
         <Card className={classes.root}>
         <div className={classes.details}>
           <CardContent className={classes.content}>
             <Typography component="h5" variant="h5" className={classes.text} >
               CREATE ACCOUNT
             </Typography><br/><br/>
             {status?
             <div>
             <Typography variant="caption" className={classes.texterror}>
                 *{error}
               </Typography><br/><br/> </div>
             :null
             }
             <br/>
           <form id="form33" onSubmit={submitngo}>
             <TextField id="outlined-basic" label="NGO name" variant="outlined" className={classes.text} name="username" required/><br/><br/>
             <TextField id="outlined-basic" label="Email" variant="outlined" className={classes.text} name="useremail" required/><br/><br/>
             <TextField id="outlined-password-input" label="Password" type="password"  className={classes.text} name="password" autoComplete="current-password" variant="outlined" required/><br/><br/>
             <TextField id="outlined-basic" label="Bio" variant="outlined" className={classes.text} name="bio" required/><br/><br/><br/>
             <Button variant="contained" color="primary" className={classes.text} type="submit">
           SignUp
         </Button><br/><br/>
         </form>
         <Typography variant="caption" className={classes.text}>
             Already have an account? <Link to='/login'>Login</Link>
             </Typography>
           </CardContent>
         </div>
       </Card>
        :
        <Card className={classes.root}>
  <div className={classes.details}>
    <CardContent className={classes.content}>
      <Typography component="h5" variant="h5" className={classes.text} >
        CREATE ACCOUNT
      </Typography><br/><br/>
      {status?
      <div>
      <Typography variant="caption" className={classes.texterror}>
          *{error}
        </Typography><br/><br/> </div>
      :null
      }
      <br/>
    <form id="form33" onSubmit={submituser}>
      <TextField id="outlined-basic" label="Username" variant="outlined" className={classes.text} name="username" required/><br/><br/>
      <TextField id="outlined-basic" label="Email" variant="outlined" className={classes.text} name="useremail" required/><br/><br/>
      <TextField id="outlined-password-input" label="Password" type="password"  className={classes.text} name="password" autoComplete="current-password" variant="outlined" required/><br/><br/>
      <TextField id="outlined-basic" label="UserMobile" variant="outlined" className={classes.text} name="usermobile" required/><br/><br/><br/>
      <Button variant="contained" color="primary" className={classes.text} type="submit">
    SignUp
  </Button><br/><br/>
  </form>
  <Typography variant="caption" className={classes.text}>
      Already have an account? <Link to='/login'>Login</Link>
      </Typography>
    </CardContent>
  </div>
</Card>
}
</div>
}
    </div>)
}
else if(regis){
  return(<Redirect to="/login"/>)
}

else if(statuss===200){
  return( <Redirect to="/dashboarduser"/>)
}

else if(statuss===201){
  return( <Redirect to="/dashboardngo"/>)
}
else{
  return(<div>{statuss}</div>)
}
    }

 
export default Register;