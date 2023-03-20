import {path} from './path'
import {publicRoutes, privateRoutes} from './routes'


type Route = {
    path: string;
    component: Function;
};



type Config = {
    path: typeof path;
    publicRoutes: Array<Route>;
    privateRoutes: Array<Route>;
}


const config : Config = {
    path,
    publicRoutes,
    privateRoutes
    
}


export default config