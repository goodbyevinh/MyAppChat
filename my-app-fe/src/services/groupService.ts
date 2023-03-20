import * as authenRequest from './request/authenRequest'

export const getGroups = async() => {
    try {
        const response = await authenRequest.get('/group/get-groups')
    
        return response.data
    } catch(err) {
        console.log(err)  
    }
}

export const insertGroup = async(name: string) => {
    try {
        const response = await authenRequest.get(`/group/insert/${name}`)
    
        return response.data
    } catch(err) {
        console.log(err)  
    }
}

