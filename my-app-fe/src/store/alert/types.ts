

export const ALERT_SUCCESS = 'ALERT_SUCCESS'
export const ALERT_ERROR = 'ALERT_ERROR'
export const ALERT_CLEAR = 'ALERT_CLEAR'

interface AlertSuccess  {
    type: typeof ALERT_SUCCESS;
    payload: {
        message: string;
    }
}

interface AlertError  {
    type: typeof ALERT_ERROR;
    payload: {
        message: string;
    }
}

interface AlertClear  {
    type: typeof ALERT_CLEAR;
}


export type AlertActionTypes = AlertSuccess | AlertError | AlertClear

export interface AlertState  {
    type: string | null;
    message: string | null;
}