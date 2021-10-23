import React from 'react';
import Input from '../../components/Input'

const argTypes = {}

const parameters = {}

export default {
    title: "General/Controles/Input",
    component: Input,
    argTypes,
    parameters
}

const Template  = args => <div style={{ width: '100%', height: '100vh', backgroundColor: '#000', paddingTop: '200px', paddingLeft: '450px'}}>
<Input {...args} style={{ width: '300px'}} />
</div>

export const Primary = Template.bind({})
Primary.args = {
    placeholder: "Digite aqui...",
    type: 'text'
}