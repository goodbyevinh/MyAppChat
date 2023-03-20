import React from 'react'
import {Collapse, Typography, Button, Avatar ,Badge} from 'antd'
import  classNames  from 'classnames/bind';
import {PlusSquareOutlined, UserOutlined} from '@ant-design/icons'

import styles from '../../layouts/ChatLayout/components/SideBar/SideBar.module.scss';
import { Friend } from '../../store/account/types';


const cx = classNames.bind(styles)
const { Panel } = Collapse;

type FriendProps = {
  friends : Array<Friend>
  handleClickFriend : (email: string, name:string) => void
  handleCLickModal: () => void;
}


const FriendList = ({friends, handleClickFriend, handleCLickModal} : FriendProps) => {
  

  return (
    <Collapse className={cx('custom-collapse')} ghost >
        <Panel key={1} header="Danh sách bạn bè" className={cx('custom-panel')}>
          <div className={cx('list-friend')}>
            {
              friends && friends.map(friend => {
       
                return (
                  <div key={friend.id} className={cx('friend')} onClick={() => handleClickFriend(friend.email,friend.name) }>
                    <Badge status={friend.online ? 'success' : 'default'} dot  >
                      <Avatar icon={<UserOutlined />} src={friend.oauth2? friend.avatar : `data:image/png;base64,${friend.avatar}`}/>
                    </Badge>
                    
                    <Typography.Link className={cx('collapse-link')}>{friend.name}</Typography.Link>
                  </div>
                )
              })
            }
              <Button type='text' icon={<PlusSquareOutlined/>} style={{color: 'white'}} onClick={handleCLickModal}/>
          </div>
        </Panel>
        
    </Collapse>

  )
}

export default FriendList