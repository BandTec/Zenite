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
//Adicionei esse componente, dentro da visualiza Linha
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
    
                        {dadosCabecalho.map((motorista) => (
                            <StyledTableRow key={motorista.name}>
                                <StyledTableCell><EstiloTitulo>{motorista.name}</EstiloTitulo></StyledTableCell>
                                <StyledTableCell><EstiloTitulo>{motorista.dados1}</EstiloTitulo></StyledTableCell>
                            </StyledTableRow>
                        ))}
    
                    </TableHead>
                    <TableBody>
    
                        {dadosCorpo.map((motorista) => (
                            <StyledTableRow key={motorista.name}>
                                <StyledTableCell component="th" scope="row">
                                    {motorista.name}
                                </StyledTableCell>
                                <StyledTableCell align="left">{motorista.dados1}</StyledTableCell>
                            </StyledTableRow>
                        ))}
    
                    </TableBody>
                </Table>
            </TableContainer>
        );
    }else{
      console.log(dadosCorpo)
      //Aqui vai as configurações da tabela
        return (
          <TableContainer component={Paper}>
            <Table className={classes.table} aria-label="simples table">
              <TableHead>
                {dadosCabecalho.map((motorista) => (
                  <StyledTableRow key={motorista.nome}>
                     <StyledTableCell>
                      <EstiloTitulo>{motorista.nome}</EstiloTitulo>
                    </StyledTableCell>
                    <StyledTableCell>
                      <EstiloTitulo>{motorista.numeroTelefone}</EstiloTitulo>
                    </StyledTableCell>
                    <StyledTableCell>
                      <EstiloTitulo>{motorista.cpf}</EstiloTitulo>
                    </StyledTableCell>
                    <StyledTableCell>
                      <EstiloTitulo>{motorista.cnh}</EstiloTitulo>
                    </StyledTableCell>
                    <StyledTableCell>
                      <EstiloTitulo>{motorista.acoes}</EstiloTitulo>
                    </StyledTableCell>
                  </StyledTableRow>
                ))}
              </TableHead>
              <TableBody>
                {dadosCorpo.map((motorista) => (
                  <StyledTableRow key={motorista.nome}>
                    <StyledTableCell component="th" scope="row">
                      {motorista.numeroTelefone}
                    </StyledTableCell>
                    <StyledTableCell align="left">{motorista.cpf}</StyledTableCell>
                   
                    <StyledTableCell align="left">
                      {motorista.cnh}
                    </StyledTableCell>
                    <StyledTableCell align="left">
                      <Link to={`/motorista/editar/${motorista.numero}`}>excluir</Link>
                      <Link to={`/motorista/editar/1/${motorista.numero}`}>editar</Link>
                    </StyledTableCell>
                  </StyledTableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        );
    }

    
}
