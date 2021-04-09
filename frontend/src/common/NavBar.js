import React from 'react';
import {AppBar, Button, makeStyles, Toolbar} from "@material-ui/core";

import {Link as RouterLink} from 'react-router-dom';
import Link from '@material-ui/core/Link';

function NavBarElement() {

    const useStyles = makeStyles((theme) => ({
        root: {},
        linkElement: {
            marginLeft: theme.spacing(2),
        },
        lastElement: {
            flexGrow: 1
        }
    }));

    const classes = useStyles()

    return (<div className={classes.root}>
        <AppBar>
            <Toolbar>
                <Link to="/" component={RouterLink} variant="h5" color="inherit">
                    Home
                </Link>
                <Link to="/weather" component={RouterLink} className={classes.linkElement} variant="h6" color="inherit">
                    Weather
                </Link>
                <Link to="/rates" component={RouterLink} className={[classes.linkElement, classes.lastElement]}
                      variant="h6" color="inherit">
                    Rates
                </Link>
                <Button color="inherit">Login</Button>
            </Toolbar>
        </AppBar>
    </div>)
}

export default NavBarElement;