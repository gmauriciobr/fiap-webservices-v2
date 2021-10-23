import { all, takeLatest } from "redux-saga/effects"
import { Types as UserTypes } from "../ducks/user"
import {
  getAppointments,
  login,
  createAppointment,
  createUser,
  createAndJustifyAppointment,
} from "./user"

export default function* rootSaga() {
  yield all([
    takeLatest(UserTypes.GET_APPOINTMENTS_REQUEST, getAppointments),
    takeLatest(UserTypes.LOGIN_REQUEST, login),
    takeLatest(UserTypes.CREATE_APPOINTMENT_REQUEST, createAppointment),
    takeLatest(UserTypes.CREATE_USER_REQUEST, createUser),
    takeLatest(
      UserTypes.CREATE_AND_JUSTIFY_APPOINTMENT_REQUEST,
      createAndJustifyAppointment
    ),
  ])
}
