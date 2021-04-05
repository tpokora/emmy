import React from 'react';
import {properties} from '../properties'
import {Box, Button, Container, Input} from "@material-ui/core";

class WeatherElement extends React.Component {

    state = {
        value: '',
        loading: true,
        forecast: null,
    };

    constructor() {
        super();
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({
            value: event.target.value
        });
    }

    async handleSubmit(event) {
        event.preventDefault();
        let url = properties.emmyEndPoint + '/api/weather/forecast?location=' + this.state.value;
        const response = await fetch(url);
        const data = await response.json();
        this.setState({forecast: data, loading: false})
        console.log(data);
    }


    render() {
        return (
            <Container maxWidth="false" disableGutters={true}>
                <Box p={8}>
                    <h1>Weather</h1>
                    <div>
                        <form onSubmit={this.handleSubmit}>
                            <Input placeholder="Location" inputProps={{ 'aria-label': 'description' }} color="primary" />
                            <Button color="primary" type="submit">Find</Button>
                        </form>
                    </div>
                    <hr/>
                    <div>
                        {this.state.loading || !this.state.forecast ? (
                            <div></div>
                        ) : (
                            <div id="forecastDetailsElement">
                                <div className="row text-success">>> Forecast: <b
                                    className="value">{this.state.forecast.name}</b>, <b
                                    className="value">{this.state.forecast.description}</b></div>
                                <div className="row text-success">>> Location: <b
                                    className="value">{this.state.forecast.location}</b></div>
                                <div className="row text-success">>> Temperature: <b
                                    className="value">{this.state.forecast.temp}</b> C, feels
                                    like: <b className="value">{this.state.forecast.feelTemp}</b> C
                                </div>
                                <div className="row text-success">>> Minimum temperature: <b
                                    className="value">{this.state.forecast.minTemp}</b> C,
                                    Maximum temperature: <b className="value">{this.state.forecast.maxTemp}</b> C
                                </div>
                                <div className="row text-success">>> Rain in 1h: <b
                                    className="value">{this.state.forecast.rain1h}</b> mm, Rain
                                    in 3h: <b className="value">{this.state.forecast.rain3h}</b> mm
                                </div>
                                <div className="row text-success">>> Pressure: <b
                                    className="value">{this.state.forecast.pressure}</b> hPa,
                                    Humidity: <b className="value">{this.state.forecast.humidity}</b>%, Wind: <b
                                        className="value">{this.state.forecast.wind}</b> km/h
                                </div>
                            </div>
                        )}
                    </div>
                </Box>
            </Container>);
    }
}

export default WeatherElement;