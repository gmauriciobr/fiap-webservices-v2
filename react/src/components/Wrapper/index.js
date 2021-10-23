import React from "react";
import { Container } from "./style";

export default function Wrapper({ children, ...props }) {
  return <Container {...props}>{children}</Container>;
}
