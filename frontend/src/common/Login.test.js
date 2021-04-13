import {render, screen} from '@testing-library/react';
import Login from "./Login";

test('Login Form', () => {
    let wrapper = render(<Login />);
    const loginTitle = document.querySelector("h1")
    expect(loginTitle).toBeInTheDocument()
    expect(loginTitle).toHaveTextContent("Sign in")
    const loginBtn = document.querySelector("Button")
    expect(loginBtn).toBeInTheDocument()
    expect(loginTitle).toHaveTextContent("Sign in")
});