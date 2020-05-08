import React from 'react';
import Blogform from './Blogform'
import Blogbyngo from './Blogbyngo'
import Globaltransaction from './Globaltransaction'
const Contentfetch = (props) => {
    if(props.count===1){
        return <Blogform/>
    }
    else if(props.count===2){
        return <Blogbyngo/>
    }else if(props.count===3){
        return (<Globaltransaction/>)
    }
}
 
export default Contentfetch;