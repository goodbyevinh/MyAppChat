
import * as publicRequest from "./request/publicRequest";
import { UploadFile } from 'antd';

export const uploadImage = async (image : UploadFile<any> ) => {
    try {    
        const formData = new FormData();

        formData.append('title', image.name)
        formData.append('image', image.originFileObj as Blob, image.name)
        const response = await publicRequest.postFile('image/add', formData);
    
        return response.data.data
    
    } catch (error) {
        console.log(error)
    }
}

export const getAvatar = async(email: string) => {
    try {
        const response = await publicRequest.get(`image/email/${email}`);
    
        return response.data
    
    } catch (error) {
        console.log(error)
    }
}
export const getImage = async(id: string) => {
    try {
        const response = await publicRequest.get(`image/get/${id}`);
    
        return response.data
    
    } catch (error) {
        console.log(error)
    }
}
