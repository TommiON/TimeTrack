import React from 'react'
import authenticationService from '../services/authentication'

const LogoutForm = ({ user, setUser }) => {

    const logout = (event) => {
        event.preventDefault()
        authenticationService.logout()
        window.localStorage.removeItem('loggedInUser')
        setUser(null)
    }

    return(
        <div>
            <p><b>{user.username}</b> on kirjautunut <button onClick={logout}>Kirjaudu ulos</button></p>
        </div>
    )

}

export default LogoutForm