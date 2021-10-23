import React from 'react';
import {Container} from './style'

export default function ContentWrapper({children, ...props}) {
    return <Container {...props}>{children}</Container>
}