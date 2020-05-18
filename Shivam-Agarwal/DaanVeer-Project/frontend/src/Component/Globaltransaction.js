import React,{useState,useEffect} from 'react';
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
  


const Globaltransaction = () => {
    const [acc,setAccount] = useState([])
    const classes = useStyles();

    useEffect(()=>{
        const transactions = async()=>{
             await transfer.methods.getAllSenderAccount().call().then((res)=>{
                setAccount(res)
            })
        }
        transactions()
    },[])
    
    if(acc.length!=0){
        console.log(acc)
        return (
            <div> 
                <TableContainer component={Paper}>
          <Table className={classes.table} aria-label="customized table">
            <TableHead>
              <TableRow>
                <StyledTableCell>Donater Address</StyledTableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {acc.map((row) => (
                <StyledTableRow key={row}>
                  <StyledTableCell component="th" scope="row">
                    {row}
                  </StyledTableCell>
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
 
export default Globaltransaction;