import {render, screen} from '@testing-library/react';
import RatesElement from "./Rates";

test('Show Rates message', () => {
    render(<RatesElement/>);
    const ratesElement = screen.getByText(/Rates/i);
    expect(ratesElement).toBeInTheDocument();
});