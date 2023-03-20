import React, { useEffect, useState, ChangeEvent, useRef, LegacyRef } from 'react'
import styles from './Chat.module.scss';
import classNames from 'classnames/bind';
import { Button, Form, Input, message, Upload, UploadFile, UploadProps } from 'antd'
import { SendOutlined, UploadOutlined } from '@ant-design/icons'
import Message from '../../components/Message/Message';
import { useDispatch, useSelector } from 'react-redux';
import { selectorEmail, selectorOauth2, selectorToken } from '../../store/account/selector';
import { getPublicMessage, stompClient, connect, joinPublicChatRoom, joinPrivateChat, joinGroupChat, getPrivateMessage } from './../../services/messageService';
import { sendMessage, joinChat, getMessages, receiveMessage, connectWebSocket, connectedWebSocket, unConnectedWebSocket, leaveChat } from './../../store/message/action';
import { CHAT_PUBLIC, TEXT, VIDEO, IMAGE, CHAT_PRIVATE, CHAT_GROUP, Message as MessageProps, JOIN, MESSAGE, LEAVE } from './../../store/message/types';
import { selectorMessages, selectorTypeChat } from '../../store/message/selector';
import { selectorName, selectorFriends, selectorGroups, selectorUser } from './../../store/account/selector';
import { userInit, loading, friendInit } from '../../store/account/action';
import { notLoading, groupInit, updateStatusFriend } from './../../store/account/action';
import { getAvatar, getFriends, getGroups } from '../../services';
import { useSearchParams } from 'react-router-dom';
import swal  from 'sweetalert';
import { type } from 'os';
import { uploadImage, getImage } from './../../services/fileService';
import { ONLINE, UPDATE_FRIENDS_IN_GROUP, UPDATE_STATUS_FRIENDS } from '../../store/account/types';





const cx = classNames.bind(styles)


type InputProps = {
  typeMessage: typeof TEXT | typeof VIDEO | typeof IMAGE;
  content: string;
  file: UploadFile<any> | null;

}

export function Chat() {

  const messagesEndRef = useRef<null | HTMLDivElement>(null)

  const scrollToBottom = () => {

    messagesEndRef.current?.scrollTo({
      behavior: 'smooth',
      top: messagesEndRef.current.scrollHeight
    })
  }

  const [input, SetInput] = useState<InputProps>({
    typeMessage: TEXT,
    content: '',
    file: null,
  })

  const dispatch = useDispatch();
  const token = useSelector(selectorToken)
  const typeChat = useSelector(selectorTypeChat)
  const name: string | undefined = useSelector(selectorName);
  const email: string | undefined = useSelector(selectorEmail);
  const messages: Array<MessageProps> = useSelector(selectorMessages);
  const isOauth2: boolean | undefined = useSelector(selectorOauth2)
  const [searchParam, setSearchParam] = useSearchParams();
  const [fileList, setfileList] = useState<UploadFile[]>([])

  useEffect(() => {
    initChat()
  }, [])


  useEffect(() => {
    scrollToBottom()
  }, [messages]);
  const initChat = async () => {
    dispatch(loading())
    await dispatch(userInit())
    await dispatch(friendInit())
    await dispatch(groupInit())
    dispatch(connectWebSocket());
    connect(token, () => {
      dispatch(connectedWebSocket());
      handleJoinRoom();
      stompClient.subscribe('/chatroom/public', onPublicMessageReceived);
      stompClient.subscribe(`/user/${email}/private`, onPrivateMessageReceived);

    }, () => {
      dispatch(unConnectedWebSocket());
    });
    dispatch(notLoading())
  }

  const handleJoinRoom = () => {
    const type = searchParam.get("typechat")
    const receiverEmail = searchParam.get('email')
    if (type === 'public') {
      joinPublicChatRoom()

    } else if (type === 'private' && receiverEmail) {
      if (receiverEmail) {
        stompClient.subscribe(`/user/${receiverEmail}/private`, onPrivateMessageReceived);
        joinPrivateChat(receiverEmail)
      }

      
    } else if (type === 'group') {
      joinGroupChat()
    } else {
      dispatch(leaveChat())
    }
  }

  const onPublicMessageReceived = async (messageResponse: any) => {
    
    const data = JSON.parse(messageResponse.body)
  

    switch (data.status) {
      case JOIN: {

        dispatch(joinChat(data.typeChat))
        const response = await getPublicMessage()

        dispatch(getMessages(response.data.messages))

        break;
      }
      case MESSAGE: {
        const response = await getAvatar(data.sender.email)

        data.sender.avatar = response.image
        data.sender.oauth2 = response.oauth2
        
        if (data.typeMessage === IMAGE) {

          const responseImage = await getImage(data.content)
        
          data.content  = responseImage.data.image
  

        } else if (data.typeMessage === VIDEO) {
          
        }
   
        dispatch(receiveMessage(data))
        break
      }
      case LEAVE: {

        break;
      }

    }
  }
  
  const onPrivateMessageReceived =  async (messageResponse: any) => {
    const data = JSON.parse(messageResponse.body)

    switch (data.status) {
      case JOIN: {
        dispatch(joinChat(data.typeChat))

        const email =  searchParam.get('email')
        const response = await getPrivateMessage(email)
        dispatch(getMessages(response.data.messages))
        break;
      }
      case MESSAGE: {
        const response = await getAvatar(data.sender.email)

        data.sender.avatar = response.image
        data.sender.oauth2 = response.oauth2
        if (data.typeMessage === IMAGE) {

          const responseImage = await getImage(data.content)
        
          data.content  = responseImage.data.image
  

        } else if (data.typeMessage === VIDEO) {
          
        }
        dispatch(receiveMessage(data))
        break;
      }
      case LEAVE: {
        break;
      }
      case ONLINE: {
        dispatch(updateStatusFriend(data.friend))
        break;
      }
      default: {

      }
    }
  }



  const handleInputChange = (e: ChangeEvent<HTMLInputElement>) => {
    SetInput({
      ...input,
      typeMessage: TEXT,
      content: e.target.value
    })
  }
  const hanldeFileChange = (e: UploadProps) => {

  
    const file  = e.fileList?.at(0)

    const typeFile  = file!.type as string
    if (typeFile.includes('image')) {
  
      SetInput({
        ...input,
        typeMessage: IMAGE,
        file: file!,
        content:''
      })
    } else if (typeFile.includes('video')) {
      SetInput({
        ...input,
        typeMessage: VIDEO,
        file: file!,
        content:''
      })
    } 
    setfileList(e.fileList!)
   
  }


  const handleSubmit = async() => {
    var { content, typeMessage, file } = input
    
    if (!content && !file) {
      return
    }
    if (typeMessage === IMAGE) {
      const response = await uploadImage(file!)
      content = response
    

    } else if (typeMessage === VIDEO) {

    }

    if (typeChat === CHAT_PUBLIC) {
      const message: MessageProps = {
        content: content,
        date: Date.now().toString(),
        typeMessage,
        status: 'MESSAGE',
        sender: {
          name: name,
          email: email,
          oauth2: isOauth2
        }
      }
      
      
      stompClient.send('/app/public-message', {}, JSON.stringify(message))
    } else if (typeChat === CHAT_PRIVATE) {

      const message: MessageProps = {
        content: content,
        date: Date.now().toString(),
        typeMessage,
        status: 'MESSAGE',
        sender: {
          name: name,
          email: email
        },
        receiver: {
          name: searchParam.get('name'),
          email: searchParam.get('email')
        }
      }
      stompClient.send('/app/private-message', {}, JSON.stringify(message))
    }


    dispatch(sendMessage())
    SetInput({
      ...input,
      typeMessage: TEXT,
      content: '',
      file: null
    })
    setfileList([])
 
  }

  return (
    <div>
      {
        typeChat &&
        <div className={cx('wrapper')}>
          <div className={cx('message-list')} ref={messagesEndRef}>
            {
              messages && messages.map((message, index) => {
      
                return (
                  <Message
                    key={index}
                    sender={message.sender?.name}
                    avatar={message.sender?.avatar}
                    content={message.content}
                    typeMessage={message.typeMessage}
                    date={message.date}
                    isSender={message.sender?.email === email ? true : false}
                    isOauth2={message.sender?.oauth2}
                  />
                )
              })
            }
          </div>

          <Form className={cx('form')} onKeyUp={(e) => {
            if (e.key === 'Enter') {
              handleSubmit()
            }
          }}>
            <Upload 
            fileList={fileList}
            maxCount={1}
            onChange={hanldeFileChange}
            multiple={false}
            beforeUpload={
              (file ) => {
                if (!file.type.includes('image/') && !file.type.includes('video/') ) {
                  swal("chỉ upload video hoặc ảnh")

                  return false
                }
                else if (file.size > 25000000 ){
                  swal("Ảnh hoặc video vượt quá 25Mb")
                  return false
                } else {
                  return true
                } 
              }
            }
            >
              <Button icon={<UploadOutlined />} type='primary' size='large' />
            </Upload>
            <Form.Item className={cx('form-input')}>
              <Input placeholder='Nhập' bordered={true} autoComplete='off' size='large' value={input.content} onChange={handleInputChange} />
            </Form.Item>
            <Button icon={<SendOutlined />} type='primary' size='large' onClick={handleSubmit} />
          </Form>
        </div>
      }
    </div>

  )
}
