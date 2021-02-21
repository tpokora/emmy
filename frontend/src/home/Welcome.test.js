import {render, screen} from '@testing-library/react';
import WelcomeElement from './Welcome';

test('Show welcome message', () => {
    render(<WelcomeElement/>);
    const linkElement = screen.getByText(/Welcome to Emmy Project ReactApp!/i);
    expect(linkElement).toBeInTheDocument();
});