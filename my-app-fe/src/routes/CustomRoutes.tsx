import React, { Fragment } from 'react'
import { Route, Routes } from 'react-router-dom'
import {RequireAuth, RequireAuthAsAdmin, RequireNotLogin} from '../auth'
import { publicRoutes, privateRoutes, authRoutes, privateAdminRoutes } from '../config/routes'

const CustomRoutes = () => {
  return (
    <Routes>
        {
          publicRoutes.map((route, index) => {
            var Layout : Function = Fragment
            if (route.layout) {
              Layout = route.layout;
            } 
          
            return  <Route key={index} path={route.path} element={<Layout> <route.component/> </Layout> }/>
          }) 

        }
        <Route element={<RequireAuth/>}>
          {
            privateRoutes.map((route, index) => {
              var Layout : Function = Fragment
              if (route.layout) {
                Layout = route.layout;
              } 
            
              return  <Route key={index} path={route.path} element={<Layout> <route.component/> </Layout> }/>
            })  
          }
        </Route>

        <Route element={<RequireAuthAsAdmin/>}>
          {
            privateAdminRoutes.map((route, index) => {
              var Layout : Function = Fragment
              if (route.layout) {
                Layout = route.layout;
              } 
            
              return  <Route key={index} path={route.path} element={<Layout> <route.component/> </Layout> }/>
            })  
          }
        </Route>

        <Route element={<RequireNotLogin/>}>
          {
            authRoutes.map((route, index) => {
              var Layout : Function = Fragment
              if (route.layout) {
                Layout = route.layout;
              } 
            
              return  <Route key={index} path={route.path} element={<Layout> <route.component/> </Layout> }/>
            })  
          }
        </Route>



    </Routes>
  )
}
export default CustomRoutes

