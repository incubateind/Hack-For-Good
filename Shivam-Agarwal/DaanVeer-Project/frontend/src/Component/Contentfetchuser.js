import React from 'react';
import Blog from './Allblog'
import Alltransaction from './Alltransaction'
import Globaltransaction from './Globaltransaction'

const Contentfetchuser = (props) => {
    if(props.count===1){
        return <Blog/>
    }
    else if(props.count===2){
        return <Alltransaction/>
    }else if(props.count===3){
        return (<Globaltransaction/>)
    }else{
        return(<div>hello</div>)
    }
}
 
export default Contentfetchuser;