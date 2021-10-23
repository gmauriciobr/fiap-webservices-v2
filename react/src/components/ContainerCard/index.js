import React from "react"

import { Container } from "./style"

export default function ContainerCard({ children, ...props }) {
  return <Container {...props}>{children}</Container>
}
