import styled from "styled-components";

export const Row = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: ${(props) => (props.start ? "flex-start" : "space-around")};
  width: 100%;
`;

export const RowBetween = styled(Row)`
  justify-content: space-between;
`;

export const Col = styled.div`
  width: 45%;
  padding-right: ${(props) => (props.start ? 20 : 0)}px;
`;

export const InfoBlock = styled.div`
  background-color: #e1eef5;
  padding: 25px;
  margin: 30px 0px;
`;

export const InfoTitle = styled.h4`
  font-weight: 700;
  font-size: 21px;
  color: #0066ac;
`;

export const InfoDetalhe = styled.h5`
  font-weight: 600;
  font-size: 15px;
  line-height: 24px;
  color: #223f61;
`;

export const Rotulo = styled.p`
  display: block;
  font-size: 15px;
  letter-spacing: 0.05em;
  color: #223f61;
  margin: 10px 0px;
  text-transform: uppercase;
`;

export const Img = styled.img`
  width: 20px;
`;
