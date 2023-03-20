import * as authenRequest from "./request/authenRequest";

export const getUser = async() => {
    try {
        const response = await authenRequest.get('/account/get')
        return response.data
    } catch (err) {
        Promise.reject(err)
    }
}