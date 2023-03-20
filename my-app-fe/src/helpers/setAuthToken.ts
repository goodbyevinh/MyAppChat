import { authenRequest } from "./api"


export const setAuthTokenRequest = (token : string) => {

    if (token) {
        authenRequest.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    } else {
        delete  authenRequest.defaults.headers.common['Authorization']
    }
} 

