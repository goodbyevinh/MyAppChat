
import {applyMiddleware, combineReducers, compose, createStore} from 'redux'
import thunkMiddleware from 'redux-thunk'
import {persistedReducer, rootReducer} from './reducer';
import { persistStore } from 'redux-persist';
import { setAuthTokenRequest } from './../helpers/setAuthToken';




export type AppState = ReturnType<typeof rootReducer>



declare global {
    interface Window {
        __REDUX_DEVTOOLS_EXTENSION_COMPOSE__?: typeof compose
    }
}
const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose

const configureStore = () => {
    const middlewares = [thunkMiddleware];
    const middlewareEnhancer = applyMiddleware(...middlewares);

    return createStore(persistedReducer, composeEnhancers(middlewareEnhancer))
}
const store = configureStore();
const persistedStore = persistStore(store) 

let currentState  = store.getState() as AppState;
store.subscribe ( () => {
    let previousState = currentState;
    currentState = store.getState() as AppState;
    if (previousState.account.token !== currentState.account.token) {
        const token = currentState.account.token;
        if (token) {
            setAuthTokenRequest(token)
        }
    }
})

export {
    store,
    persistedStore
}


