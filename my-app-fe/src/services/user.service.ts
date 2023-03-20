

export const login = (email: string, password: string) => {
    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
      
        body: JSON.stringify({
            email,
            password
        })
    }
    return fetch(`${process.env.REACT_APP_API_URL}/auth/signin`)
        .then(handleResponse)
        .then(response => {
            sessionStorage.setItem('user', JSON.stringify(response))
            return response
        })
}
const logout = () => {
    sessionStorage.removeItem('user')
}
const handleResponse = (response : any) => {
    return response.text().then((text: string )=> {
        const data = text && JSON.parse(text)
        if (!response.ok) {
            const error = ( data && data.description ) || response.statusText
            return Promise.reject(error)
        }

        return data;
    })
}
export const userService = {
    login,
    logout
}