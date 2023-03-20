import React from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { Navigate, Outlet, useParams } from 'react-router'
import config from '../config'
import { selectorToken } from './../store/account/selector';
import { useSearchParams } from 'react-router-dom';
import { LOGIN_SUCCESS } from '../store/account/types';

export const RequireNotLogin = () => {
  
  const token = useSelector(selectorToken)
  const [searchParams, setSearchParams] = useSearchParams();
  const dispatch = useDispatch();

  const isLogin = token ? true : false

  if (searchParams.get('token') && searchParams.get('refresh')) {
    const token =  searchParams.get('token')
    const refreshToken = searchParams.get('refresh')
    dispatch({
      type:  LOGIN_SUCCESS,
      payload: {token, refreshToken }
    })

    return <Navigate to={config.path.home}/>
  } else if (isLogin) {
    return <Navigate to={config.path.home}/>
  } else {
    return <Outlet/>
  }
}
