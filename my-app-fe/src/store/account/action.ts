import { AccountActionTypes, LOGIN_REQUEST, LOGIN_SUCCESS, LOGIN_FAILURE, LOG_OUT, SIGNUP_REQUEST, SIGNUP_SUCCESS, SIGNUP_FAILURE,
    LOADING, NOT_LOADING, ACCOUNT_REQUEST, ACCOUNT_SUCCESS, ACCOUNT_FAILURE, FRIEND_SUCCESS, FRIEND_FAILURE, GROUP_FAILURE, GROUP_SUCCESS, UPDATE_STATUS_FRIENDS, Friend } from './types';
import { Dispatch } from "react"
import { userService } from './../../services/authService';
import { history } from '../../helpers';
import { alertError } from './../alert/action';
import { AlertActionTypes, ALERT_ERROR } from '../alert/types';
import { getUser } from './../../services/userService';
import { getFriends } from './../../services/friendService';
import { getGroups } from './../../services/groupService';



export const login  = (email: string , password: string, form: string) : any  => {
    return async(dispatch : Dispatch<AccountActionTypes | AlertActionTypes>) => {
        dispatch({
            type:  LOGIN_REQUEST,
            payload: {
                email: email,
                password: password
        
            }
        })

        try {
            const response = await userService.login({email, password});
            const {token , refreshToken }  = response
                
            dispatch({
                type:  LOGIN_SUCCESS,
                payload: {token, refreshToken }
            })
            history.push(form)

        } catch (error : any) {
            dispatch({
                type: LOGIN_FAILURE,
                payload: {
                    error: error.toString()
                }
            })
      
            dispatch(alertError('Đăng nhập thất bại'))
        }
    }
}


export const signup  = (email: string , password: string, fullname: string, avatar: File, form: string) : any  => {
    

    return async(dispatch : Dispatch<AccountActionTypes | AlertActionTypes> ) => {
        dispatch({
            type:  SIGNUP_REQUEST,
            payload: {
                email: email,
                password: password,
                fullname,
                avatar
            }
        })
        try {
            const response = await userService.signnup({email, password, fullname, avatar})
            const {token , refreshToken, account}  = response
                dispatch({
                    type:  SIGNUP_SUCCESS,
                    payload: {token, refreshToken}
                })
                history.push(form)
        }catch (error : any) {
            dispatch({
                type: SIGNUP_FAILURE,
                payload: {
                    error: error.toString()
                }
            })
            dispatch({
                type: ALERT_ERROR,
                payload: {
                    message: 'Không thể đăng ký'
                }
            })
        }
        
    }
}


export const userInit  =  () : any => {

    return async(dispatch : Dispatch<AccountActionTypes>) => {
        dispatch({
            type:  ACCOUNT_REQUEST
        })

        try {
            const response = await getUser()
            const user = response.data
      
         
            dispatch({
                type:  ACCOUNT_SUCCESS,
                payload: {user}
            })

        }catch (error : any) {
            dispatch({
                type: ACCOUNT_FAILURE,
                payload: {
                    error: error.toString()
                }
            })
        
            
        }
        
    }
}

export const friendInit  =  () : any => {

    return async(dispatch : Dispatch<AccountActionTypes>) => {
        try {
            const response  = await getFriends()
   
            dispatch({
                type: FRIEND_SUCCESS,
                payload: {
                    friends: response.data
                }
            })
        } catch (err: any) {
            dispatch({
                type: FRIEND_FAILURE,
                payload: {
                    error: err.toString()
                }
            })
 
        }
        
    }
}

export const groupInit  =  () : any => {

    return async(dispatch : Dispatch<AccountActionTypes>) => {
        try {
            const response  = await getGroups()
       
            dispatch({
                type: GROUP_SUCCESS,
                payload: {
                    groups: response.data
                }
            })
        } catch (err: any) {
            dispatch({
                type: GROUP_FAILURE,
                payload: {
                    error: err.toString()
                }
            })
  
        }
        
    }
}



export const logout = ():AccountActionTypes => {
    return {type: LOG_OUT}
}

export const loading = () : AccountActionTypes => {
    return  {
        type: LOADING,
        payload: {
            loading: true
        }
    }
}

export const notLoading = () : AccountActionTypes => {
    return  {
        type: LOADING,
        payload: {
            loading: false
        }
    }
}
export const updateStatusFriend = (friend: Friend) : AccountActionTypes => {

    return {
        type: UPDATE_STATUS_FRIENDS,
        payload: {
            friend
        }
    }

}
