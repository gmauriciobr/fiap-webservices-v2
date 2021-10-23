export const Types = {
  GET_APPOINTMENTS_REQUEST: "user/GET_APPOINTMENT_REQUEST",
  GET_APPOINTMENTS_SUCCESS: "user/GET_APPOINTMENT_SUCCESS",
  GET_APPOINTMENTS_ERROR: "user/GET_APPOINTMENT_ERROR",
  LOGIN_REQUEST: "user/LOGIN_REQUEST",
  LOGIN_SUCCESS: "user/LOGIN_SUCCESS",
  LOGIN_ERROR: "user/LOGIN_ERROR",
  CREATE_APPOINTMENT_REQUEST: "user/CREATE_APPOINTMENT_REQUEST",
  CREATE_APPOINTMENT_SUCCESS: "user/CREATE_APPOINTMENT_SUCCESS",
  CREATE_APPOINTMENT_ERROR: "user/CREATE_APPOINTMENT_ERROR",
  CREATE_AND_JUSTIFY_APPOINTMENT_REQUEST:
    "user/CREATE_AND_JUSTIFY_APPOINTMENT_REQUEST",
  CREATE_AND_JUSTIFY_APPOINTMENT_SUCCESS:
    "user/CREATE_AND_JUSTIFY_APPOINTMENT_SUCCESS",
  CREATE_AND_JUSTIFY_APPOINTMENT_ERROR:
    "user/CREATE_AND_JUSTIFY_APPOINTMENT_ERROR",
  CREATE_USER_REQUEST: "user/CREATE_USER_REQUEST",
  CREATE_USER_SUCCESS: "user/CREATE_USER_SUCCESS",
  CREATE_USER_ERROR: "user/CREATE_USER_ERROR",
}

const INITIAL_STATE = {
  data: [],
  loading: false,
  hasError: false,
}

export default function user(state = INITIAL_STATE, action) {
  switch (action.type) {
    case Types.GET_TRIAL_REQUEST:
      return { ...state, loading: true }

    case Types.GET_APPOINTMENTS_SUCCESS:
      return { ...state, loading: false, data: action.data }

    case Types.GET_APPOINTMENTS_ERROR:
      return { ...state, loading: false, hasError: true }

    case Types.LOGIN_REQUEST:
      return { ...state, loading: true }

    case Types.LOGIN_SUCCESS:
      return { ...state, loading: false }

    case Types.LOGIN_ERROR:
      return { ...state, loading: false, hasError: true }

    case Types.CREATE_APPOINTMENT_REQUEST:
      return { ...state, loading: true, hasError: false }

    case Types.CREATE_APPOINTMENT_SUCCESS:
      return { ...state, loading: false, hasError: false }

    case Types.CREATE_APPOINTMENT_ERROR:
      return { ...state, loading: false, hasError: true }

    case Types.CREATE_AND_JUSTIFY_APPOINTMENT_REQUEST:
      return { ...state, loading: true, hasError: false }

    case Types.CREATE_AND_JUSTIFY_APPOINTMENT_SUCCESS:
      return { ...state, loading: false, hasError: false }

    case Types.CREATE_AND_JUSTIFY_APPOINTMENT_ERROR:
      return { ...state, loading: false, hasError: true }

    case Types.CREATE_USER_REQUEST:
      return { ...state, loading: true, hasError: false }

    case Types.CREATE_USER_SUCCESS:
      return { ...state, loading: false, hasError: false }

    case Types.CREATE_USER_ERROR:
      return { ...state, loading: false, hasError: true }

    default:
      return state
  }
}

export const Creators = {
  getAppointmentsRequest: (initialDate, finalDate) => ({
    type: Types.GET_APPOINTMENTS_REQUEST,
    initialDate,
    finalDate,
  }),
  getAppointmentsSuccess: (data) => ({
    type: Types.GET_APPOINTMENTS_SUCCESS,
    data,
  }),
  getAppointmentsError: () => ({
    type: Types.GET_APPOINTMENTS_ERROR,
  }),
  loginRequest: (email, password) => ({
    type: Types.LOGIN_REQUEST,
    email,
    password,
  }),
  loginSuccess: () => ({ type: Types.LOGIN_SUCCESS }),
  loginError: () => ({ type: Types.LOGIN_ERROR }),
  createAppointmentRequest: () => ({
    type: Types.CREATE_APPOINTMENT_REQUEST,
  }),
  createAppointmentSuccess: () => ({
    type: Types.CREATE_APPOINTMENT_SUCCESS,
  }),
  createAppointmentError: () => ({
    type: Types.CREATE_APPOINTMENT_SUCCESS,
  }),
  createAndJustifyAppointmentRequest: (date, comment) => ({
    type: Types.CREATE_AND_JUSTIFY_APPOINTMENT_REQUEST,
    date,
    comment,
  }),
  createAndJustifyAppointmentSuccess: () => ({
    type: Types.CREATE_AND_JUSTIFY_APPOINTMENT_SUCCESS,
  }),
  createAndJustifyAppointmentError: () => ({
    type: Types.CREATE_AND_JUSTIFY_APPOINTMENT_ERROR,
  }),
  createUserRequest: (nome, email, password) => ({
    type: Types.CREATE_USER_REQUEST,
    nome,
    email,
    password,
  }),
  createUserSuccess: () => ({
    type: Types.CREATE_USER_SUCCESS,
  }),
  createUserError: () => ({
    type: Types.CREATE_USER_ERROR,
  }),
}
