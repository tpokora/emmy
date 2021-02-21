import React, {Component} from 'react';
import './App.css';
import HomeElement from './home/Welcome.js'
import WelcomeElement from "./home/Welcome.js";

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



render () {
  return (
    <div className="App">
      <header className="App-header">
        <h1 className="App-title">{this.state.message}</h1>
        <p>
          <WelcomeElement/>
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}
}

export default App;
