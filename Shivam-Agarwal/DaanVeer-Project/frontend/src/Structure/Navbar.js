import React,{useContext,useState} from 'react';
import {AppBar,Toolbar,IconButton,Typography,Button,makeStyles,CssBaseline} from '@material-ui/core'
import LibraryAddIcon from '@material-ui/icons/LibraryAdd';
import { AuthContext } from '../Context/AuthContext';
import {Link} from 'react-router-dom'
import Axios from 'axios'

const drawerWidth = 240;
const useStyles = makeStyles(theme => ({
    root: {
      display: "flex"
    },
  
    appBar: {
      [theme.breakpoints.up("sm")]: {
        width: `calc(100% - ${drawerWidth}px)`,
        marginLeft: drawerWidth
      }
    },
    menuButton: {
      marginRight: theme.spacing(2),
      [theme.breakpoints.up("sm")]: {
        display: "none"
      }
    },
    title: {
      flexGrow: 1,
    },
  }));

const Navbar = (props) => {
    const classes = useStyles();
 
    const {Auth,setauth} = useContext(AuthContext)
    function handleDrawer() {
         props.handleDrawerToggle(!props.mobileOpen);
    }
    const logoutfunction = () =>{
      Axios.post("http://localhost:4000/logout",{},{withCredentials:true}).then((res)=>{
          setauth(res.data)
      }).catch((err)=>{
           alert("something went wrong")
      })
  }

    return ( 
        <div>
  <div className={classes.root}>
      <CssBaseline />
      <AppBar position="fixed" className={classes.appBar}>
        <Toolbar>
          <IconButton
            color="inherit"
            aria-label="open drawer"
            edge="start"
            onClick={handleDrawer}
            className={classes.menuButton}
          >
            <LibraryAddIcon/>
          </IconButton>
          <Typography variant="h6" className={classes.title} noWrap>
            DaanVeer
          </Typography>
    {Auth  ?
    <div>
    <Button color="inherit" onClick={logoutfunction}>Logout</Button>
    </div>
     : 
     <div>
      <Link to="/register" style={{ color: '#FFF', padding:'40px'}}>Register</Link>
      <Link to="/login" style={{ color: '#FFF', padding:'3px'}}>Login</Link>

    </div>
    }
  </Toolbar>
</AppBar>
        </div>
        </div>
     );
}
 
export default Navbar;