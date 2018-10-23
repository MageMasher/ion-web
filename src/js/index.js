// Doing this because the aws-sdk uses deeply nested modules. ex. 'aws-sdk/clients/lambda'
import React from 'react';
import ReactDOM from 'react-dom';
import Lambda from 'aws-sdk/clients/lambda';
import AWS from 'aws-sdk';
window.AWS = AWS;
window.Lambda = Lambda;
window.React = React;
window.ReactDOM = ReactDOM;
