import React,{useContext,useEffect,useState} from 'react';
import {LoadContext} from '../Context/LoadContext'
import {Redirect} from 'react-router-dom'
import Navbar from '../Structure/Navbar'
import Loading from '../Structure/Loading'
import {Grid,Paper,makeStyles, Button,Divider,Drawer,Hidden,List,ListItem,ListItemText,useTheme,Typography} from '@material-ui/core'
import Axios from 'axios';
import { AuthContext } from '../Context/AuthContext';
import Contentfetchuser from './Contentfetchuser'

const drawerWidth = 240;

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
  },
  drawer: {
    [theme.breakpoints.up('sm')]: {
      width: drawerWidth,
      flexShrink: 0,
    },
  },
  toolbar: theme.mixins.toolbar,
  drawerPaper: {
    width: drawerWidth,
  },
  
  text: {
    [theme.breakpoints.up("sm")]: {
      width: `calc(100% - ${drawerWidth}px)`,
      marginLeft: drawerWidth
    }
  },
  content: {
    flexGrow: 1,
    padding: theme.spacing(3),
  },
}));

const Dashboarduser = (props) => {

    const {Load,setloadTrue,setloadFalse} = useContext(LoadContext)
    const {Auth,setauth,statuss,setstatuss} = useContext(AuthContext)
    const { container } = props;
    const classes = useStyles();
    const theme = useTheme();
    const [mobileOpen, setMobileOpen] = useState(false);
    const [count,setCount] = useState(1)
  
    const handleDrawerToggle = (mobile) => {
      setMobileOpen(mobile);
    };
    const handleDrawerToggle2 = () => {
        setMobileOpen(!mobileOpen);
      };
  
    let header = {
        headers : {'Authorization' : 'Bearer ' + Auth}
    }

    const drawer = (
        <div>
          <div className={classes.toolbar} />
          <Divider />
          <List>
              <ListItem button onClick={()=>{setCount(1)}}>
                <ListItemText>Show all projects</ListItemText>
              </ListItem>
              <ListItem button onClick={()=>{setCount(2)}}>
                <ListItemText>Show all donation made by you</ListItemText>
              </ListItem>
              <ListItem button onClick={()=>{setCount(3)}}>
                <ListItemText>Show all donater</ListItemText>
              </ListItem>
          </List>
         
        </div>
      );
    useEffect(()=>{
        console.log("jeee")
        setloadTrue()
        const protecteds = () => {
            Axios.post("http://localhost:4000/protected",{},header).then((res)=>{
                if(res.status===200){
                    console.log(res)
                }else{
                    console.log(res)
                }setloadFalse()
           })
        }
      protecteds()
    },[])

if(Auth===""){
    return(
        <div>
        {Load? <div><Loading/></div>
            :
           <div> <Redirect to="/login"/>)</div>
        }
   </div>
    )}
    else if(statuss===201){
        return(
            <div>
            {Load? <div><Loading/></div>
                :
               <div> <Redirect to="/dashboardngo"/></div>
            }
       </div>
        )
    }
    else{
        console.log("ji")
    return (  
        
        <div>
        {Load? <div><Loading/></div>
        :
        <div>
            <Navbar mobileOpen={mobileOpen} handleDrawerToggle={handleDrawerToggle}/> 
      <nav className={classes.drawer} aria-label="mailbox folders">
        {/* The implementation can be swapped with js to avoid SEO duplication of links. */}
        <Hidden smUp implementation="css">
          <Drawer
            container={container}
            variant="temporary"
            anchor={theme.direction === 'rtl' ? 'right' : 'left'}
            open={mobileOpen}
            onClose={handleDrawerToggle2}
            classes={{
              paper: classes.drawerPaper,
            }}
            ModalProps={{
              keepMounted: true, // Better open performance on mobile.
            }}
          >
            {drawer}
          </Drawer>
        </Hidden>
        <Hidden xsDown implementation="css">
          <Drawer
            classes={{
              paper: classes.drawerPaper,
            }}
            variant="permanent"
            open
          >
            {drawer}
          </Drawer>
        </Hidden>
      </nav>
      <main className={classes.content}>
        <div className={classes.toolbar} />
        <Typography className={classes.text}>
            <Contentfetchuser count={count}/>
        </Typography>
        </main>
        </div>
}
    </div>
    )
}
    

}
 
export default Dashboarduser
;