export const JOIN_CHAT = 'JOIN_CHAT'
export const SEND_MESSAGE = 'SEND_MESSAGE'
export const RECEIVE_MESSAGE = 'RECEIVE_MESSAGE'
export const LEAVE_CHAT = 'LEAVE_CHAT'
export const CONNECTED = 'CONNECTED'
export const UNCONNECTED = 'UNCONNECTED'
export const CONNECT = 'CONNECT'
export const GET_MESSAGES = 'GET_MESSAGES'
export const CHANGE_TYPE_CHAT = 'CHANGE_TYPE_CHAT'

export const CHAT_PUBLIC = 'CHAT_PUBLIC'
export const CHAT_PRIVATE = 'CHAT_PRIVATE'
export const CHAT_GROUP = 'CHAT_GROUP'

export const TEXT = 'TEXT'
export const VIDEO = 'VIDEO'
export const IMAGE = 'IMAGE'
export const JOIN = 'JOIN'
export const MESSAGE = 'MESSAGE'
export const LEAVE = 'LEAVE'

export interface MessageUser  {
    id?: number;
    email: string | undefined | null;
    name: string | undefined | null;
    avatar?: string ;
    oauth2?: boolean | undefined;
}

export interface Message {
    sender?: MessageUser | undefined;
    receiver?: MessageUser | undefined;
    content: string | undefined;
    date: string | undefined;
    status:typeof LEAVE |typeof JOIN |typeof MESSAGE | null;
    typeMessage: typeof TEXT | typeof VIDEO | typeof IMAGE  ;
}
export interface MessageState {
    typeChat: typeof CHAT_PUBLIC | typeof CHAT_PRIVATE | typeof CHAT_GROUP | null ;
    messages: Array<Message>;
    isJoin: boolean;
    connected: boolean;
    loading: boolean;
}   

interface ConnectWebSocket {
    type: typeof CONNECT;
}
interface ConnectedWebSocket {
    type: typeof CONNECTED;
}
interface unConnectedWebSocket {
    type: typeof UNCONNECTED;
}

interface JoinChat  {
    type: typeof JOIN_CHAT,
    payload: {
        typeChat: typeof CHAT_PUBLIC | typeof CHAT_PRIVATE | typeof CHAT_GROUP | null;
    }
}
interface SendMessage  {
    type: typeof SEND_MESSAGE;
}
interface ReceiveMessage  {
    type: typeof RECEIVE_MESSAGE;
    payload: {
        message: Message
    };
}

interface GetMessages {
    type: typeof GET_MESSAGES;
    payload: {

        messages: Array<Message>;
    }
}

interface LeaveChat  {
    type: typeof LEAVE_CHAT;

}
interface ChangeTypeChat {
    type: typeof CHANGE_TYPE_CHAT;
    payload : {
        typeChat: typeof CHAT_PUBLIC | typeof CHAT_PRIVATE | typeof CHAT_GROUP | null;
    }
}


export type MessageActionTypes = JoinChat
                                |SendMessage
                                |LeaveChat
                                |ReceiveMessage
                                |ConnectedWebSocket
                                |unConnectedWebSocket
                                |ConnectWebSocket
                                |GetMessages
                                |ChangeTypeChat