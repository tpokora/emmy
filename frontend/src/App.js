import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import WelcomeElement from './home/Welcome.js'
import NavBarElement from './common/NavBar.js'

class App extends Component {

    state = {};

    constructor() {
        super();
        fetch('http://localhost:8080/api/hello')
            .then(response => response.text())
            .then(message => {
                this.setState({message: message});
            });
    }


    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <NavBarElement/>
                </header>
                <main className="App-main">
                    <WelcomeElement/>
                    <h1 className="App-title">{this.state.message}</h1>
                    <a
                        className="App-link"
                        href="https://reactjs.org"
                        target="_blank"
                        rel="noopener noreferrer"
                    >
                        Learn React
                    </a>
                </main>
            </div>
        );
    }
}

export default App;
