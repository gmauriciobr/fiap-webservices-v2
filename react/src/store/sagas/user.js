import { call, put } from "redux-saga/effects"
import qs from "qs"
import { saveToken } from "../../services/auth"
import api from "../../services/api"
import history from "../../services/history"

import { Creators as UserActions } from "../ducks/user"

export function* login(action) {
  try {
    const { email, password } = action

    const response = yield call(api.post, "/login", {
      email,
      password,
    })

    if (response.status === 200) {
      saveToken(response.data.token)
      history.push("/home")
    }
  } catch (error) {
    throw new Error(error)
  }
}

export function* getAppointments(action) {
  try {
    const { initialDate, finalDate } = action

    const params = qs.stringify(
      { dataInicio: initialDate, dataFim: finalDate },
      { addQueryPrefix: true, skipNulls: true }
    )

    const response = yield call(api.get, `/ponto${params}`)

    if (response.status === 200) {
      yield put(UserActions.getAppointmentsSuccess(response.data))
    }
  } catch (error) {
    yield put(UserActions.getAppointmentsError())
  }
}

export function* createAppointment(action) {
  try {
    const { date, comment } = action

    const response = yield call(api.post, `/ponto`, { date, comment })

    if (response.status === 201) {
      yield put(UserActions.createAppointmentSuccess())
      history.push("/home")
    }
  } catch (error) {
    yield put(UserActions.getAppointmentsError())
  }
}

export function* createUser(action) {
  try {
    const { nome, email, password } = action

    const response = yield call(api.post, `/usuario`, { nome, email, password })

    if (response.status === 201) {
      yield put(UserActions.createUserSuccess())
      history.push("/login")
    }
  } catch (error) {
    yield put(UserActions.getAppointmentsError())
  }
}
