import { Friend } from "../account/types";
import { JOIN_CHAT, LEAVE_CHAT,Message, MessageUser, MessageActionTypes, SEND_MESSAGE, RECEIVE_MESSAGE, CHAT_PUBLIC, CHAT_PRIVATE, CHAT_GROUP, CONNECTED, UNCONNECTED, CONNECT, GET_MESSAGES, CHANGE_TYPE_CHAT, UPDATE_FRIENDS_IN_GROUP } from "./types";




export const joinChat = (typeChat : typeof CHAT_PUBLIC | typeof CHAT_PRIVATE | typeof CHAT_GROUP | null) : MessageActionTypes  => {
    return {
    
        type: JOIN_CHAT,
        payload: {
            typeChat: typeChat
        }
    }
}
export const leaveChat = () : MessageActionTypes => {
    return {
        type: LEAVE_CHAT,
    }
}

export const sendMessage = () : MessageActionTypes => {
    return {
        type: SEND_MESSAGE,

    }
}
export const receiveMessage = (message: Message) : MessageActionTypes => {
    return {
        type: RECEIVE_MESSAGE,
        payload: {
            message
        }
    }
}

export const connectWebSocket = () : MessageActionTypes  => {
    return {
        type: CONNECT

    }
}

export const connectedWebSocket = () : MessageActionTypes  => {
    return {
        type: CONNECTED

    }
}
export const unConnectedWebSocket = () : MessageActionTypes => {
    return {
        type: UNCONNECTED
    }
}
export const getMessages = ( messages: Array<Message>, friends: Array<Friend> = []) : MessageActionTypes => {
    return {
        type: GET_MESSAGES,
        payload: {
            friends,
            messages
        }
    }
}


export const changeTypeChat = (typeChat : typeof CHAT_PUBLIC | typeof CHAT_PRIVATE | typeof CHAT_GROUP | null  ) : MessageActionTypes => {
    return {
        type: CHANGE_TYPE_CHAT,
        payload: {
            typeChat
        }
    }
}
export const updateFriendGroup = (friend: Friend) : MessageActionTypes=> {
    return {
        type: UPDATE_FRIENDS_IN_GROUP ,
        payload: {
            friend
        }
    }
}



