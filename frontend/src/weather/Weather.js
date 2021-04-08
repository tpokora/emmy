import React, { useState } from 'react';
import {properties} from '../properties'
import {Box, Button, Container, Input, makeStyles} from "@material-ui/core";


function WeatherElement() {

    const [locationName, setLocationName] = useState('')
    const [loading, setLoading] = useState(true)
    const [forecast, setForecast] = useState(null)

    const useStyles = makeStyles((theme) => ({
        weatherForm: {
            color: 'white'
        }
    }));

    const classes = useStyles();

    const submitForm = async function handleSubmit(event) {
        event.preventDefault();
        let url = properties.emmyEndPoint + '/api/weather/forecast?location=' + locationName;
        const response = await fetch(url);
        const data = await response.json();
        setForecast(data)
        setLoading(false)
        console.log(data);
    }

    return (
        <Container maxWidth="false" disableGutters={true}>
            <Box p={8}>
                <h1>Weather</h1>
                <div>
                    <form onSubmit={submitForm}>
                        <Input id="forecastFormInput" className={classes.weatherForm} placeholder="Location"
                               value={locationName} onChange={e => setLocationName(e.target.value)}
                               inputProps={{'aria-label': 'description'}} color="primary"/>
                        <Button id="forecastFormBtn" color="primary" type="submit">Find</Button>
                    </form>
                </div>
                <hr/>
                <div>
                    {loading || !forecast ? (
                        <div></div>
                    ) : (
                        <div id="forecastDetailsElement">
                            <div className="row text-success">>> Forecast: <b
                                className="value">{forecast.name}</b>, <b
                                className="value">{forecast.description}</b></div>
                            <div className="row text-success">>> Location: <b
                                className="value">{forecast.location}</b></div>
                            <div className="row text-success">>> Temperature: <b
                                className="value">{forecast.temp}</b> C, feels
                                like: <b className="value">{forecast.feelTemp}</b> C
                            </div>
                            <div className="row text-success">>> Minimum temperature: <b
                                className="value">{forecast.minTemp}</b> C,
                                Maximum temperature: <b className="value">{forecast.maxTemp}</b> C
                            </div>
                            <div className="row text-success">>> Rain in 1h: <b
                                className="value">{forecast.rain1h}</b> mm, Rain
                                in 3h: <b className="value">{forecast.rain3h}</b> mm
                            </div>
                            <div className="row text-success">>> Pressure: <b
                                className="value">{forecast.pressure}</b> hPa,
                                Humidity: <b className="value">{forecast.humidity}</b>%, Wind: <b
                                    className="value">{forecast.wind}</b> km/h
                            </div>
                        </div>
                    )}
                </div>
            </Box>
        </Container>);
}

export default WeatherElement;