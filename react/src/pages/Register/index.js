import "react-datepicker/dist/react-datepicker.css"
import React, { useRef, useState } from "react"
import { useDispatch } from "react-redux"
import { useHistory } from "react-router-dom"
import styled from "styled-components"
import DatePicker from "react-datepicker"
import { format } from "date-fns"

import ContainerMain from "../../components/ContainerMain"
import WrapperFlex from "../../components/WrapperFlex"
import FormWrapper from "../../components/FormWrapper"
import Form from "../../components/Form"
import FormTitle from "../../components/FormTitle"
import Textarea from "../../components/Textarea"
import Button from "../../components/Button"
import Input from "../../components/Input"

import { Creators as UserActions } from "../../store/ducks/user"

export default function Register() {
  const dispatch = useDispatch()
  const history = useHistory()
  const inputRef = useRef(null)

  const [date, setDate] = useState("")
  const [comment, setComment] = useState("")

  const handleSubmit = () => {
    const selectedDate = format(date, "yyyy-mm-dd")

    dispatch(UserActions.createAppointmentRequest(selectedDate, comment))
  }

  return (
    <FormWrapper>
      <FormTitle>Nova Entrada</FormTitle>
      <Form onSubmit={() => console.log("")}>
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
        <Textarea
          value={comment}
          placeholder="Justifique sua batida"
          onChange={(event) => setComment(event.target.value)}
        />
        <WrapperFlex
          style={{ width: "100%", justifyContent: "center", marginTop: "20px" }}
        >
          <Button
            padding
            style={{ width: "100%" }}
            onClick={() => handleSubmit()}
          >
            Salvar
          </Button>
        </WrapperFlex>
      </Form>
    </FormWrapper>
  )
}
