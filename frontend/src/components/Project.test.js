import React from 'react'
import '@testing-library/jest-dom/extend-expect'
import { render, cleanup } from '@testing-library/react'
import Project from './Project'

afterEach(cleanup)

test('renders content', () => {
    const projectName = 'testetaan'
    const component = render(<Project name={projectName} />)
    expect(component.container).toHaveTextContent(projectName)
})