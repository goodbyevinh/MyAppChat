import React from 'react'
import { SideBar, Header } from './components';
import {Row, Col, Spin} from 'antd'
import {Chat} from '../../pages/Private';
import { useSelector } from 'react-redux';
import { selectorLoadingAccount } from './../../store/account/selector';

type Props = {
    children: JSX.Element;
}

function ChatLayout({children} : Props) {
  const loading = useSelector(selectorLoadingAccount)

  return (
    <Spin spinning={loading}>
        {<div style={{width:'100vw', height:'100vh'}}>
          <Row>
              <Col span={4}>
                <SideBar/>
              </Col>
              <Col span={20}>
                <Header/>
                {children}
              </Col>
          </Row>
      </div>}
    </Spin>

  )
}

export {ChatLayout}