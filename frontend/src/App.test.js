import {render, screen} from '@testing-library/react';
import App from './App';

test('Render home page', () => {
    render(<App/>);
    const linkElement = screen.getByText(/Welcome to Emmy Project ReactApp!/i);
    expect(linkElement).toBeInTheDocument();
});
