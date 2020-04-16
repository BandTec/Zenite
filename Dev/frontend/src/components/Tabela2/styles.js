import styled from 'styled-components';
import { makeStyles, withStyles } from '@material-ui/core/styles';
import TableCell from '@material-ui/core/TableCell';
import TableRow from '@material-ui/core/TableRow';

export const EstiloTitulo = styled.div`
    color: #000000;
    font-weight: 800px;
`;

export const useStyles = makeStyles({
    table: {
      minWidth: 260,
    },
});

export const StyledTableCell = withStyles(theme => ({
    head: {
        //background: 'linear-gradient(50deg, #D5E9F3 90%, #D5E9F3 90%)',
        //color: theme.palette.common.black,
        background: 'linear-gradient(50deg, #ffffff 90%, #ffffff 90%)',
    },
    body: {
        fontSize: 14,
    },
  }))(TableCell);

export const StyledTableRow = withStyles(theme => ({
    root: {
        '&:nth-of-type(odd)': {
            background: 'linear-gradient(50deg, #D5E9F3 90%, #D5E9F3 90%)',
            //backgroundColor: theme.palette.background.default,
        },
    },
}))(TableRow);

