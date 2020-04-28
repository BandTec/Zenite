import styled from 'styled-components';
import { makeStyles, withStyles } from '@material-ui/core/styles';
import TableCell from '@material-ui/core/TableCell';
import TableRow from '@material-ui/core/TableRow';

export const Titulo = styled.p`
    color: #4F4F4F;
    font-weight: 700;
    text-transform: uppercase;
    letter-spacing: .05em;
`;

export const Texto = styled.p`
    color: #333333;
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

