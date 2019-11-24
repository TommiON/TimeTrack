import React, { useState } from 'react'
import projectService from '../services/projects'
import { Form, Button } from 'react-bootstrap'

const NewProjectForm = () => {
    const [name, setName] = useState('')

    const addNew = async (event) => {
        const newProject = {
            "id": Math.floor(Math.random() * Number.MAX_SAFE_INTEGER),
            // oikeasti id:t generoidaan backendissä
            "name": name,
            "started": Date(),
            "active": true,
            "owner": 'mock-käyttäjä'
            // pitää kehittää palvelu aktiivisen käyttäjän saamiseksi
        }
        
        await projectService.postNew(newProject)
    }

    const handleNameChange = (event) => {
        setName(event.target.value)
    }

    return(
        <div>
            <hr></hr>
            <h4>Lisää uusi projekti</h4>
            <Form onSubmit={addNew}>
                <Form.Group>
                    <Form.Label>Nimi:</Form.Label>
                    <Form.Control type="text" name="projectName" onChange={handleNameChange} />
                    <Button variant="primary" type="submit">Lisää</Button>
                </Form.Group>
            </Form>
        </div>
    )

}

export default NewProjectForm