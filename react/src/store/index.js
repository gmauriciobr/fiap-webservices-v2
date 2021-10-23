import { createStore, compose, applyMiddleware } from "redux";
import createSaga from "redux-saga";

import sagas from "./sagas";
import reducers from "./ducks";

const sagaMiddleware = createSaga();
const store = createStore(reducers, compose(applyMiddleware(sagaMiddleware)));

sagaMiddleware.run(sagas);

export default store;
