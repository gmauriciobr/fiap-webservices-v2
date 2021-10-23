import React from "react";
import { Container } from "./style";

export default function WrapperFlex({ children, ...props }) {
  return <Container {...props}>{children}</Container>;
}
