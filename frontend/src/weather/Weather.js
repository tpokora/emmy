import React from 'react';
import {Jumbotron} from "react-bootstrap";

class WeatherElement extends React.Component {

    constructor() {
        super();
        this.state = {value: '', forecast: ''};

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    handleSubmit(event) {
        alert('Find in location: ' + this.state.value)
        fetch('http://localhost:8080/api/weather/forecast?location='+this.state.value)
            .then(response => response.text())
            .then(forecast => {
                this.setState({forecast: forecast});
            });
        alert('Forecast: ' + this.state.forecast)
        event.preventDefault();
    }


    render() {
        return (<Jumbotron>
            <h1>Weather</h1>
            <div>
                <form onSubmit={this.handleSubmit} >
                    <label>
                        Location: <input type="text" value={this.state.value} onChange={this.handleChange}/>
                    </label>
                    <input type="submit" value="Find"/>
                </form>
            </div>
            <div>
                <h3>Forecast:</h3>
                <p>{this.state.forecast}</p>
            </div>
        </Jumbotron>)
    }
}

export default WeatherElement;