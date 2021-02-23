import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import {
    BrowserRouter as Router,
    Switch,
    Route
} from "react-router-dom";
import WelcomeElement from './home/Welcome.js'
import NavBarElement from './common/NavBar.js'
import RatesElement from "./rates/Rates";
import UsersElement from "./users/Users";
import WeatherElement from "./weather/Weather";

class App extends React.Component {

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
            <Router>
                <div className="App">
                    <header className="App-header">
                        <NavBarElement/>
                    </header>
                    <main className="App-main">
                        <Switch>
                            <Route path="/users">
                                <UsersElement/>
                            </Route>
                            <Route path="/weather">
                                <WeatherElement/>
                            </Route>
                            <Route path="/rates">
                                <RatesElement/>
                            </Route>
                            <Route path="/">
                                <WelcomeElement/>
                            </Route>
                        </Switch>
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
            </Router>
        );
    }
}

export default App;
