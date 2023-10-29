import styled from "styled-components";
import axios from "axios";
import React, { Component } from 'react';
import {Navigate} from "react-router-dom";
import './styles.css';
import './Form.css';

const Container = styled.h1`
    font-size: 1.5rem;
    margin-bottom: 10px;
    margin-top: 10px;
    margin-left: 10px;
`;


const UsernameInput = styled.input`
    background: none;
    padding: 7px;
    width: 70%;
    color: #fff;
    border: 2px solid #aaa;
    display: block;
    box-sizing: border-box;
    margin-left: 10px;
`;

const PasswordInput = styled.input`
    background: none;
    padding: 7px;
    width: 70%;
    color: #fff;
    border: 2px solid #aaa;
    display: block;
    box-sizing: border-box;
    margin-left: 10px;
`;

const ConfirmPasswordInput = styled.input`
    background: none;
    padding: 7px;
    width: 70%;
    color: #fff;
    border: 2px solid #aaa;
    display: block;
    box-sizing: border-box;
    margin-left: 10px;
`;

const SubmitButton = styled.button`
    font-size: 1.1rem;
    color:#fff;
    border:0;
    background-color: #378ad3;
    border-radius: 5px;
    text-decoration: none;
    margin-top: 10px;
    margin-left: 10px;
`;

const Fieldlabel = styled.label`
    padding: 10px;
`;

class RegistrationPage extends Component{
    constructor(props) {
        super(props);
        this.state = {
            userName: '',
            emailId: '',
            password: '',
            confirmpassword: '',
            errors: {}
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    
    handleChange(event) {
        this.setState({ [event.target.name]: event.target.value });
    }

    isValid(str){
        return !/[\s~`!@#$%\^&*+=\-\[\]\\';,/{}|\\":<>?()._]/g.test(str);
    }
    
    handleSubmit(event) {
    event.preventDefault();
    const { userName, emailId, password, confirmpassword } = this.state;
    
    // Check for errors
    let errors = {};
    if (userName === '') {
        errors.name = 'Name is required';
    } else if (userName.length > 10){
        errors.name = 'Username cannot be more than 10 characters';
    }
    if (emailId === '') {
        errors.email = 'Email is required';
    } else if (!/\S+@\S+\.\S+/.test(emailId)) {
        errors.email = 'Email is invalid';
    }
    if (password === '') {
        errors.password = 'Password is required';
    } else if (password.length < 6) {
        errors.password = 'Password must be at least 6 characters long';
    } else if (this.isValid(password)){
        errors.password = 'Password should have atleast 1 special character';
    } else if (confirmpassword !== password) {
        errors.confirmpassword = 'Password do not match';
    } 

    if (Object.keys(errors).length === 0) {
    // Submit the form
    axios.post('http://127.0.0.1:8080/user/add',{userName:this.state.userName, emailId:this.state.emailId, password:this.state.password})
        .then(response => {
        this.setState({redirectToHomePage: true})
        })
        .catch((error) => {
                        if (error.response) {
                            console.log(error.response.data);
                            console.log(error.response.status);
                            console.log(error.response.headers);
                        } else if (error.request) {
                            console.log(error.request);
                        } else {
                            console.log('Error', error.message);
                        }
                    })
    } else {
        // Update state with errors
        this.setState({ errors });
    }
}
    
      render() {
        const { userName, emailId, password, confirmpassword, errors } = this.state;
    
        return (<>{this.state.redirectToHomePage && (
            <Navigate to = {'/'} />
        )}
        <form onSubmit={this.handleSubmit}>
            <Container><h1>Registration</h1></Container>
            <br />
            <Fieldlabel htmlFor="name">Please enter your username:</Fieldlabel>
            <div>
                <UsernameInput
                placeholder="Username"
                type="text"
                id="userName"
                name="userName"
                value={userName}
                onChange={this.handleChange}
                />
                {errors.name && <div className="error">{errors.name}</div>}
            </div>
            <br />
            <Fieldlabel htmlFor="email">Please enter your email address:</Fieldlabel>
            <div>
                <UsernameInput
                placeholder="Email"
                type="email"
                id="emailId"
                name="emailId"
                value={emailId}
                onChange={this.handleChange}
                />
                {errors.email && <div className="error">{errors.email}</div>}
            </div>
            <br />
            <Fieldlabel htmlFor="password">Create a password:</Fieldlabel>
            <div>
                <PasswordInput
                placeholder="Password"
                type="password"
                id="password"
                name="password"
                value={password}
                onChange={this.handleChange}
                />
                {errors.password && <div className="error">{errors.password}</div>}
            </div>
            <br />
            <Fieldlabel htmlFor="confirmpassword">Re-enter your password:</Fieldlabel>
            <div>
                <ConfirmPasswordInput
                placeholder="Confirm password"
                type="password"
                id="confirmpassword"
                name="confirmpassword"
                value={confirmpassword}
                onChange={this.handleChange}
                />
                {errors.confirmpassword && <div className="error">{errors.confirmpassword}</div>}
            </div>
            <br />
            <SubmitButton type="submit">Submit</SubmitButton>
            </form>
            </>);
            }
        }    
export default RegistrationPage;

