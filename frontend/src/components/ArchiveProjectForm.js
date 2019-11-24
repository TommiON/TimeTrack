import React from 'react'
import projectService from '../services/projects'
import { Form, Button } from 'react-bootstrap'

const ArchiveProjectForm = ({ project }) => {
    
    const archive = async () => {
        await projectService.archive(project)
    }

    return(
        <div>
            <hr></hr>
            <Form onSubmit={archive}>
                <Form.Group>
                    <Button variant="primary" type="submit">Arkistoi tämä projekti</Button>
                </Form.Group>
            </Form>
        </div>
    )
}

export default ArchiveProjectForm