import React from 'react';
import {Container} from './style'

export default function FormTitle({children, ...props}) {
    return <Container {...props}>{children}</Container>
}