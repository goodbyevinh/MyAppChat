
import { AppState } from '..';

export const selectorAlert = (state : AppState) => state.alert
export const selectorAlertMessage = (state: AppState) => state.alert.message