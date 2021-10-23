import "react-datepicker/dist/react-datepicker.css"
import React, { useState } from "react"
import { useDispatch } from "react-redux"
import { useHistory } from "react-router-dom"
import WrapperFlex from "../../components/WrapperFlex"
import FormWrapper from "../../components/FormWrapper"
import Form from "../../components/Form"
import FormTitle from "../../components/FormTitle"
import Input from '../../components/Input'
import { Creators as UserActions } from "../../store/ducks/user"
import Button from "../../components/Button"

export default function Login() {
  const history = useHistory()
  const dispatch = useDispatch()

  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")

  const handleSubmit = () => {
    dispatch(UserActions.loginRequest(email, password))
  }

  return (
    <FormWrapper>
      <FormTitle>Acesse sua conta</FormTitle>
      <Form onSubmit={() => console.log("validating...")}>
        <Input
          name="email"
          placeholder="digite o seu email"
          value={email}
          onChange={(event) => setEmail(event.target.value)}
        />
        <Input
          name="password"
          type="password"
          placeholder="digite sua senha"
          value={password}
          onChange={(event) => setPassword(event.target.value)}
        />
        <p onClick={() => history.push("/register-user")} style={{ color: "#fff", cursor: 'pointer'}}>
          Não tem uma conta? Se cadastre aqui!
        </p>
        <WrapperFlex
          style={{ width: "100%", justifyContent: "center", marginTop: "20px" }}
        >
          <Button padding style={{ width: "100%" }} onClick={() => handleSubmit()}>
            Entrar
          </Button>
        </WrapperFlex>
      </Form>
    </FormWrapper>
  )
}
