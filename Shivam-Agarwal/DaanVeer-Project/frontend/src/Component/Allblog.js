import React, { useEffect, useState } from 'react';
import Axios from 'axios';
import {Card,CardActionArea,CardContent,Typography,Button,makeStyles,CardActions,withStyles,TextField} from '@material-ui/core'
import Blogauthor from '../Structure/Blogauthor'
import Dialog from '@material-ui/core/Dialog';
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import MuiDialogContent from '@material-ui/core/DialogContent';
import MuiDialogActions from '@material-ui/core/DialogActions';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import {web3,transfer} from '../App'


const useStyles = makeStyles({
    roots: {
      width: 340,
      border: "1px solid blue",
      height:300
    },
    container:{
        display:'flex',
        flexDirection:'row',
        flexWrap:'wrap',
        justifyContent:'space-around',
        alignItems:'center'
    },
    
  });
  
const styles = (theme) => ({
  
  closeButton: {
    position: 'absolute',
    right: theme.spacing(1),
    top: theme.spacing(1),
    color: theme.palette.grey[500],
  },
});


  const DialogTitle = withStyles(styles)((props) => {
    const { children, classes, onClose, ...other } = props;
    return (
      <MuiDialogTitle disableTypography className={classes.root} {...other}>
        <Typography variant="h6">{children}</Typography>
        {onClose ? (
          <IconButton aria-label="close" className={classes.closeButton} onClick={onClose}>
            <CloseIcon />
          </IconButton>
        ) : null}
      </MuiDialogTitle>
    );
  });
  
  const DialogContent = withStyles((theme) => ({
    root: {
      padding: theme.spacing(2),
    },
  }))(MuiDialogContent);
  
  const DialogActions = withStyles((theme) => ({
    root: {
      margin: 0,
      padding: theme.spacing(1),
    },
  }))(MuiDialogActions);
  
const Blog = () => {
    const [Blog,setBlog] = useState([])
    const classes = useStyles();
    const [open, setOpen] = React.useState(false);
    const [ind,setInd] = useState(0)
    const handleClickOpen = (i) => {
        setInd(i)
      setOpen(true);
    };
    const handleClose = () => {
      setOpen(false);
    };
    useEffect(()=>{
        const getdata = async() =>{
            Axios.get("http://localhost:4000/blog").then((res)=>{
                setBlog(res.data)   
        }).catch((err)=>{
            console.log(err)
        })
        }
       getdata()
    },[])
    const check = async(e,address) =>{
        e.preventDefault()
        let amount = e.target.amount.value
        let sender = await web3.eth.getAccounts()
            const sentfromaccount = await transfer.methods.setTransaction(address).send({from:sender[0], to:address, value: web3.utils.toWei(amount,"ether")})
    }
if(Blog.length!=0){
    
       return(
           <div className = {classes.root}>
           <div className={classes.container}>
               { Blog.map((i,index)=>{  
        return(
        <Card className={classes.roots}>
        <CardActionArea>
          <CardContent>
            <Typography gutterBottom variant="h5" component="h2">
              {i.Blog_title}
            </Typography>
            <Typography gutterBottom variant="body2" color="textSecondary" component="h2">
                <Blogauthor id={i.Blog_author}/>  
            </Typography>
            <Typography variant="body2" color="textSecondary" component="p">
              {i.Blog_content}
            </Typography><br/><br/>
            <Typography variant="body2" color="textSecondary" component="p">
              Total Amount Required : {i.Blog_amount}
            </Typography>
          </CardContent>
        </CardActionArea>
        <CardActions>
          <Button size="small" color="primary" className="classes.button" onClick={()=>{handleClickOpen(index)}}>
                Donate
          </Button>
        </CardActions>
      </Card>)
    })} 
      <Dialog onClose={handleClose} aria-labelledby="customized-dialog-title" open={open}>
        <DialogTitle id="customized-dialog-title" onClose={handleClose}>
          {Blog.map((data,i)=>{
              if(i==ind){
                  return (
                      <div>Donate for {data.Blog_title}</div>
                  )} })}
        </DialogTitle>
        <form onSubmit={(e)=>{
            Blog.map((data,i)=>{
                if(i==ind){
                    check(e,data.Blog_wallet)
                }
            })
            }}>
        <DialogContent dividers>
          <TextField
          id="outlined-textarea"
          label="Donate Amount"
          variant="outlined"
          required
          name="amount"
        /><br/><br/><br/>
          <Typography variant="body2" color="textSecondary" component="p">
          {Blog.map((data,i)=>{
              if(i==ind){
                  return (
                      <div>Project Wallet Address : {data.Blog_wallet}</div>
                  )} })}
          </Typography>
        </DialogContent>
        <DialogActions>
          <Button autoFocus color="primary" type="submit">
            Donate
          </Button>
        </DialogActions>
        </form>
      </Dialog>
               </div>
               </div>
       )
}else{
    return (<div>Loading....</div>)
}
}
 
export default Blog;