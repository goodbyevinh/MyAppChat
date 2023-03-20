import React from 'react'
import { useSelector } from 'react-redux'
import { Navigate, Outlet } from 'react-router-dom'
import config from '../config'
import { AppState } from '../store'
import { AccountState } from '../store/account/types'
import { selectorToken, selectorUser } from './../store/account/selector';
import { AuthenticateUser } from '../store/account/types'


export const RequireAuth = () => {
  
  const token = useSelector(selectorToken)
  const isLogin = token ? true : false

  return (
    isLogin ? 
        <Outlet/> 
        : <Navigate to={config.path.login} replace />
        
  )
}

