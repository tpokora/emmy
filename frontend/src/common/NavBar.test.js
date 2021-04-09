import {render, screen} from '@testing-library/react';
import App from "../App";

test('Navigation bar', () => {
    render(<App/>);
    const emmyAppElement = screen.getByText(/EmmyApp/i);
    const homeElement = screen.getByText(/Home/i);
    const usersElement = screen.getByText(/Users/i);
    const weatherElement = screen.getByText(/Weather/i);
    const ratesElement = screen.getByText(/Rates/i);
    expect(emmyAppElement).toBeInTheDocument();
    expect(homeElement).toBeInTheDocument();
    expect(usersElement).toBeInTheDocument();
    expect(weatherElement).toBeInTheDocument();
    expect(ratesElement).toBeInTheDocument();
});