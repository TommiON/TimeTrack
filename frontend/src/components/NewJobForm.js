import React, { useState } from 'react'
import jobService from '../services/jobs'
import DatePicker from 'react-datepicker'
import 'react-datepicker/dist/react-datepicker.css'
import { Form, Button, Row, Col } from 'react-bootstrap'

const NewJobForm = ({ projectId }) => {
    const [name, setName] = useState('')
    const [startDate, setStartDate] = useState(new Date())
    const [endDate, setEndDate] = useState(new Date())

    const addNew = async (event) => {
        const newJob = {
            "id": Math.floor(Math.random() * Number.MAX_SAFE_INTEGER),
            // oikeasti id:t generoidaan backendissä
            "name": name,
            "start": startDate,
            "end": endDate,
            "projectId": projectId,
            "doer": 'mock-käyttäjä'
            // pitää kehittää palvelu aktiivisen käyttäjän saamiseksi
        }

        await jobService.postNew(newJob)
    }

    return(
        <div>
            <p><b>Tee uusi tuntikirjaus tähän projektiin</b></p>
            <Form onSubmit={addNew}>
                <Row>
                    <Col>
                        <Form.Label>Kuvaus:</Form.Label>
                    </Col>
                    <Col>
                        <Form.Label>Alkoi:</Form.Label>
                    </Col>
                    <Col>
                        <Form.Label>Loppui:</Form.Label>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <Form.Control type="text" name="jobName" onChange={(name) => setName(name.target.value)} />
                    </Col>
                    <Col>
                        <DatePicker selected={startDate}
                                    onChange={(date) => setStartDate(date)}
                                    showTimeSelect
                                    timeFormat="HH:mm"
                                    timeIntervals={15}
                                    timeCaption="time"
                                    dateFormat="MMMM d, yyyy h:mm aa"
                        />
                    </Col>
                    <Col>
                        <DatePicker selected={endDate}
                                    onChange={(date) => setEndDate(date)}
                                    showTimeSelect
                                    timeFormat="HH:mm"
                                    timeIntervals={15}
                                    timeCaption="time"
                                    dateFormat="MMMM d, yyyy h:mm aa"
                        />
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <Button variant="primary" type="submit">Lisää</Button>
                    </Col>
                </Row>
            </Form>
        </div>
    )
}

/*
https://reactdatepicker.com/#example-date-range-with-disabled-navigation-shown
https://www.npmjs.com/package/react-datepicker

<form onSubmit={addNew}>
                Kuvaus: <input value={name} onChange={(name) => setName(name.target.value)} />
                Alkoi: <DatePicker  selected={startDate}
                                    onChange={(date) => setStartDate(date)}
                                    showTimeSelect
                                    timeFormat="HH:mm"
                                    timeIntervals={15}
                                    timeCaption="time"
                                    dateFormat="MMMM d, yyyy h:mm aa"
                        />
                Loppui: <DatePicker selected={endDate}
                                    onChange={(date) => setEndDate(date)}
                                    showTimeSelect
                                    timeFormat="HH:mm"
                                    timeIntervals={15}
                                    timeCaption="time"
                                    dateFormat="MMMM d, yyyy h:mm aa"
                        />
                <button type="submit">Lisää</button>
            </form>

*/

export default NewJobForm