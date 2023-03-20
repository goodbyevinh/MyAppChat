import React from 'react'
import {Collapse, Typography, Button} from 'antd'
import  classNames  from 'classnames/bind';
import {PlusSquareOutlined} from '@ant-design/icons'
import styles from '../../layouts/ChatLayout/components/SideBar/SideBar.module.scss';
import { Group } from './../../store/account/types';

const cx = classNames.bind(styles)

const { Panel } = Collapse;

type GroupProps = {
  groups : Array<Group>
}

const GroupList = ({groups} : GroupProps) => {
  return (
    <Collapse className={cx('custom-collapse')} ghost>
        <Panel  key={1} header="Danh sách nhóm chat">
            <div className={cx('group')}>
              {
                groups && groups.map(group => {
                  return <Typography.Link key={group.id} className={cx('collapse-link')}>{group.name}</Typography.Link>
                })
              }
              
              <Button type='text' icon={<PlusSquareOutlined/>} style={{color: 'white'}}/>
            </div>
        </Panel>
    </Collapse>
  )
}

export default GroupList