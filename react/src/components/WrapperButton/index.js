import React from 'react';
import { Container } from './style';

export default function WrapperButton({children, ...props}) {
    return <Container {...props}>{children}</Container>
}
