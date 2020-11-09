import styled, { css } from "styled-components";
import AsyncSelect from "react-select/async";

export const Container = styled.div`
  margin-top: 36px;
`;

import Select from "react-select";

export const StyledSelect = styled(AsyncSelect)`
  & .Select__control {
    border: 1px solid ${colors.grayMedium2};
    border-radius: 4px;

    width: 100%;
    min-height: 45px;
    color: ${colors.black};
    margin-top: 10px;
    transition: all ease-out 0.3s;
  }

  & .Select__indicator-separator {
    background-color: ${colors.grayMedium2};
  }

  & .Select__dropdown-indicator {
    color: ${colors.grayMedium2};
  }

  & .Select__value-container {
    padding: 5px 12px;
  }

  & .Select__loading-indicator {
    color: hsl(274.6, 50.4%, 22.9%);
    font-size: 6px;
  }

  & .Select__control--is-disabled {
    background-color: ${colors.grayLight2};
    & .Select__single-value {
      color: #000;
    }
  }

  ${(props) =>
    props.error !== undefined &&
    css`
      & .Select__control {
        border: 1px solid ${colors.secundary} !important;
      }
    `}

  ${(props) =>
    props.error === undefined &&
    css`
      & .Select__control {
        border: 1px solid ${colors.grayMedium2} !important;
      }
    `}

    & .Select__control--is-focused {
    border: 1px solid !important;
    border-color: ${colors.primary} !important;
    box-shadow: none;
    /* box-shadow: 0 0 0 1px  ${colors.primary} !important; */
  }

  ${(props: { feedback: boolean }) =>
    props.feedback &&
    css`
      & .Select__control {
        border: 1px solid ${colors.secundaryDark} !important;
      }
    `}

  &:focus {
    border: 1px solid ${colors.primary};
  }
`;

export const Rotulo = styled.label`
  display: block;
  font-size: 15px;
  letter-spacing: 0.05em;
  color: #223f61;
  margin-bottom: 10px;
  text-transform: uppercase;
`;

export const TextoAlerta = styled.p`
  font-size: 10px;
  font-weight: 600;
  letter-spacing: 0.05em;
  line-height: 12px;
  color: #282828;
  padding-bottom: 10px;
`;

export const ErrorMessage = styled.p`
  margin-top: 5px;
  font-size: 12px;
  color: red;
`;
