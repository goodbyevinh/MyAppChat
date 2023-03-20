import React from 'react'
import {Typography, Avatar, Button} from 'antd'
import classNames  from 'classnames/bind';
import styles from './UserInfor.module.scss'
import { useSelector } from 'react-redux';
import { selectorUser } from './../../store/account/selector';
const cx = classNames.bind(styles)

const UserInfor = () => {

  const account  = useSelector(selectorUser)
  return (
    <div className={cx('wrapper')} >
      {account?.oauth2 ? <Avatar src={account.avatar} />  : <Avatar src={`data:image/png;base64,${account?.avatar}`} />}

      <Typography.Text className={cx('username')}> {account?.name}</Typography.Text>
    </div>
  )
}
export default UserInfor
