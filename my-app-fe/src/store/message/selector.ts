
import { AppState } from '..';

export const selectorTypeChat = (state : AppState) => state.message.typeChat
export const selectorMessages = (state : AppState) => state.message.messages
export const selectorFriendInGroup = (state : AppState) => state.message.friends