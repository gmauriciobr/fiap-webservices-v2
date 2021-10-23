import React from 'react';
import {Container} from './style'

export default function FormWrapper({children, ...props}) {
    return <Container {...props}>{children}</Container>
}