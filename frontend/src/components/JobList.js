import React, { useState, useEffect } from 'react'
import Job from './Job'
import jobService from '../services/jobs'
import { Table } from 'react-bootstrap'

const JobList = ({ projectId }) => {
    const [jobs, setJobs] = useState([])

    useEffect(() => {
        jobService.getAllForProject(projectId).then(response => setJobs(response))
    }, [])

    if(jobs.length === 0) {
        return(<p>Ei tuntikirjauksia</p>)
    }

    return(
        <div>
            <Table bordered hover striped size="sm">
                <tbody>
                    <tr>
                        <td><b>tuntikirjaukset</b></td>
                        <td><b>aloitettu</b></td>
                        <td><b>lopetettu</b></td>
                    </tr>
                    {jobs.map(job => (
                        <Job key={job.id} name={job.name} start={job.start} end={job.end} />   
                    ))}
                </tbody>
            </Table>
        </div>
    )
}

export default JobList