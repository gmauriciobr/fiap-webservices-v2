import styled, { keyframes } from "styled-components"

const slideInFromBottom = keyframes`
  0% {
    transform: translateY(1000px);
    opacity: 0;
  }

  100% {
    transform: translateY(0);
    opacity: 1;
  }
`

export const Container = styled.div`
  animation: ${slideInFromBottom} 1.4s ease;
  background-color: #fff;
  border: none;
  border-radius: 15px;
  width: 100%;
  padding: 0 10px 10px 10px;
}
`
