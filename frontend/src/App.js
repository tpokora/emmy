import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import {
    HashRouter as Router,
    Switch,
    Route
} from "react-router-dom";
import WelcomeElement from './home/Welcome.js'
import NavBarElement from './common/NavBar.js'
import RatesElement from "./rates/Rates";
import UsersElement from "./users/Users";
import WeatherElement from "./weather/Weather";
import {Container, makeStyles} from "@material-ui/core";


function App() {

    const useStyles = makeStyles((theme) => ({

    }));

    const classes = useStyles();

    return (
        <Router>
            <div className="App">
                <header className="App-header">
                    <NavBarElement/>
                </header>
                <Container className="App-main">
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
                </Container>
            </div>
        </Router>
    );
}

export default App;
