import { Modal, Button, Tabs, Avatar, Typography } from 'antd'
import React, { ChangeEvent, MouseEventHandler, useRef, useState } from 'react'
import classNames from 'classnames/bind';
import styles from '../../../layouts/ChatLayout/components/SideBar/SideBar.module.scss'
import swal from 'sweetalert';
import { inviteFriend } from '../../../services';
import { useDispatch } from 'react-redux';
import { loading, notLoading } from './../../../store/account/action';
import { acceptFriend, getInvitedFriens } from './../../../services/friendService';


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


type Props = {
    isOpen: boolean;
    handleOk: () => void;
    notFriends: Array<NotFriend>
    invitedFriends: Array<InvitedFriend>
    handleButtonInvite: (id: number) => void;
    handleButtonAccept: (id: number) => void;
}

export const ModalForm = ({ isOpen, handleOk, notFriends, invitedFriends, handleButtonInvite, handleButtonAccept }: Props) => {
    const dispatch = useDispatch()



    return (
        <div>
            <Modal
                title={'Thêm bạn bè'}
                open={isOpen}
                onOk={handleOk}
                okText='Ok'
                onCancel={handleOk}
                footer={[

                    <Button key="submit" type="primary" onClick={handleOk}>
                        Thoát
                    </Button>,
                ]}
                width='20%'
            >
                <Tabs
                    defaultActiveKey="1"
                    
                    items={[
                        {
                            key: '1',
                            label: `Mời kết bạn`,
                            children: (
                                <div className={cx('content-modal')}>
                                    <div className={cx('list-friend-invite')}>
                                        {
                                            notFriends && notFriends.map(friend => {
                                                return (
                                                    <div className={cx('friend')} key={friend.id}>
                                                        <div  className={cx('avatar-name')} >
                                                            <Avatar src={friend.oauth2 ? friend.avatar : `data:image/png;base64,${friend.avatar}`} />
                                                            <Typography.Link className={cx('collapse-link')}>{friend.name}</Typography.Link>
                                                            
                                                        </div>
                                                        <Button name="email"  danger={friend.invite} disabled={friend.invite} type='primary' onClick={() => handleButtonInvite(friend.id)}> Mời </Button>
                                                    </div> 
                                                )
                                            })
                                        }
                                    </div>
                                   
                                </div>

                            ),
                        },
                        {
                            key: '2',
                            label: `Lời mời`,
                            children:
                                <div className={cx('content-modal')}>
                                    <div className={cx('list-friend-accept')}>
                                        {
                                            invitedFriends && invitedFriends.map(friend => {
                                                return (
                                                    <div className={cx('friend')} key={friend.id}>
                                                        <div  className={cx('avatar-name')} >
                                                            <Avatar src={friend.oauth2 ? friend.avatar : `data:image/png;base64,${friend.avatar}`} />
                                                            <Typography.Link className={cx('collapse-link')}>{friend.name}</Typography.Link>
                                                            
                                                        </div>
                                                        <Button type='primary' onClick={() => handleButtonAccept(friend.id)}> Chấp nhận </Button>
                                                    </div> 
                                                )
                                            })
                                        }
                                    </div>
                                </div>
                        }
                    ]}
                />

            </Modal>
        </div>
    )
}
