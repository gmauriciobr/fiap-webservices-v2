import React from 'react';
import {Container} from './style'

export default function Button({children, ...props}) {
    return <Container {...props}>{children}</Container>
}