import axios from 'axios'
const baseURL = 'http://localhost:3001'

const getAll = async () => {
    const response = await axios.get(`${baseURL}/projects`)
    return response.data
}

const getOne = async (projectId) => {
    const response = await axios.get(`${baseURL}/projects/${projectId}`)
    return response.data
}

const postNew = async (newProject) => {
    const response = await axios.post(`${baseURL}/projects`, newProject)
    return response.data
}

const archive = async (project) => {
    const updatedProject = {
        id: project.id,
        name: project.name,
        started: project.started,
        owner: project.owner,
        active: false
    }
    const response = await axios.put(`${baseURL}/projects/${project.id}`, updatedProject)
    console.log('updatedProject: ', updatedProject)
    return response.data
}

export default { getAll, getOne, postNew, archive }