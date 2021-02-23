import {render, screen} from '@testing-library/react';
import WeatherElement from "./Weather";

test('Show Weather message', () => {
    render(<WeatherElement/>);
    const weatherElement = screen.getByText(/Weather/i);
    expect(weatherElement).toBeInTheDocument();
});