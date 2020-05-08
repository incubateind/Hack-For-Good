import React ,{useContext }from 'react';
import LoadContextProvider from './Context/LoadContext';
import AuthContextProvider from './Context/AuthContext';
import Refresh from './Refresh'
import Web3 from 'web3'
import transferabi from './abi/Transfers.json'


export let web3 = new Web3(Web3.givenProvider||"http://localhost:8545")
window.ethereum.enable()
export let transfer = new web3.eth.Contract(transferabi.abi,"0xbA18d8e1B46Ec67A2B506ADa42E1256725C02490")

const App = () => {

    return (
        <LoadContextProvider> 
            <AuthContextProvider>  
                <Refresh/>
         </AuthContextProvider>
         </LoadContextProvider>

       
    );
}
 
export default App;
    