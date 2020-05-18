import React,{createContext,useState} from 'react';

export const LoadContext = createContext()

const LoadContextProvider = (props) => {
    const [Load,setLoad] = useState(false)

    const setloadTrue = () =>{
        setLoad(true)
    }
    const setloadFalse = () =>{
        setLoad(false)
    }
    
    return ( 
        <LoadContext.Provider value={{Load,setloadTrue:setloadTrue,setloadFalse:setloadFalse}}>
           {props.children}
        </LoadContext.Provider>
     );
}
 
export default LoadContextProvider;