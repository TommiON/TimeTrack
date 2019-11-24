import React from 'react'

const Notification = ({ message, error }) => {
    const greenStyle = {
        border: 'solid',
        padding: 10,
        borderWidth: 0,
        color: 'green',
        background: 'lightgrey'
    }

    const redStyle = {
        border: 'solid',
        padding: 10,
        borderWidth: 0,
        color: 'red',
        background: 'lightgrey'
    }

    if(message === '') {
        return null
    }

    return(
        <div style={error ? redStyle : greenStyle}>
            {message}
        </div>
    )
}

export default Notification