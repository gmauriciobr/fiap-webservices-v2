import React from 'react';
import Textarea from '../../components/Textarea'

const argTypes = {}

const parameters = {}

export default {
    title: "General/Controles/Textarea",
    component: Textarea,
    argTypes,
    parameters
}

const Template  = args => <div style={{ width: '100%', height: '100vh', backgroundColor: '#000', paddingTop: '200px', paddingLeft: '450px'}}>
<Textarea {...args} style={{ width: '300px'}} />
</div>

export const Primary = Template.bind({})
Primary.args = {
    placeholder: "Digite aqui...",
}