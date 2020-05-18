import React,{useContext,useEffect,useState} from 'react';
import {LoadContext} from '../Context/LoadContext'
import {Redirect} from 'react-router-dom'
import Navbar from '../Structure/Navbar'
import Loading from '../Structure/Loading'
import Axios from 'axios';
import { AuthContext } from '../Context/AuthContext';
import {Card,CardContent,Typography,makeStyles,TextField,Button} from '@material-ui/core'
import Cookies from 'js-cookie'

const useStyles = makeStyles(theme => ({
    root: {
        display: 'flex',
        flexWrap: 'wrap',
      },
      textField: {
        marginLeft: theme.spacing(1),
        marginRight: theme.spacing(1),
        width: '25ch',
      },

  }));



const Blogform = () => {
    const classes = useStyles();
    
    const [status,setStatus] = useState(false)
    const [error,setError] = useState()

    const raised = (e) =>{
        e.preventDefault()
        Axios.post("http://localhost:4000/createblog",{
            Blog_title: e.target.Blog_title.value,
            Blog_content:e.target.Blog_content.value,
            Blog_amount:e.target.Blog_amount.value,
            Blog_wallet:e.target.Blog_wallet.value,
            Blog_author:Cookies.get('refreshtoken')
        }).then((res)=>{
            if(res.status===203){
                setStatus(true)
            setError(res.data)
            }else{
                console.log(res)
                alert("Your request for project is raised!! Thank you")
            }
        }).catch((err)=>{
            setStatus(true)
            setError(err)
        })
    
    }
    
    return ( 
        <div className={classes.root}>
                    <form id="form33" onSubmit={raised}>
            <TextField
          id="outlined-full-width"
          label="Project Title"
          style={{ margin: 8 }}
          fullWidth
          margin="normal"
          InputLabelProps={{
            shrink: true,
          }}
          variant="outlined"
          name="Blog_title"
          required
        />
        <TextField
        label="Wallet address"
        id="outlined-margin-normal"
        className={classes.textField}
        margin="normal"
        variant="outlined"
        name="Blog_wallet"
        required
      />
        <TextField
          label="Amount Required"
          id="outlined-margin-normal"
          className={classes.textField}
          margin="normal"
          variant="outlined"
          name="Blog_amount"
          required
        />
         <TextField
          id="outlined-multiline-static"
          label="Project Content"
          multiline
          fullWidth
          rows="4"
          margin="normal"
          variant="outlined"
          name="Blog_content"
          required
        /><br/><br/>
        <Button variant="contained" color="primary" className={classes.text} type="submit">
                Raise
              </Button>
               {status?
                  <div>
                  <Typography variant="caption">
                      *{error}
                    </Typography><br/><br/> </div>
                  :null
                  }
              </form>
            </div>
    )}
 
export default Blogform;
