import React from 'react';
import FormTitle from '../../components/FormTitle'

const argTypes = {}

const parameters = {}

export default {
    title: "General/Controles/FormTitle",
    component: FormTitle,
    argTypes,
    parameters
}

const Template  = args =><div style={{ width: '100%', height: '100vh', backgroundColor: '#000', paddingTop: '200px'}}>
<FormTitle {...args}>{args.text}</FormTitle>
</div>

export const Primary = Template.bind({})
Primary.args = {
    text: "Meu título",
}