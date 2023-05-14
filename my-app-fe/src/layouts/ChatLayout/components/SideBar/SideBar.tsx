import React, { useEffect, useState } from 'react'
import {Row, Col} from 'antd'
import UserInfor from '../../../../components/UserInfor/UserInfor';
import FriendList from '../../../../components/Friend/FriendList';
import GroupList from '../../../../components/Group/GroupList';
import styles from './SideBar.module.scss';
import  classNames  from 'classnames/bind';
import PublicGroup from './../../../../components/PublicGroup/PublicGroup';
import { useDispatch, useSelector } from 'react-redux';
import { stompClient } from '../../../../services/messageService';
import { changeTypeChat, getMessages, joinChat } from '../../../../store/message/action';
import { CHAT_PUBLIC } from '../../../../store/message/types';
import { CHAT_PRIVATE, CHAT_GROUP } from './../../../../store/message/types';
import { getPublicMessage } from './../../../../services/messageService';
import { selectorTypeChat } from './../../../../store/message/selector';
import { selectorFriends, selectorGroups } from '../../../../store/account/selector';
import { Group, Friend } from './../../../../store/account/types';
import { useSearchParams } from 'react-router-dom';
import { selectorLoadingAccount } from './../../../../store/account/selector';
import { ModalForm } from '../../../../components/Modal';
import { getNotFriends, insertGroup } from '../../../../services';
import { acceptFriend, getInvitedFriens, inviteFriend } from './../../../../services/friendService';
import { loading, notLoading } from '../../../../store/account/action';
import swal from 'sweetalert';


const cx = classNames.bind(styles)


type NotFriend = {
  id: number;
  name: string;
  avatar: string;
  oauth2: boolean;
  email: string;
  invite: boolean;
}
type InvitedFriend = {
  id: number;
  name: string;
  avatar: string;
  oauth2: boolean;
  email: string;
}
function SideBar() {
  const dispatch = useDispatch();
  const typeChat = useSelector(selectorTypeChat)
  const friends : Array<Friend> = useSelector(selectorFriends)
  const groups : Array<Group> = useSelector(selectorGroups)
  const [isOpen, SetOpen] = useState<boolean>(false)
  const [notFriends, SetNotFriends] = useState<Array<NotFriend>>([]);
  const [invitedFriends, SetInvitedFriends] = useState<Array<InvitedFriend>>([]);


  const handleCLickModal = async() => {
    const responseNot = await getNotFriends();
    const responseInvite = await getInvitedFriens();
    
    SetInvitedFriends(responseInvite.data)
    SetNotFriends(responseNot.data)


    SetOpen(true)
  }

  const handleOk = () => {
    SetOpen(false)
  }
 
  const [searchParams, setSearchParams] = useSearchParams();
 


  const handleClickFriend = (email: string, name: string) => {
    
    setSearchParams({typechat: 'private', email:email, name:name})
    window.location.reload();
  }

  const handleClickPublic = () => {
    setSearchParams({typechat: 'public'})
    window.location.reload();
  }

  const handleClickGroup = (id: number) => {
    setSearchParams({typechat: 'group', groupid: `${id}`} )
    window.location.reload();
  }
  
  const handleButtonInvite = async (id: number) => {
    dispatch(loading())
    const response = await inviteFriend(id);

    if (response.success) {
      SetNotFriends((prev) => {
        for (let i = 0; i < prev.length ; i++) {
          if (prev[i].id === id){
            prev[i].invite = true
            break;
          }
        }
        return prev
      })
        
    }else {
        swal("Thất bại")
    }
    dispatch(notLoading())
  }
  const handleButtonAccept = async(id: number) => {
      dispatch(loading())
      const response = await acceptFriend(id);
      if (response.success) {
        const newResponse = await getInvitedFriens();
        SetInvitedFriends(newResponse.data);

      }else {
          swal("Thất bại")
      }
      dispatch(notLoading())
  }
  
  const handleAddGroup = async() => {


    //const response = await insertGroup(name);
    

  }
  return (
 
    <div className={cx('wrapper')}>
      <ModalForm isOpen={isOpen} handleOk={handleOk} 
      notFriends={notFriends} invitedFriends={invitedFriends} handleButtonInvite={handleButtonInvite} handleButtonAccept={handleButtonAccept}/>
      <Row >
        <Col span={24}> <UserInfor/> </Col>
        <Col span={24} style={{margin:'20px 0px 10px 40px'}}> <PublicGroup handleClick={handleClickPublic}/> </Col>
        <Col span={24}> <FriendList friends={friends}  handleClickFriend={handleClickFriend} handleCLickModal= {handleCLickModal}/> </Col>
        <Col span={24}> <GroupList groups={groups}/> </Col>
      </Row>


    </div>

  )
}

export {SideBar}