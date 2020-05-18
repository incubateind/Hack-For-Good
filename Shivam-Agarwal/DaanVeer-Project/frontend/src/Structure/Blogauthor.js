
import React, { useEffect } from 'react';
import { useState } from 'react';
import Axios from 'axios'

const Blogauthor = (props) => {
    const [author,setAuthor] = useState()

    useEffect(()=>{
        let url = "http://localhost:4000/blogauthor/"+ props.id
        Axios.get(url).then((res)=>{
             setAuthor(res.data.ngo_name)
    }).catch((err)=>{
        console.log(err)
    })
    },[])

    return ( 
        <div>By: {author} NGO</div>
     );
}
 
export default Blogauthor;