import axios from "axios"
import { AppState, store } from "../store";
import { history } from "./history";
import { REFRESH_REQUEST, REFRESH_SUCCESS, REFRESH_FAILURE } from './../store/account/types';

export const publicRequest = axios.create({
    baseURL: process.env.REACT_APP_API_URL
})

publicRequest.interceptors.response.use ( 
    response => response ,
    err => {
        return Promise.reject(err);
    });


export const authenRequest = axios.create({
    baseURL: process.env.REACT_APP_API_URL
})

authenRequest.interceptors.response.use ( 
    response => response ,
    (err) => {
        console.log(err)
        const response = err.response
        const currentState = store.getState() as AppState
        const refreshToken : any = currentState.account.refreshToken
        
        if (response.status == 403 && response.data.status == 403 && !err.config._isRetryRequest) {
            store.dispatch({
                type: REFRESH_REQUEST
            })
            const form  = new FormData()
            form.append('token', refreshToken)
            publicRequest.postForm(`auth/refresh-token`, form).then(response => {
        
                store.dispatch({
                    type: REFRESH_SUCCESS,
                    payload: {
                        token: response.data.data.token,
                        refreshToken: response.data.data.refreshToken
                    }
                })

            }).catch(err =>{
                store.dispatch({
                    type: REFRESH_FAILURE
                })
                history.push('/login')
                console.log('err', err)
            }) 
        } 

        return Promise.reject(err);
    });
    