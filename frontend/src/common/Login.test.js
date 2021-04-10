import {render, screen} from '@testing-library/react';
import Login from "./Login";

test('Login Form', () => {
    render(<Login />)
    const loginTitle = screen.getByText(/Login/i);
    expect(loginTitle).toBeInTheDocument()
})