import React,{useContext} from 'react';
import {AppBar,Toolbar,IconButton,Typography,Button,makeStyles} from '@material-ui/core'
import LibraryAddIcon from '@material-ui/icons/LibraryAdd';
import {Link} from 'react-router-dom'
import Axios from 'axios'

const useStyles = makeStyles(theme => ({
    root: {
      flexGrow: 1,
    },
    menuButton: {
      marginRight: theme.spacing(2),
    },
    title: {
      flexGrow: 1,
    },
  }));

const Navbar2 = () => {
    const classes = useStyles();
    


    return ( 
        <div>
    <AppBar position="static">
        <Toolbar>
    <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu">
      <LibraryAddIcon />
    </IconButton>
    <Typography variant="h6" className={classes.title}>
      DaanVeer
    </Typography>
     <div>
      <Link to="/register" style={{ color: '#FFF', padding:'40px'}}>Register</Link>
      <Link to="/login" style={{ color: '#FFF', padding:'3px'}}>Login</Link>
    </div>
  </Toolbar>
</AppBar>
        </div>
     );
}
 
export default Navbar2;