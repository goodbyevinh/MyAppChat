import { CHANGE_TYPE_CHAT } from '../message/types'
import {AccountState, AccountActionTypes, LOGIN_FAILURE, LOGIN_REQUEST, LOGIN_SUCCESS, LOG_OUT, SIGNUP_REQUEST, 
    SIGNUP_FAILURE, SIGNUP_SUCCESS, LOADING, NOT_LOADING, ACCOUNT_REQUEST, ACCOUNT_SUCCESS, ACCOUNT_FAILURE, FRIEND_SUCCESS, FRIEND_FAILURE, GROUP_FAILURE, GROUP_SUCCESS, REFRESH_FAILURE, REFRESH_SUCCESS, REFRESH_REQUEST, UPDATE_STATUS_FRIENDS} from './types'


const initialState: AccountState = {
    user: null,
    loading: false,
    error: null,
    token: null,
    refreshToken: null,
    friends: [],
    groups: []
}
const accountReducer = (
    state: AccountState = initialState,
    action: AccountActionTypes 
) : AccountState => {
    switch (action.type) {
        case LOGIN_REQUEST : {
            return {...state, loading:true }
        } 
        case LOGIN_SUCCESS: {
            return {...state, loading: false ,error: null, token: action.payload.token, refreshToken: action.payload.refreshToken}
        }
        case LOGIN_FAILURE : {
            return {...state, loading: false, error: action.payload.error}
        }
        case SIGNUP_REQUEST : {
            return {...state, loading:true }
        } 
        case SIGNUP_SUCCESS: {
            return {...state, loading: false , error:null, token: action.payload.token, refreshToken: action.payload.refreshToken}
        }
        case SIGNUP_FAILURE : {
            return {...state, loading: false, error: action.payload.error}
        }
        case LOG_OUT: {
            return {...state, user: null, token: null, refreshToken: null, loading: false}
        }
        case LOADING: {
            return {
                ...state, loading: action.payload.loading
            }
        }
        case NOT_LOADING: {
            return {
                ...state, loading: action.payload.loading
            }
        }
        case ACCOUNT_REQUEST : {
            return {
                ...state, loading: true
            }
        } 
        case ACCOUNT_SUCCESS: {
            return {
                ...state, user: action.payload.user
            }
        }
        case ACCOUNT_FAILURE: {
            return {
                ...state, user: null, error: action.payload.error
            }
        }
        case FRIEND_SUCCESS: {
            return {
                ...state, error: null, friends: action.payload.friends
            }
        }
        case FRIEND_FAILURE: {
            
            return {
                ...state, friends: [], error: action.payload.error
            }
        }
        case GROUP_SUCCESS: {
            
            return {
                ...state, error: null, groups: action.payload.groups
            }
        }
        case GROUP_FAILURE: {
            
            return {
                ...state, groups: [], error: action.payload.error
            }
        }
        case REFRESH_REQUEST: {
            return {
                ...state, loading: true
            }
        }
        case REFRESH_FAILURE: {
            return {
                ...initialState
            }
        }
        case REFRESH_SUCCESS: {
            return {
                ...state, loading: false, token: action.payload.token ,  refreshToken: action.payload.refreshToken
            }
        }
        case UPDATE_STATUS_FRIENDS: {
   
            return {
                ...state, 
                friends: [
                    ...state.friends.filter(friend => friend.id !== action.payload.friend.id),
                    action.payload.friend
                ]
            }
        }
        default:
            return state
    }
}

export default accountReducer