import React from "react"
import { Redirect, Route, Switch } from "react-router-dom"
import ProtectedRoute from "./protectedRoute"
import Home from "../pages/Home"
import Register from "../pages/Register"
import Edit from "../pages/Edit"
import Login from "../pages/Login"
import RegisterUser from "../pages/RegisterUser"

const Routes = () => {
  return (
    <Switch>
      <ProtectedRoute exact path="/home" component={Home} />;
      <ProtectedRoute exact path="/register" component={Register} />;
      <ProtectedRoute exact path="/edit/:id" component={Edit} />;
      <Route exact path="/login" component={Login} />;
      <Route exact path="/register-user" component={RegisterUser} />;
      <Redirect from="/" exact to="/home" />;
    </Switch>
  )
}

export default Routes
