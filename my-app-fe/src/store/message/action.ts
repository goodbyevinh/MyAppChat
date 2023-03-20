import { JOIN_CHAT, LEAVE_CHAT,Message, MessageUser, MessageActionTypes, SEND_MESSAGE, RECEIVE_MESSAGE, CHAT_PUBLIC, CHAT_PRIVATE, CHAT_GROUP, CONNECTED, UNCONNECTED, CONNECT, GET_MESSAGES, CHANGE_TYPE_CHAT } from "./types";




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
export const getMessages = ( messages: Array<Message>) : MessageActionTypes => {
    return {
        type: GET_MESSAGES,
        payload: {
        
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



