import "./reset.css"
import React, { useEffect, useState } from "react"
import { Router } from "react-router-dom"
import { CgLogOff } from "react-icons/cg"
import Routes from "./routes"
import history from "./services/history"
import { isAuthenticated, logout } from "./services/auth"

function App() {
  const [showIcon, setShowIcon] = useState(false)

  const handleLogout = () => {
    setShowIcon(false)
    logout()
  }

  useEffect(() => {
    if (isAuthenticated()) {
      console.log("entrou")
      setShowIcon(true)
    }
  }, [])

  return (
    <Router history={history}>
      {showIcon && (
        <div
          style={{
            display: "flex",
            width: "100%",
            padding: "15px 0",
            justifyContent: "flex-end",
            backgroundColor: "#000",
          }}
        >
          <div style={{ marginRight: "30px" }}>
            <CgLogOff
              color="#e91c5d"
              size="2rem"
              style={{ cursor: "pointer" }}
              onClick={() => handleLogout()}
            />
          </div>
        </div>
      )}
      <Routes />
    </Router>
  )
}

export default App
