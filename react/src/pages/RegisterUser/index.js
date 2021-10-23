import "react-datepicker/dist/react-datepicker.css"
import React, { useState } from "react"
import { useDispatch } from "react-redux"
import styled from "styled-components"

import WrapperFlex from "../../components/WrapperFlex"
import FormWrapper from "../../components/FormWrapper"
import Form from '../../components/Form'
import FormTitle from "../../components/FormTitle"
import Button from "../../components/Button"
import Input from '../../components/Input'

import { Creators as UserActions } from "../../store/ducks/user"

export default function RegisterUser() {
  const dispatch = useDispatch()

  const [name, setName] = useState("")
  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")

  const handleSubmit = () => {
    dispatch(UserActions.createUserRequest(name, email, password))
  }

  return (
    <FormWrapper>
      <FormTitle>Crie sua conta</FormTitle>
      <Form onSubmit={() => console.log("")}>
        <Input
          name="nome"
          placeholder="digite o seu nome"
          onChange={(event) => setName(event.target.value)}
        />
        <Input
          name="email"
          placeholder="digite o seu email"
          onChange={(event) => setEmail(event.target.value)}
        />
        <Input
          name="password"
          type="password"
          placeholder="digite sua senha"
          onChange={(event) => setPassword(event.target.value)}
        />
        <WrapperFlex
          style={{ width: "100%", justifyContent: "center", marginTop: "20px" }}
        >
          <Button padding style={{ width: "100%" }} onClick={() => handleSubmit()}>
            Salvar
          </Button>
        </WrapperFlex>
      </Form>
    </FormWrapper>
  )
}
