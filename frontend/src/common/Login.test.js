import {render, screen} from '@testing-library/react';
import Login from "./Login";

test('Login Form', () => {
    let wrapper = render(<Login />);
    const loginTitle = screen.getByText(/Sign in/i);
    expect(loginTitle).toBeInTheDocument()
});