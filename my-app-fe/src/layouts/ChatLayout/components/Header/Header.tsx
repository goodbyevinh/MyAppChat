import React, { useEffect } from 'react'
import {Avatar, Button, Tooltip} from 'antd'
import {UserAddOutlined} from '@ant-design/icons'
import styles from './Header.module.scss';
import  classNames  from 'classnames/bind';
import { LogoutOutlined } from '@ant-design/icons';
import { useDispatch, useSelector } from 'react-redux';
import { logout } from './../../../../store/account/action';
import { history } from '../../../../helpers';
import { selectorTypeChat } from '../../../../store/message/selector';
import { CHAT_GROUP, CHAT_PUBLIC } from '../../../../store/message/types';
import { CHAT_PRIVATE } from './../../../../store/message/types';
import { useParams } from 'react-router';
import { useSearchParams } from 'react-router-dom';
import { changeTypeChat } from './../../../../store/message/action';
import { selectorFriendInGroup } from './../../../../store/message/selector';


const cx = classNames.bind(styles)

function Header() {
  const dispatch = useDispatch();
  const friendsGroup = useSelector(selectorFriendInGroup)

  const handleLogout = () => {
    dispatch(logout())
    history.push('/login')
  }
  const typeChat = useSelector(selectorTypeChat)

  const searchParams = useSearchParams()
  return (
    <div className={cx('wrapper')}>
      <Button type='text' icon={<LogoutOutlined />} onClick={handleLogout}></Button>
      <div className={cx('header-info')}>
        {
          typeChat == null ? ( <><p className={cx('header-title')}>Chào mừng bạn</p><span className={cx('header-description')}>chọn phòng chat bên phải</span></> )
          : typeChat == CHAT_PUBLIC ? <><p className={cx('header-title')}>Phòng chat public</p><span className={cx('header-description')}></span></>
          : typeChat == CHAT_PRIVATE ? <><p className={cx('header-title')}>Phòng chat bạn bè</p><span className={cx('header-description')}> Bạn bè </span></>
          : <><p className={cx('header-title')}>Phòng Chat Nhóm</p><span className={cx('header-description')}></span></>
        }
        
        
      </div>
      <div className={cx('button-group')}>
        { typeChat === CHAT_PUBLIC ?  
                                  <>
                      
                                    <Avatar.Group size={'small'} maxCount={2} style={{marginRight:'20px'}}>
                                        {
                                          friendsGroup.map(friend => {
          
                                            return <Tooltip key={friend.id} title={friend.email}> <Avatar src={friend.oauth2? friend.avatar : `data:image/png;base64,${friend.avatar}`}/> </Tooltip>
                                          })
                                        }
                                    </Avatar.Group>
                                  </>
        : typeChat === CHAT_GROUP ?
                                  <>
                                    <Button icon={<UserAddOutlined/>} type={'text'} style={{marginRight:'20px'}}>Mời</Button> 
                                    <Avatar.Group size={'small'} maxCount={2} style={{marginRight:'20px'}}>
                                    <Tooltip> <Avatar src={'https://www.w3schools.com/howto/img_avatar.png'}/> </Tooltip>
                                    <Tooltip> <Avatar src={'https://www.w3schools.com/howto/img_avatar.png'}/> </Tooltip>
                                    <Tooltip> <Avatar src={'https://www.w3schools.com/howto/img_avatar.png'}/> </Tooltip>
                                    <Tooltip> <Avatar src={'https://www.w3schools.com/howto/img_avatar.png'}/> </Tooltip>
                                    <Tooltip> <Avatar src={'https://www.w3schools.com/howto/img_avatar.png'}/> </Tooltip>
                                    <Tooltip> <Avatar src={'https://www.w3schools.com/howto/img_avatar.png'}/> </Tooltip>
                                  </Avatar.Group>
                                  </>
                                  :<></>
        }

      </div>
    </div>
  )
}

export  {Header}