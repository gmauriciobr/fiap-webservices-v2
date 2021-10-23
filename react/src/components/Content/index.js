import React from 'react'
import {Container} from './style'

export default function Content({children, ...props}) {
    return <Container {...props}>{children}</Container>
}