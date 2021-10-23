import axios from "axios"
import history from "./history"
import { getToken, logout } from "./auth"

const { REACT_APP_BASEURL_API } = process.env

const api = axios.create({
  baseURL: REACT_APP_BASEURL_API,
  headers: { "Access-Control-Allow-Origin": "*" },
})

const redirectUser = (url) => {
  history.push(url)
}

api.interceptors.request.use(async (config) => {
  const token = getToken()

  if (token) config.headers.Authorization = `Bearer ${token}`

  return config
})

api.interceptors.response.use(
  async (response) => {
    return response
  },
  async function (error) {
    if (error.response.status === 401) {
      logout()
      redirectUser("/login")
    }

    return error.response
  }
)

export default api
