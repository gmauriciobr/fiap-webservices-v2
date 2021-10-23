import React from 'react'
import {Container} from './style'

export default function ItemHeader({children, ...props}) {
    return <Container {...props}>{children}</Container>
}