import {render, screen} from '@testing-library/react';
import UsersElement from "./Users";

test('Show User message', () => {
    render(<UsersElement/>);
    const usersElement = screen.getByText(/Users/i);
    expect(usersElement).toBeInTheDocument();
});