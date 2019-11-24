// import axios from 'axios'
// const baseURL = ''

const login = async (username, password) => {
    const mockUser = {
        'username': username,
        'token': 'dummy'
    }
    return mockUser
}

const logout = async () => {

}

export default { login, logout }