import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import { isAuthenticated } from './auth';

const PrivateRoute = ({ component: Component, ...rest }) => (
    <Route 
        {...rest}
        render={props =>
            isAuthenticated() ? (
                <Component {...props} />
            ) : (
                <Redirect to={{ pathname: "/", state: props.location}} />
            )
        }
    />
)

export default PrivateRoute;