import styled, { css } from "styled-components"

export const Container = styled.button`
  border: none;
  border-radius: 20px;
  background-color: #e91c5d;
  color: #fff;
  cursor: pointer;
  padding: 5px 15px;
  transition: all 0.3s ease;
  font-family: "Roboto Condensed", sans-serif;

  ${({ padding }) =>
    padding
      ? css`
          padding: 10px 30px;
        `
      : ""}

  :hover {
    background: #b10f44;
  }

  ${({ disabled }) =>
    disabled
      ? css`
          background-color: rgba(233, 28, 93, 0.5);
          cursor: default;

          :hover {
            background-color: rgba(233, 28, 93, 0.5);
          }
        `
      : ""}
`
