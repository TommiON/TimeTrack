import React from 'react'
import JobList from './JobList'
import ArchiveProjectForm from './ArchiveProjectForm'
import NewJobForm from './NewJobForm'

const Project = (props) => {

    return(
        <div>
            <NewJobForm projectId={props.project.id} />
            <JobList projectId={props.project.id} />
            <ArchiveProjectForm project={props.project} />
        </div>        
    )
}

export default Project