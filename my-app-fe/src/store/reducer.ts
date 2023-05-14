
import { combineReducers } from 'redux';
import accountReducer from './account/reducers';
import storage from 'redux-persist/lib/storage';
import persistReducer from 'redux-persist/es/persistReducer';
import { alertReducer } from './alert/reducers';
import { messageReducer } from './message/reducers';

const persistConfig = {
    key: 'root',
    storage,
    blacklist: ['alert', 'message']
}

const rootReducer = combineReducers({
    account: accountReducer,
    alert: alertReducer,
    message: messageReducer
})
const persistedReducer = persistReducer(persistConfig, rootReducer)

export {persistedReducer, rootReducer} 

