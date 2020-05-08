import React, { useState,useEffect } from 'react';
import { withStyles, makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import {web3,transfer} from '../App'


const StyledTableCell = withStyles((theme) => ({
    head: {
      backgroundColor: theme.palette.primary.dark,
      color: theme.palette.common.white,
    },
    body: {
      fontSize: 14,
    },
  }))(TableCell);
  
  const StyledTableRow = withStyles((theme) => ({
    root: {
      '&:nth-of-type(odd)': {
        backgroundColor: theme.palette.background.default,
      },
    },
  }))(TableRow);
  
  
  const useStyles = makeStyles({
    table: {
      minWidth: 600,
    },
  });
  


const Alltransaction = () => {
    const [acc,setAccount] = useState([])
    const classes = useStyles();

    useEffect(()=>{
        const transactions = async()=>{
            const account = await web3.eth.getAccounts()
             await transfer.methods.getAllInfoByAccount(account[0]).call().then((res)=>{
                setAccount(res)
            })
        }
        transactions()
    },[])
    
    if(acc.length!=0){
        console.log(acc[0][0])
        return (
            <div> 
                <TableContainer component={Paper}>
          <Table className={classes.table} aria-label="customized table">
            <TableHead>
              <TableRow>
                <StyledTableCell>NGO Address</StyledTableCell>
                <StyledTableCell align="right">Amount donated</StyledTableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {acc.map((row) => (
                <StyledTableRow key={row[1]}>
                  <StyledTableCell component="th" scope="row">
                    {row[1]}
                  </StyledTableCell>
                  <StyledTableCell align="right">{web3.utils.fromWei(row[0],'ether')} Ether</StyledTableCell>
                </StyledTableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
        </div>
         );
    }else{
        return(<div>No Donation made</div>)
    }
   
}
 
export default Alltransaction;
