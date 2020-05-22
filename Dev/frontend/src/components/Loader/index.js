import React from 'react';
import { Center } from './styles';
import Loading from "./../../assets/img/loading1.gif";

export default function Loader() {
  return (
    <Center>
      <img src={Loading} alt="Carregamento" />
    </Center>
  );
}
