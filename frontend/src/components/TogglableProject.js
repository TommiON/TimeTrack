import React, { useState } from 'react'

const TogglableProject = (props) => {
    const [visible, setVisible] = useState(false)

    const hideWhenVisible = { display: visible ? 'none' : '' }
    const showWhenVisible = { display: visible ? '' : 'none' }

    const toggleVisibility = () => { setVisible(!visible) }

    return (
        <div>
          <div style={hideWhenVisible}>
            <h4 onClick={toggleVisibility}>{props.labelText}</h4>
          </div>
          <div style={showWhenVisible}>
            <h4 onClick={toggleVisibility}>{props.labelText}</h4>
            {props.children}
          </div>
        </div>
    )
}

export default TogglableProject