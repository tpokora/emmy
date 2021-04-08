import React from 'react';
import {Box, Container, Typography} from "@material-ui/core";

function UsersElement() {

    return (
        <Container maxWidth="false" disableGutters={true}>
            <Box p={8}>
                <Typography variant="h4" component="h1" gutterBottom>
                    Users
                </Typography>
            </Box>
        </Container>
    );
}


export default UsersElement;