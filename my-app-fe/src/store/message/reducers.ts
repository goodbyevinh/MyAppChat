
import { MessageActionTypes, MessageState, CHANGE_TYPE_CHAT, GET_MESSAGES, UPDATE_FRIENDS_IN_GROUP } from './types';

const initialState : MessageState = {
    typeChat: null,
    messages: [],
    isJoin: false,
    connected: false,
    loading: false,
    friends: []
}

export const messageReducer = (state = initialState, action : MessageActionTypes) : MessageState => {
    
    switch (action.type) {
        case 'JOIN_CHAT' : {
            return {
                ...state,
                isJoin: true, 
                typeChat: action.payload.typeChat
            }
        }
        case 'LEAVE_CHAT' : {
            return {
                ...state,
                isJoin: false,
                typeChat: null
            }
        }
        case 'SEND_MESSAGE':  {
            return {
                ...state
            }
        }
     
        case 'RECEIVE_MESSAGE': {
    
            return {
                ...state,
                messages: [
                    ...state.messages,
                    action.payload.message
                ]
            }
        }
        case 'CONNECT': {
            return {
                ...state,
                loading: true
            }
        }
        case 'CONNECTED': {
            return {
                ...state,
                connected: true,
                loading: false
            }
        }
        case 'UNCONNECTED': {
            return state
        }
        case CHANGE_TYPE_CHAT: {
            return {
                ...state, typeChat: action.payload.typeChat
            }
        }
        case GET_MESSAGES: {
            return {
                ...state, messages: action.payload.messages, friends: action.payload.friends
            }
        }
        case UPDATE_FRIENDS_IN_GROUP: {
            if (action.payload.friend.online ) {
                return {
                    ...state, friends: [
                        ...state.friends,
                        action.payload.friend
                    ]
                }

            } else {
                return {
                    ...state, 
                    friends: [
                        ...state.friends.filter(friend => friend.id !== action.payload.friend.id),
                    ]
                
                }
            }
            

        }
        default:
            return state
    }
}