import {Client, over} from 'stompjs';
import SockJS from 'sockjs-client';
import { connectWebSocket, connectedWebSocket, unConnectedWebSocket } from '../store/message/action';
import { Dispatch } from 'react';
import { CHAT_PRIVATE, CHAT_PUBLIC, MessageActionTypes } from './../store/message/types';

import * as authenRequest from "./request/authenRequest";
import { history } from '../helpers';
import { AnyAction } from 'redux';


export var stompClient : Client ;

export const connect = (token: string | null, onConnected: any, onError: any) =>{
    let Sock = new SockJS(`${process.env.REACT_APP_WEBSOCKET}?token=${token}`);

    stompClient = over(Sock);
    
    stompClient.connect({},onConnected, onError);
  
}

export const getPublicMessage = async() => {
    try {
        const response = await authenRequest.get('message/public');
        return response.data
    } catch (error) {
        console.log(error)
    }

}
export const getPrivateMessage = async(email: string | null) => {
    try {
        const response = await authenRequest.get(`message/private/${email}`);
        return response.data
    } catch (error) {
        console.log(error)
    }

}
export const joinPublicChatRoom = () => {
    const message = {
        status: 'JOIN',
        typeChat: CHAT_PUBLIC
    }
    stompClient.send('/app/public-message', {}, JSON.stringify(message))
}
export const joinPrivateChat = (receiverEmail: string) => {
    const message = {
      status: 'JOIN',
      typeChat: CHAT_PRIVATE,
      receiver: {
        email: receiverEmail
      }

    }
    stompClient.send('/app/private-message', {}, JSON.stringify(message))
}
export const joinGroupChat = () => {
    // const message = {
    //   status: 'JOIN',
    //   typeChat: CHAT_GROUP
    // }
    // stompClient.send('/app/message', {}, JSON.stringify(message))
}










