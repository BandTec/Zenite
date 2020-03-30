import React from 'react';
import { Container, EstiloTitulo } from './styles';
import { makeStyles, withStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';

const useStyles = makeStyles({
    table: {
      maxWidth: 260,
      maxHeight: 420,
    },
});

const StyledTableCell = withStyles(theme => ({
    head: {
    //background: 'linear-gradient(50deg, #D5E9F3 90%, #D5E9F3 90%)',
    //color: theme.palette.common.black,
    background: 'linear-gradient(50deg, #ffffff 90%, #ffffff 90%)',
    },
    body: {
        fontSize: 14,
        
      
    },
  }))(TableCell);
  
  const StyledTableRow = withStyles(theme => ({
    root: {
      '&:nth-of-type(odd)': {
           background: 'linear-gradient(50deg, #D5E9F3 90%, #D5E9F3 90%)',
        //backgroundColor: theme.palette.background.default,
        
      },
    },
  }))(TableRow);

export default function Tabela() {

    const classes = useStyles();

    return (
        <TableContainer component={Paper}>
            <Table className={classes.table} aria-label="simples table">
                <TableHead>
                    <StyledTableRow>
                        <StyledTableCell><EstiloTitulo>Código Linha</EstiloTitulo></StyledTableCell>
                        <StyledTableCell><EstiloTitulo>Destino</EstiloTitulo></StyledTableCell>
                    </StyledTableRow>
                </TableHead>
                <TableBody>
                    <StyledTableRow>
                        <StyledTableCell component="th" scope="row">917H-10</StyledTableCell>
                        <StyledTableCell align="left">Vila Mariana</StyledTableCell>
                    </StyledTableRow>
                    <StyledTableRow>
                        <StyledTableCell component="th" scope="row">8001-10</StyledTableCell>
                        <StyledTableCell align="left">Vila Piaui</StyledTableCell>
                    </StyledTableRow>
                    <StyledTableRow>
                        <StyledTableCell component="th" scope="row">8002-10</StyledTableCell>
                        <StyledTableCell align="left">Pirituba</StyledTableCell>
                    </StyledTableRow>
                    <StyledTableRow>
                        <StyledTableCell component="th" scope="row">8004-10</StyledTableCell>
                        <StyledTableCell align="left">Santa</StyledTableCell>
                    </StyledTableRow>
                    <StyledTableRow>
                        <StyledTableCell component="th" scope="row">8005-10</StyledTableCell>
                        <StyledTableCell align="left">Itaim Paulista</StyledTableCell>
                    </StyledTableRow>
                </TableBody>
            </Table>
        </TableContainer>
    );
}
