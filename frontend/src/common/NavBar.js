import React from 'react';
import { Navbar, Nav }from 'react-bootstrap';

class NavBarElement extends React.Component {

    render() {
        return (<div>
            <Navbar bg="dark" variant="dark">
                <Navbar.Brand href="#home">Emmy</Navbar.Brand>
                <Nav className="mr-auto">
                    <Nav.Link href="#home">Home</Nav.Link>
                    <Nav.Link href="#users">Users</Nav.Link>
                    <Nav.Link href="#weather">Weather</Nav.Link>
                </Nav>
            </Navbar>
        </div>)
    }
}

export default NavBarElement;