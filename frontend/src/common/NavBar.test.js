import {render, screen} from '@testing-library/react';
import App from "../App";

test('Navigation bar', () => {
    render(<App/>);
    const homeElement = screen.getByText(/Home/i);
    const weatherElement = screen.getByText(/Weather/i);
    const ratesElement = screen.getByText(/Rates/i);
    const loginBtn = screen.getByText(/Login/i);
    expect(homeElement).toBeInTheDocument();
    expect(weatherElement).toBeInTheDocument();
    expect(ratesElement).toBeInTheDocument();
    expect(loginBtn).toBeInTheDocument();
});