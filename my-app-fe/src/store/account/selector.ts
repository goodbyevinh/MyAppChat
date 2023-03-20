import { AppState } from '..';
import { AuthenticateUser } from './types';


export const selectorLoadingAccount = (state : AppState) => state.account.loading
export const selectorToken = (state : AppState) => state.account.token
export const selectorRefreshToken = (state : AppState) => state.account.refreshToken
export const selectorAvatar = (state : AppState) => state.account.user?.avatar
export const selectorName = (state : AppState) => state.account.user?.name
export const selectorEmail = (state : AppState) => state.account.user?.email
export const selectorUser = (state : AppState) => state.account.user
export const selectorFriends = (state : AppState) => state.account.friends
export const selectorGroups = (state : AppState) => state.account.groups
export const selectorOauth2 = (state: AppState) => state.account.user?.oauth2
