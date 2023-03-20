import {ALERT_ERROR, ALERT_SUCCESS, ALERT_CLEAR, AlertActionTypes} from  './types'

export const alertSuccess = (message: string) : AlertActionTypes => {
    return {
        type: ALERT_SUCCESS,
        payload: {message}
    }
}
export const alertClear = (): AlertActionTypes => {
    return {
        type: ALERT_CLEAR
    }
}
export const alertError = (message: string) : AlertActionTypes => {
    return {
        type: ALERT_ERROR,
        payload: {message}
    }
}

