import React from 'react';
import Button from '../../components/Button'

const argTypes = {}

const parameters = {}

export default {
    title: "General/Controles/Button",
    component: Button,
    argTypes,
    parameters
}

const Template  = args => <Button {...args}>{args.label}</Button>

export const Primary = Template.bind({})
Primary.args = {
    label: "Meu Botão",
    padding: false
}