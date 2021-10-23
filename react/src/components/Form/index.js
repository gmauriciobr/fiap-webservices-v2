import React from 'react';
import {Container} from './style'

export default function Form({children, ...props}) {
    return <Container {...props}>{children}</Container>
}