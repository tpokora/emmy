import {Box, Container, Typography} from "@material-ui/core";
import React from "react";

function Login() {

    return (
        <Container maxWidth="false" disableGutters={true}>
            <Box p={8}>
                <Typography variant="h4" component="h1" gutterBottom>
                    Login
                </Typography>
            </Box>
        </Container>)

}

export default Login