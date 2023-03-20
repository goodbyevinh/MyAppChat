import React, { useEffect } from 'react'
import { selectorToken } from '../../store/account/selector'
import { connectedWebSocket, connectWebSocket, unConnectedWebSocket } from '../../store/message/action'
import { useDispatch, useSelector } from 'react-redux';
import { connect, stompClient } from '../../services';

export const Page404 = () => {

  const token = useSelector(selectorToken)
  const dispatch = useDispatch();

  useEffect(() => {
    
    connect(token, () => {
      dispatch(connectedWebSocket());

      stompClient.subscribe('/chatroom/public', onPublicMessageReceived);

    }, () => {
      dispatch(unConnectedWebSocket());
    });
  }, [])
  const onPublicMessageReceived = () => {

  }
  const onPrivateMessageReceived = () => {
    
  }

  return (
    <div>Page404</div>
  )
}

