import {render, screen} from '@testing-library/react';
import NavBarElement from './NavBar';

test('Show welcome message', () => {
    render(<NavBarElement/>);
    const emmyElement = screen.getByText(/Emmy/i);
    const homeElement = screen.getByText(/Home/i);
    const usersElement = screen.getByText(/Users/i);
    const weatherElement = screen.getByText(/Weather/i);
    const ratesElement = screen.getByText(/Rates/i);
    expect(emmyElement).toBeInTheDocument();
    expect(homeElement).toBeInTheDocument();
    expect(usersElement).toBeInTheDocument();
    expect(weatherElement).toBeInTheDocument();
    expect(ratesElement).toBeInTheDocument();
});