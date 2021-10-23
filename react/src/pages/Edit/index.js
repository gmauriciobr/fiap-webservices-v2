import "react-datepicker/dist/react-datepicker.css"
import React, { useRef, useState } from "react"
import { useHistory } from "react-router-dom"
import styled from "styled-components"
import DatePicker from "react-datepicker"
import WrapperFlex from "../../components/WrapperFlex"
import FormWrapper from '../../components/FormWrapper'
import Form from '../../components/Form'
import FormTitle from '../../components/FormTitle'
import Textarea from '../../components/Textarea'
import Button from '../../components/Button'
import Input from '../../components/Input'

export default function Edit() {
  const history = useHistory()
  const inputRef = useRef(null)
  const [date, setDate] = useState("")

  return (
    <FormWrapper>
      <FormTitle>Edição de Entrada</FormTitle>
      <Form>
        <DatePicker
          ref={inputRef}
          showTimeSelect
          name="batida"
          selected={date}
          onChange={(event) => setDate(event)}
          dateFormat="dd/MM/yyyy h:mm aa"
          locale="pt-BR"
          customInput={<Input />}
        />
        <Textarea placeholder="Justifique sua batida" />
        <WrapperFlex
          style={{ width: "100%", justifyContent: "center", marginTop: "20px" }}
        >
          <Button padding style={{ width: "100%" }} onClick={() => history.push("/")}>
            Salvar
          </Button>
        </WrapperFlex>
      </Form>
    </FormWrapper>
  )
}
