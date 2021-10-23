import React from "react";
import { Container } from "./style";

export default function ContainerMain({ children, ...props }) {
  return <Container {...props}>{children}</Container>;
}
