import React from 'react'
import { Typography } from 'antd'

type Props = {
  handleClick: () => void
}

const PublicGroup = ({handleClick} : Props ) => {
  return (
    <div>
        <Typography.Link style={{color:'white'}} onClick={handleClick}> Nhóm chat chung </Typography.Link>
    </div>
  )
}

export default PublicGroup