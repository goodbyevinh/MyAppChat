import axios from "axios";
import { publicRequest } from "../../helpers";



const customConfig = {
    headers: {
        'Content-Type': 'application/json'
    }
};

const customConfigFile = {
    headers: {
        'Content-Type': 'multipart/form-data'
    }
};

export const get = async (path: string) => {
    const response = await publicRequest.get(path)
    return response;
}

export const postJson = async(path : string, json: string) => {
    const response = await publicRequest.post(path, json, customConfig);
    return response
}
export const postParams = async (path: string, params : FormData) => {
    const response = await publicRequest.post(path, params, customConfig)
    return response;
}
export const postFile = async (path: string, params : FormData) => {
    const response = await publicRequest.post(path, params, customConfigFile)
    return response;
}

