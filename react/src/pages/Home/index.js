import "./customStyles.css"
import "react-datepicker/dist/react-datepicker.css"
import React, { useEffect, useState } from "react"
import { useDispatch, useSelector } from "react-redux"
import { useHistory } from "react-router-dom"
import styled from "styled-components"
import { FaPlus } from "react-icons/fa"
import { MdArrowDownward } from "react-icons/md"
import { format, subDays } from "date-fns"
import { Collapse } from "react-collapse"

import Button from "../../components/Button"
import ContainerMain from "../../components/ContainerMain"
import Wrapper from "../../components/Wrapper"
import WrapperFlex from "../../components/WrapperFlex"
import WrapperCard from "../../components/WrapperCard"
import Card from "../../components/Card"
import WrapperButton from "../../components/WrapperButton"
import ItemHeader from "../../components/ItemHeader"
import { Creators as UserActions } from "../../store/ducks/user"
import ContentWrapper from "../../components/ContentWrapper"
import Content from "../../components/Content"

export default function Home() {
  const dispatch = useDispatch()
  const history = useHistory()

  const { data } = useSelector((state) => state.user)

  const [collapse, setCollapse] = useState(false)

  const handleAppointNow = () => {
    const date = format(new Date(), "yyyy-MM-dd")

    dispatch(UserActions.createAppointmentRequest(date))
  }

  useEffect(() => {
    const initialDate = format(subDays(new Date(), 30), "yyyy-MM-dd")
    const finalDate = format(new Date(), "yyyy-MM-dd")

    dispatch(UserActions.getAppointmentsRequest(initialDate, finalDate))
  }, [])

  return (
    <ContainerMain>
      <Wrapper>
        {!data.length ? (
          <WrapperFlex
            style={{
              width: "100%",
              alignItems: "center",
              padding: "15px 30px",
              flexDirection: "column",
            }}
          >
            <h1 style={{ color: "#fff" }}>
              Notamos que você ainda não tem batidas
            </h1>
            <Button
              style={{ width: "300px", marginTop: "15px" }}
              onClick={() => handleAppointNow()}
            >
              <FaPlus /> Bater ponto agora
            </Button>
          </WrapperFlex>
        ) : (
          <>
            <WrapperFlex
              style={{ justifyContent: "center", padding: "15px 30px" }}
            >
              <Button onClick={() => handleAppointNow()}>
                <FaPlus /> Bater ponto agora
              </Button>
            </WrapperFlex>

            <WrapperCard>
              {data.map((d) => (
                <Card key={d.id}>
                  <WrapperButton>
                    <Button onClick={() => history.push("/register")}>
                      <FaPlus /> Adicionar Batida
                    </Button>
                  </WrapperButton>
                  <ItemHeader>Data: {d.data}</ItemHeader>
                  <ItemHeader>
                    Horas trabalhadas: {d.horasTrabalhadas}
                  </ItemHeader>
                  <ItemHeader>
                    Quantidade de Marcações: {d.quantidadeMarcacoes}
                  </ItemHeader>
                  <div style={{ cursor: "pointer" }}>
                    <MdArrowDownward onClick={() => setCollapse(!collapse)} />
                  </div>
                  <Collapse isOpened={collapse}>
                    <ContentWrapper>
                      {d.itens.map((item) => (
                        <Content key={item.id}>
                          <ItemHeader>
                            Marcação:{" "}
                            {format(new Date(item.marcacao), "dd-MM-yyy:HH:mm")}
                          </ItemHeader>
                          <ItemHeader>
                            Observação: {item.observacao || "Sem observações"}
                          </ItemHeader>
                        </Content>
                      ))}
                    </ContentWrapper>
                  </Collapse>
                </Card>
              ))}
            </WrapperCard>
          </>
        )}
      </Wrapper>
    </ContainerMain>
  )
}
