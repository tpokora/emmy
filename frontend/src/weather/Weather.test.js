import {render, screen, fireEvent, waitFor} from '@testing-library/react';
import WeatherElement from "./Weather";

test('Show Weather message', () => {
    render(<WeatherElement/>);
    const weatherElement = screen.getByText(/Weather/i);
    expect(weatherElement).toBeInTheDocument();
});

test('Get Forecast', async() => {
    const mockForecast = {
        description: "testDescription",
        feelTemp: 1.2,
        humidity: 10,
        id: 1,
        latitude: 22.11,
        location: "testLocation",
        longitude: 11.11,
        maxTemp: 2.9,
        minTemp: 0.9,
        name: "testName",
        pressure: 1000,
        rain1h: 0,
        rain3h: 0,
        temp: 1.1,
        timestamp: (6) [2021, 2, 26, 18, 50, 22],
        wind: 10.1
    }
    render(<WeatherElement/>);
    let formInput = document.getElementById("forecastFormInput");
    let submitBtn = document.getElementById("forecastFormBtn");
    let forecastDetailsElement = document.getElementById("forecastDetailsElement");

    expect(formInput).toBeInTheDocument();
    expect(submitBtn).toBeInTheDocument();
    expect(forecastDetailsElement).toBeNull();

    formInput.value = "Test location";
    fireEvent.click(submitBtn)

    jest.spyOn(global, "fetch").mockImplementation(() => {
        Promise.resolve({
            json: () => Promise.resolve(mockForecast)
        })
    });

    await waitFor(() => {
        forecastDetailsElement = document.getElementById("forecastDetailsElement");
        expect(forecastDetailsElement).toBeInTheDocument();
    })

});