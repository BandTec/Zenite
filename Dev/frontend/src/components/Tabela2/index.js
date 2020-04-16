import React from 'react';

import { StyledTableCell, StyledTableRow, useStyles, EstiloTitulo } from './styles';

import Acoes from './Acoes';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableBody from '@material-ui/core/TableBody';
import Table from '@material-ui/core/Table';
import Paper from '@material-ui/core/Paper';

export default function Tabela2({ dados }) {
    const classes = useStyles();
    const cabecalho = [];

    if(dados.length){
        for (const [key, value] of Object.entries(dados[0]))
            cabecalho.push(key)
    }

    return (
        <TableContainer component={Paper}>
            <Table className={classes.table} aria-label="simples table">
                <TableHead>
                    <StyledTableRow>
                        {cabecalho.map(coluna => (
                            <StyledTableCell><EstiloTitulo>{coluna}</EstiloTitulo></StyledTableCell>
                        ))}
                        <StyledTableCell><EstiloTitulo>Ações</EstiloTitulo></StyledTableCell>
                    </StyledTableRow>
                </TableHead>
                <TableBody>
                    {dados.map(linha => 
                        <StyledTableRow>
                            {cabecalho.map(coluna => (
                                <StyledTableCell align="left"><EstiloTitulo>{linha[coluna]}</EstiloTitulo></StyledTableCell>
                            ))}
                        <Acoes id={linha.id}/>
                        </StyledTableRow>
                    )}
                </TableBody>
            </Table>
        </TableContainer>
    );
}
