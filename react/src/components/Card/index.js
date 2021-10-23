import React from 'react';
import {Container} from './style'

export default function Card({children, ...props}) {
    return <Container {...props}>{children}</Container>
}