
import { AlertState, AlertActionTypes, ALERT_CLEAR, ALERT_SUCCESS, ALERT_ERROR } from './types';

const initState : AlertState = {
    type: null,
    message: null
}
 
export const alertReducer = (state: AlertState = initState, action : AlertActionTypes ) : AlertState => {
    switch(action.type) {
        case ALERT_CLEAR: {
            return {
                ...state,
                message: null,
                type: null
            }
        }
        case ALERT_SUCCESS: {
            return {
                ...state,
                message: action.payload.message,
                type: 'alert-success'
            }
        }
        case ALERT_ERROR: {
            return {
                ...state,
                message: action.payload.message,
                type: 'alert-danger'
            }
        }
        default: return state
    }
    
}