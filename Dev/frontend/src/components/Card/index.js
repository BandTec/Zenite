import React from 'react';

import { Card as Cardizinho } from './styles';

export default function Card({ row, column, cor, children }) {
  return (
          <Cardizinho row={row} column={column} cor={cor}>
            {children}
          </Cardizinho>
  );
}
