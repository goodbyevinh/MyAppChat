import * as publicRequest from "./request/publicRequest";

type loginBody = {
    email: string;
    password: string;
}
type signupBody = {
    email: string;
    password: string;
    fullname: string;
    avatar: File;
}
const login = async (body : loginBody) => {
    try {
        const json = JSON.stringify(body)
        const response = await publicRequest.postJson('auth/login', json);
        const {token, refreshToken, account} = response.data.data
       
        return {token, refreshToken, account}
    } catch (error) {
        return Promise.reject(error)
    }

}
const signnup = async (body : signupBody) => {
    try {
        const {email, password, fullname, avatar} = body;
        const json = JSON.stringify({email, password, fullname})

        const formData = new FormData();
        formData.append('signup', json)
        formData.append('title', avatar.name)
        formData.append('image', avatar, avatar.name)

        const response = await publicRequest.postFile('auth/signup', formData);
        const {token, refreshToken, account} = response.data.data
        sessionStorage.setItem('user', account)
        return {token, refreshToken, account}
    } catch (error) {
        return Promise.reject(error)
    }

}



const logout = () => {
    
}

export const userService = {
    login,
    logout,
    signnup
}