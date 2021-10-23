import { combineReducers } from "redux";
import user from "./user";

const appReducer = combineReducers({ user });

export default (state, action) => {
  return appReducer(state, action);
};
