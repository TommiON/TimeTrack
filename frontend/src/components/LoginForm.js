import React, { useState } from 'react'
import authenticationService from '../services/authentication'

const LoginForm = ({ setUser }) => {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    
    const login = async (event) => {
        event.preventDefault()
        const user = await authenticationService.login(username, password)
        window.localStorage.setItem('loggedInUser', JSON.stringify(user))
        setUser(user)
    }

    const handleUsernameChange = (event) => {
        setUsername(event.target.value)
    }

    const handlePasswordChange = (event) => {
        setPassword(event.target.value)
    }

    return(
        <div>
            <h1>Kirjaudu sisään</h1>
            <form onSubmit={login}>
                <p>Käyttäjätunnus: <input value={username} onChange={handleUsernameChange} /></p>
                <p>Salasana: <input value={password} onChange={handlePasswordChange} type="password" /></p>
                <button type="submit">Kirjaudu</button>
            </form>
        </div>
    )

}

export default LoginForm