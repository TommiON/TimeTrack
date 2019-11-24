import axios from 'axios'
const baseURL = 'http://localhost:3001'

const getAll = async () => {
    const response = await axios.get(`${baseURL}/jobs`)
    return response.data
}

const getAllForProject = async (projectId) => {
    const response = await axios.get(`${baseURL}/projects/${projectId}/jobs`)
    return response.data
}

const postNew = async (newJob) => {
    const response = await axios.post(`${baseURL}/jobs`, newJob)
    return response.data
}

export default { getAll, getAllForProject, postNew }