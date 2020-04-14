import React from 'react';
import {Link} from 'react-router-dom';
import { EstiloTitulo } from './styles';
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
      minWidth: 260,
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

export default function Tabela( { tabela, dadosCabecalho, dadosCorpo } ) {

    const classes = useStyles();
    if(tabela === 1){
        return (
            <TableContainer component={Paper}>
                <Table className={classes.table} aria-label="simples table">
                    <TableHead>
    
                        {dadosCabecalho.map((linha) => (
                            <StyledTableRow key={linha.name}>
                                <StyledTableCell><EstiloTitulo>{linha.name}</EstiloTitulo></StyledTableCell>
                                <StyledTableCell><EstiloTitulo>{linha.dados1}</EstiloTitulo></StyledTableCell>
                            </StyledTableRow>
                        ))}
    
                    </TableHead>
                    <TableBody>
    
                        {dadosCorpo.map((linha) => (
                            <StyledTableRow key={linha.name}>
                                <StyledTableCell component="th" scope="row">
                                    {linha.name}
                                </StyledTableCell>
                                <StyledTableCell align="left">{linha.dados1}</StyledTableCell>
                            </StyledTableRow>
                        ))}
    
                    </TableBody>
                </Table>
            </TableContainer>
        );
    }else{
        return (
          <TableContainer component={Paper}>
            <Table className={classes.table} aria-label="simples table">
              <TableHead>
                {dadosCabecalho.map((linha) => (
                  <StyledTableRow key={linha.registro}>
                    <StyledTableCell>
                      <EstiloTitulo>{linha.registro}</EstiloTitulo>
                    </StyledTableCell>
                    <StyledTableCell>
                      <EstiloTitulo>{linha.nome}</EstiloTitulo>
                    </StyledTableCell>
{/*                     <StyledTableCell>
                      <EstiloTitulo>{linha.dataNasc}</EstiloTitulo>
                    </StyledTableCell> */}
                    <StyledTableCell>
                      <EstiloTitulo>{linha.telefone}</EstiloTitulo>
                    </StyledTableCell>
                    <StyledTableCell>
                      <EstiloTitulo>{linha.cpf}</EstiloTitulo>
                    </StyledTableCell>
                    <StyledTableCell>
                      <EstiloTitulo>{linha.acoes}</EstiloTitulo>
                    </StyledTableCell>
                  </StyledTableRow>
                ))}
              </TableHead>
              <TableBody>
                {dadosCorpo.map((linha) => (
                  <StyledTableRow key={linha.registro}>
                    <StyledTableCell component="th" scope="row">
                      {linha.registro}
                    </StyledTableCell>
                    <StyledTableCell align="left">{linha.nome}</StyledTableCell>
                    {/* <StyledTableCell align="left">
                      {linha.dataNasc}
                    </StyledTableCell> */}
                    <StyledTableCell align="left">
                      {linha.telefone}
                    </StyledTableCell>
                    <StyledTableCell align="left">
                      {linha.cpf}
                    </StyledTableCell>
                    <StyledTableCell align="left">
                      <Link to={`/fiscal/editar/${linha.registro}`}>excluir</Link>
                      <Link to={`/fiscal/editar/1/${linha.registro}`}>editar</Link>
                    </StyledTableCell>
                  </StyledTableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        );
    }

    
}
