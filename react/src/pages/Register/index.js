import "react-datepicker/dist/react-datepicker.css"
import React, { useRef, useState, useEffect } from "react"
import { useDispatch } from "react-redux"

import DatePicker from "react-datepicker"
import { formatISO } from "date-fns"

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
  const inputRef = useRef(null)

  const [date, setDate] = useState("")
  const [comment, setComment] = useState("")
  const [disabled, setDisabled] = useState(true)

  const handleSubmit = () => {
    // const selectedDate = format(date, "yyyy-mm-ddTHH:mm:ssZ")
    const selectedDate = formatISO(date)

    dispatch(
      UserActions.createAndJustifyAppointmentRequest(
        selectedDate.replace("-03:00", "Z"),
        comment
      )
    )
  }

  useEffect(() => {
    if (date !== "" && comment !== "") {
      setDisabled(false)
    }
  }, [date, comment])

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
            disabled={disabled}
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
