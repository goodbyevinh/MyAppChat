import React from 'react'
import { Navigate, Outlet } from 'react-router'
import config from '../config'
import { useSelector } from 'react-redux';
import { selectorToken } from './../store/account/selector';


export const RequireAuthAsAdmin = () => {

  const token = useSelector(selectorToken)
  const isLogin = token ? true : false

  const isAdmin = false
  if (!isLogin) {
    return <Navigate to={config.path.login}/>
  } else if (isLogin && isAdmin) {
    return <Outlet />
  } else {
    return <Navigate to={config.path.page404}/>
  }

}

