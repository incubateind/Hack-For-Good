import React, { useEffect, useState } from 'react';
import Axios from 'axios';
import Cookies from 'js-cookie'
import {Card,CardActionArea,CardContent,Typography,Button,makeStyles,CardActions,withStyles,TextField} from '@material-ui/core'
import Dialog from '@material-ui/core/Dialog';
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import MuiDialogContent from '@material-ui/core/DialogContent';
import MuiDialogActions from '@material-ui/core/DialogActions';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import {web3,transfer} from '../App'

const useStyles = makeStyles({
    root: {
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
    }
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

const Blogbyngo = () => {
    const [Blog,setBlog] = useState([])
    const classes = useStyles();
    const [open, setOpen] = React.useState(false);
    const [ind,setInd] = useState(0)
    const [acc,setAccount] = useState([])

    const handleClickOpen = (i) => {
        setInd(i)
      setOpen(true);
    };
    const handleClose = () => {
      setOpen(false);
    };
    const check = async(address) =>{
          const sentfromaccount = await transfer.methods.getNGODonaterCount(address).call().then((res)=>{
            setAccount(res)
          })
        
        }
    useEffect(()=>{
        const getdata = async() =>{
            const str = "http://localhost:4000/blogbyngo/" + Cookies.get('refreshtoken') 
            Axios.get(str).then((res)=>{
                setBlog(res.data)   
        }).catch((err)=>{
            console.log(err)
        })
        }
       getdata()
    },[])

if(Blog.length!=0){
       return(
           <div className={classes.container}>
               { Blog.map((i,index)=>{
        return(
        <Card className={classes.root}>
        <CardActionArea>
          <CardContent>
            <Typography gutterBottom variant="h5" component="h2">
              {i.Blog_title}
            </Typography>
            <Typography variant="body2" color="textSecondary" component="p">
              {i.Blog_content}
            </Typography><br/><br/>
            <Typography variant="body2" color="textSecondary" component="p">
              Total Amount Required : {i.Blog_amount}
            </Typography>
            <Typography variant="body2" color="textSecondary" component="p">
              Wallet Address : {i.Blog_wallet}
            </Typography>
          </CardContent>
        </CardActionArea>
        <CardActions>
          <Button size="small" color="primary" className="classes.button" onClick={()=>{handleClickOpen(index)}}>
            Amount Raised
          </Button>
        </CardActions>
      </Card>)
    })}
    <Dialog onClose={handleClose} aria-labelledby="customized-dialog-title" open={open}>
        <DialogTitle id="customized-dialog-title" onClose={handleClose}>
          {Blog.map((data,i)=>{
              if(i==ind){
                  return (
                      <div>All The Donation for {data.Blog_title}</div>
                  )} })}
        </DialogTitle>
        <DialogContent dividers>
          <Typography variant="body2" color="textSecondary" component="p">
          {Blog.map((data,i)=>{
              if(i==ind){
                  return (
                      <div>Project Wallet Address : {data.Blog_wallet}</div>
                  )} })}
          </Typography>
          <Button onClick={()=>{
            Blog.map((data,i)=>{
                if(i==ind){
                    check(data.Blog_wallet)
                }
            })
            }}>Show all donations</Button>
            {/* { acc.length? */}
            {acc.map((am)=>{
              return (<div>{web3.utils.fromWei(am,'ether')} Ether donated</div>) 
            })}
            {/* :
            <div>No donations made</div>
            } */}
        </DialogContent>
      </Dialog>
               </div>
       )
}else{
    return (<div>Loading....</div>)
}
}
 
export default Blogbyngo;