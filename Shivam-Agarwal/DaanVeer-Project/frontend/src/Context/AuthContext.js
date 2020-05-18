import React,{createContext,useState,useEffect} from 'react';
import Axios from 'axios'

export const AuthContext = createContext()

const AuthContextProvider = (props) => {
    const [Auth,setAuth] = useState()
    const [statuss,setStatuss] = useState()
   
    const setauth= (data) =>{
        setAuth(data)
    }
    const setstatuss= (data) =>{
        setStatuss(data)
    }
    
    return ( 
        <AuthContext.Provider value={{Auth,setauth:setauth,statuss,setstatuss:setstatuss}}>
           {props.children}
        </AuthContext.Provider>
     );
}
 
export default AuthContextProvider;