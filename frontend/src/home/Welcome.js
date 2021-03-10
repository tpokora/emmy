import React from 'react';
import {Box, Container, Typography} from "@material-ui/core";

class WelcomeElement extends React.Component {

    render() {
        return (
            <Container maxWidth="false" disableGutters={true}>
                <Box p={8}>
                    <Typography variant="h4" component="h1" gutterBottom>
                        Welcome to Emmy Project ReactApp!
                    </Typography>
                </Box>
            </Container>
        )
    }
}

export default WelcomeElement;