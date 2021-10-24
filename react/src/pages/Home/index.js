import { format, subDays } from "date-fns";
import React, { useEffect, useState } from "react";
import { Collapse } from "react-collapse";
import "react-datepicker/dist/react-datepicker.css";
import { FaPlus } from "react-icons/fa";
import { MdArrowDownward } from "react-icons/md";
import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import Button from "../../components/Button";
import Card from "../../components/Card";
import ContainerMain from "../../components/ContainerMain";
import Content from "../../components/Content";
import ContentWrapper from "../../components/ContentWrapper";
import ItemHeader from "../../components/ItemHeader";
import Wrapper from "../../components/Wrapper";
import WrapperCard from "../../components/WrapperCard";
import WrapperFlex from "../../components/WrapperFlex";
import { Creators as UserActions } from "../../store/ducks/user";
import "./customStyles.css";

export default function Home() {
  const dispatch = useDispatch();
  const history = useHistory();

  const { data } = useSelector((state) => state.user);

  const [collapse, setCollapse] = useState(false);
  const [activeId, setActiveId] = useState(null);

  const handleAppointNow = () => {
    const date = format(new Date(), "yyyy-MM-dd");

    dispatch(UserActions.createAppointmentRequest(date));
  };

  useEffect(() => {
    const initialDate = format(subDays(new Date(), 30), "yyyy-MM-dd");
    const finalDate = format(new Date(), "yyyy-MM-dd");

    dispatch(UserActions.getAppointmentsRequest(initialDate, finalDate));
  }, []);

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
            <Button
              style={{ width: "300px", marginTop: "15px" }}
              onClick={() => history.push("/register")}
            >
              <FaPlus /> Adicionar Batida
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
              <Button
                style={{ marginLeft: "15px" }}
                onClick={() => history.push("/register")}
              >
                <FaPlus /> Adicionar Batida
              </Button>
            </WrapperFlex>

            <WrapperCard>
              {data.map((d) => (
                <Card key={d.id}>
                  <ItemHeader>
                    Data:
                    {` ${d.data.substring(
                      d.data.length,
                      d.data.length - 2
                    )}/${d.data.substring(
                      d.data.length - 3,
                      d.data.length - 5
                    )}/${d.data.substring(0, 4)}`}
                  </ItemHeader>
                  <ItemHeader>
                    Horas trabalhadas: {d.horasTrabalhadas}
                  </ItemHeader>
                  <ItemHeader>
                    Quantidade de Marcações: {d.quantidadeMarcacoes}
                  </ItemHeader>
                  <div style={{ cursor: "pointer" }}>
                    <MdArrowDownward
                      onClick={() => {
                        setActiveId(d.data);
                        setCollapse(!collapse);
                      }}
                    />
                  </div>
                  <Collapse isOpened={collapse && activeId === d.data}>
                    <ContentWrapper>
                      {d.itens.map((item) => (
                        <Content key={item.id}>
                          <ItemHeader>
                            Marcação:{" "}
                            {format(
                              new Date(item.marcacao),
                              "dd/MM/yyyy - HH:mm"
                            )}
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
  );
}
