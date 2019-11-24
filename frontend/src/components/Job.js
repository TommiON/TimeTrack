import React from 'react'

const Job = ({ name, start, end }) => {

    return(
        <tr key={name}>
            <td>{name}</td>
            <td>{start}</td>
            <td>{end}</td>
        </tr>
    )
}

export default Job