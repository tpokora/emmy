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

    constructor() {
        super();
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
                    </main>
                </div>
            </Router>
        );
    }
}

export default App;
