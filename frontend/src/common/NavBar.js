import React from 'react';
import { Navbar, Nav }from 'react-bootstrap';
import {
    Link
} from "react-router-dom";

class NavBarElement extends React.Component {

    render() {
        return (<div>
            <Navbar bg="dark" variant="dark">
                <Navbar.Brand href="#home">EmmyApp</Navbar.Brand>
                <Nav className="mr-auto">
                    <Link to="/" className="nav-link">Home</Link>
                    <Link to="/users" className="nav-link">Users</Link>
                    <Link to="/weather" className="nav-link">Weather</Link>
                    <Link to="/rates" className="nav-link">Rates</Link>
                </Nav>
            </Navbar>
        </div>)
    }
}

export default NavBarElement;