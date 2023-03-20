import {path} from './path'
import { Chat } from '../pages/Private';
import { Login, Page404, Signup } from '../pages/Public';
import { Admin } from './../pages/Admin';
import { ChatLayout } from '../layouts';
import { Fragment } from 'react';


type Route = {
    path: string;
    component: Function;
    layout?: Function;
};


const publicRoutes : Array<Route> = [
    {path: path.page404, component: Page404 , layout: ChatLayout}
]

const privateRoutes : Array<Route> = [
    {path: path.home, component: Chat , layout: ChatLayout}
]

const privateAdminRoutes : Array<Route> = [
    {path: path.admin, component: Admin}
]
const authRoutes : Array<Route> = [
    {path: path.login, component: Login},
    {path: path.signup, component: Signup},
    {path: path.redirect, component: Fragment}
]






export {publicRoutes, privateRoutes, authRoutes, privateAdminRoutes}
