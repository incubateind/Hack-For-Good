import React,{useState, useEffect,useContext} from 'react';
import { AuthContext } from './Context/AuthContext';
import Axios from 'axios'
import Dashboarduser from './Component/Dashboarduser'
import Dashboardngo from './Component/Dashboardngo'

import {BrowserRouter,Switch,Route} from 'react-router-dom'
import Register from './Component/Register'
import Login from './Component/Login'
import { LoadContext } from './Context/LoadContext';



const Refresh = (props) => {
    const {Auth,setauth,statuss,setstatuss} = useContext(AuthContext)
    const {Load,setloadTrue,setloadFalse} = useContext(LoadContext)

        useEffect(()=>{
          console.log("j")
        setloadTrue()
            const refresh = async() =>{
             Axios.post('http://localhost:4000/refresh_token',{},{withCredentials:true}).then((res)=>{
               console.log(res.status)
               setauth(res.data)
               setstatuss(res.status)
               setloadFalse()
            }).catch((err)=>{
              console.log("heloo")
            })}
      refresh()
          },[])

    return ( 
        <div>
          <BrowserRouter>
             <Switch>
                 <Route path="/register" component={Register}/>
                 <Route path="/login" component={Login}/>
                 <Route path="/dashboarduser" component={Dashboarduser}/>
                 <Route path="/dashboardngo" component={Dashboardngo}/>

             </Switch>
         </BrowserRouter>
           </div>
     );
}
 
export default Refresh;