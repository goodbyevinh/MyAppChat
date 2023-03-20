
export const LOGIN_REQUEST = 'LOGIN_REQUEST'
export const LOGIN_SUCCESS = 'LOGIN_SUCCESS'
export const LOGIN_FAILURE = 'LOGIN_FAILURE'
export const SIGNUP_REQUEST = 'SIGNUP_REQUEST'
export const SIGNUP_SUCCESS = 'SIGNUP_SUCCESS'
export const SIGNUP_FAILURE = 'SIGNUP_FAILURE'
export const ACCOUNT_REQUEST = 'ACCOUNT_REQUEST'
export const ACCOUNT_SUCCESS = 'ACCOUNT_SUCCESS'
export const ACCOUNT_FAILURE = 'ACCOUNT_FAILURE'

export const LOG_OUT = 'LOG_OUT'
export const LOADING = 'LOADING'
export const NOT_LOADING = 'NOT_LOADING'

export const FRIEND_SUCCESS = 'FRIEND_SUCCESS'
export const FRIEND_FAILURE = 'FRIEND_FAILURE'

export const UPDATE_STATUS_FRIENDS = 'UPDATE_STATUS_FRIENDS'
export const UPDATE_FRIENDS_IN_GROUP = 'UPDATE_FRIENDS_IN_GROUP'

export const GROUP_SUCCESS = 'GROUP_SUCCESS'
export const GROUP_FAILURE = 'GROUP_FAILURE'

export const REFRESH_REQUEST = 'REFRESH_REQUEST'
export const REFRESH_SUCCESS = 'REFRESH_SUCCESS'
export const REFRESH_FAILURE = 'REFRESH_FAILURE'

export const ONLINE = 'ONLINE'


export interface AuthenticateUser  {
    id: number  ;
    name : string  ;
    email: string  ;
    avatar: string ;
    oauth2: boolean ;
}
export interface Friend {
    id: number;
    name: string;
    avatar: string;
    oauth2: boolean;
    email: string;
    online: boolean
}
export interface Group {
    id: number;
    name: string;
}


export interface AccountState {
    user: AuthenticateUser | null;
    friends: Array<Friend>;
    groups: Array<Group>;
    loading: boolean;
    error: string | null;
    token: string  | null;
    refreshToken: string | null;
}



export interface LoginRequest  {
    type: typeof LOGIN_REQUEST;
    payload: {
        email: string;
        password: string;

    }
}
export interface SignupRequest  {
    type: typeof SIGNUP_REQUEST;
    payload: {
        email: string;
        password: string;
        fullname: string;
        avatar: File;

    }
}
export interface AccountRequest {
    type: typeof ACCOUNT_REQUEST;
}

export interface LoginSuccess  {
    type: typeof LOGIN_SUCCESS;
    payload: {
        token: string;
        refreshToken: string;
    }
}
export interface SignupSuccess  {
    type: typeof SIGNUP_SUCCESS;
    payload: {
        token: string;
        refreshToken: string;
    }
}
export interface AccountSuccess  {
    type: typeof ACCOUNT_SUCCESS;
    payload: {
        user: AuthenticateUser;
    }
}

export interface LoginFailure  {
    type: typeof LOGIN_FAILURE;
    payload: {
        error: string;
    }
}
export interface SignupFailure  {
    type: typeof SIGNUP_FAILURE;
    payload: {
        error: string;
    }
}
export interface AccountFailure  {
    type: typeof ACCOUNT_FAILURE;
    payload: {
        error: string;
    }
}
export interface Logout  {
    type: typeof LOG_OUT
}
export interface Loading {
    type: typeof LOADING,
    payload: {
        loading: boolean;
    }
}
export interface NotLoading {
    type: typeof NOT_LOADING,
    payload: {
        loading: boolean;
    }
}
export interface FriendSuccess {
    type: typeof FRIEND_SUCCESS;
    payload: {
        friends: Array<Friend>;
    }
}
export interface GroupSuccess {
    type: typeof GROUP_SUCCESS;
    payload: {
        groups: Array<Group>;
    }
}
export interface FriendFailure {
    type: typeof FRIEND_FAILURE
    payload: {
        error: string;
    }
}
export interface GroupFailure {
    type: typeof GROUP_FAILURE
    payload: {
        error: string;
    }
}
export interface RefreshTokenRequest {
    type: typeof REFRESH_REQUEST;
}
export interface RefreshTokenFailure {
    type: typeof REFRESH_FAILURE;
}
export interface RefreshTokenSuccess {
    type: typeof REFRESH_SUCCESS;
    payload: {
        token: string;
        refreshToken: string;
    }
}
interface UpdateStatusFriend {
    type: typeof UPDATE_STATUS_FRIENDS,
    payload: {
        friend: Friend
    }
}

export type AccountActionTypes =  LoginRequest 
| LoginFailure 
| LoginSuccess 
| Logout 
| SignupFailure 
| SignupRequest 
| SignupSuccess 
| Loading 
| NotLoading
| AccountRequest
| AccountSuccess
| AccountFailure
| GroupSuccess
| FriendSuccess
| FriendFailure
| GroupFailure
| RefreshTokenRequest
| RefreshTokenFailure
| RefreshTokenSuccess
| UpdateStatusFriend



