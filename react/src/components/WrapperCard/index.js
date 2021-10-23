import React from 'react';
import {Container} from './style'

export default function WrapperCard({children, ...props}) {
    return <Container {...props}>{children}</Container>
}