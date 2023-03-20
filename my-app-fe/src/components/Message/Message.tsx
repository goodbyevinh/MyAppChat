import React from 'react'
import styles from './Message.module.scss';
import  classNames  from 'classnames/bind';
import { Avatar, Typography, Tooltip, Image } from 'antd';
import {Player} from 'video-react'
import "../../../node_modules/video-react/dist/video-react.css";
import { TEXT, VIDEO  , IMAGE} from './../../store/message/types';
const cx = classNames.bind(styles)

type MessageProps = {
    sender: string | undefined | null;
    avatar: string | undefined;
    content: string | undefined;
    typeMessage: typeof TEXT |typeof VIDEO |typeof IMAGE;
    date: string | undefined;
    isSender: boolean;
    isOauth2: boolean | undefined ;
}

const Message = ({sender, avatar, content, typeMessage, date, isSender, isOauth2} : MessageProps) => {
 
  return (
    <div className={cx('wrapper')}>
        <div >
            {
                isSender ? 
                <div className={cx('message-send')} >
                    <Tooltip title={date}>
                        {
                            typeMessage == TEXT ? <Typography.Text className={cx('message')}>{content}</Typography.Text> 
                            : typeMessage == IMAGE ? <Image className={cx('image')} src={`data:image/png;base64,${content}`} style={{height: '200px', width: '200px'}} />
                            : <Player
                            playsInline
                            fluid={false} width={400} height={250}
                            poster="/assets/poster.png"
                            src={content}
                          />
                        }
                        
                    </Tooltip>
                    <Tooltip title={sender}>
                        {isOauth2 ? <Avatar size={'small'} src={avatar}/> : <Avatar size={'small'} src={`data:image/png;base64,${avatar}`}/>}
                    </Tooltip>
                </div>
                : 
                <div className={cx('message-receive')} >
                    <Tooltip title={sender}>
                        {isOauth2 ? <Avatar size={'small'} src={avatar}/> : <Avatar size={'small'} src={`data:image/png;base64,${avatar}`}/>}
                    </Tooltip>
                    <Tooltip title={date}>
                        {
                            typeMessage == TEXT ? <Typography.Text className={cx('message')}>{content}</Typography.Text> 
                            : typeMessage == IMAGE ? <Image className={cx('image')} src={`data:image/png;base64,${content}`} style={{height: '200px', width: '200px'}} />
                            : <Player
                            playsInline
                            fluid={false} width={400} height={250}
                            poster="/assets/poster.png"
                            src={content}
                          />
                        }
                    </Tooltip>
                </div>
            }
        </div>

    </div>
  )
}

export default Message