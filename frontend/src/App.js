import React, { useState, useEffect } from 'react'
import './App.css'
import LoginForm from './components/LoginForm'
import LogoutForm from './components/LogoutForm'
import ProjectList from './components/ProjectList'

function App() {
  const [user, setUser] = useState(null)

  useEffect(() => {
    const loggedUserJSON = window.localStorage.getItem('loggedInUser')
    if(loggedUserJSON) {
      const loggedUser = JSON.parse(loggedUserJSON)
      setUser(loggedUser)
    }
  }, [])

  if(!user) {
    return(
      <div className="container">
        <LoginForm setUser={u => setUser(u)} />
      </div>
    )
  }

  return (
    <div className="container">
      <LogoutForm user={user} setUser={setUser} />
      <ProjectList />
    </div>
  )
}

export default App
