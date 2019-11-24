import React, { useState, useEffect } from 'react'
import Project from './Project'
import TogglableProject from './TogglableProject'
import NewProjectForm from './NewProjectForm'
import projectService from '../services/projects'
import { Table } from 'react-bootstrap'

const ProjectList = () => {
    const [projects, setProjects] = useState([])

    useEffect(() => {
        projectService.getAll().then(response => setProjects(response))
    }, [])

    return(
        <div>
             <h2>Projektit</h2>
             <Table bordered hover size="sm">
                 <tbody>
                    {projects
                    .filter(project => project.active)
                    .map(project => (
                        <tr key={project.id}>
                            <td>
                                <TogglableProject key={project.id} labelText={project.name}>
                                <Project project={project} key={project.id} />
                                </TogglableProject>
                            </td>
                        </tr>
                    ))}
                 </tbody>
             </Table>
             <NewProjectForm />
             <p>Toiminto arkistoitujen projektien näyttämiseen jonnekin tänne...</p>
        </div>
    )
}

export default ProjectList