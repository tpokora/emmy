import React from 'react';
import {Box, Container, Typography} from "@material-ui/core";

function RatesElement() {

    return (
        <Container maxWidth="false" disableGutters={true}>
            <Box p={8}>
                <Typography variant="h4" component="h1" gutterBottom>
                    Rates
                </Typography>
            </Box>
        </Container>
    );
}

export default RatesElement;