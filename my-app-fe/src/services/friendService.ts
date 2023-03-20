import * as authenRequest from './request/authenRequest'

export const getFriends = async() => {
    try {
        const response = await authenRequest.get('/friend/get-friends')
        return response.data
    } catch(err) {
        console.log(err)  
    }
}


export const getNotFriends = async() => {
    try {
        const response = await authenRequest.get('/friend/get-not-friends')
        return response.data
    } catch(err) {
        console.log(err)  
    }
}

export const getInvitedFriens = async() => {
    try {
        const response = await authenRequest.get('/friend/get-invited-friends')
        return response.data
    } catch(err) {
        console.log(err)  
    }
}

export const inviteFriend = async(id: number) => {
    try {
        const response = await authenRequest.get(`/friend/invite/${id}`)
        return response.data
    } catch(err) {
        console.log(err)  
    }
}
export const acceptFriend = async(id: number) => {
    try {
        const response = await authenRequest.get(`/friend/accept/${id}`)
        return response.data
    } catch(err) {
        console.log(err)  
    }
}