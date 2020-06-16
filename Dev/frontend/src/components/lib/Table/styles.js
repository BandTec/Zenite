import styled from 'styled-components';

export const Container = styled.div`
    display: flex;
    width: 100%; height: 100%;
    padding: 0 5%;
    flex-direction: column;
    background-color: #0066AC;
    border-radius: 5px;
`;

export const Titulo = styled.div`
    display: flex;
    width: 100%; height: 20%;
    color: #ffffff;
    text-transform: uppercase;
    font-size: 20px;
    align-items: center;
`;

export const Busca = styled.input`
    height: 35px;
    border-radius: 5px;
    padding: 5%;

    &::placeholder{
        color: #223F61;
        font-family: 'Open Sans';
    }
`;

export const Tabela = styled.table`
    margin-top: 15px;
    color: #fff;
    tr{
        display: flex;
        align-items: center;
        height:40px;
        width: 100%;
        th {
            text-transform: uppercase;
        }
        td, th {
            margin-right: 15px;
            color: #fff;
        }
    }
    thead, tbody tr{
        display: table;
        width: 100%;
        table-layout: fixed;
    }

    tbody{
        display: block;
        height: 110px;
        overflow: auto;
    }
`;