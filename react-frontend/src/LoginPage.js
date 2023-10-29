import axios from "axios";
import './styles.css';
import React, { useState } from "react";
import { Link, Navigate } from "react-router-dom";
import UserLoggedIn from "./UserLoggedIn";
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import IconButton from '@mui/material/IconButton';
import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import InputAdornment from '@mui/material/InputAdornment';
import FormControl from '@mui/material/FormControl';
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';



function LoginPage() {

    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');
    const [errors, setErrors] = useState({});
    const [redirectToHomePage, setRedirectToHomePage] = useState(false);
    const [showPassword, setShowPassword] = useState(false);

    const handleChange = (event) => {
        const { id, value } = event.target;
        if (id === 'userName') {
            setUserName(value);
        } else if (id === 'password') {
            setPassword(value);
        }
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        // Check for errors
        let errors = {};
        if (userName === '') {
        errors.name = 'Name is required';
        } 
        if (password === '') {
        errors.password = 'Password is required';
        }
        if (Object.keys(errors).length === 0) {
            // Submit the form
            axios.post("http://127.0.0.1:8080/user/login", {
                username: userName,
                password: password,
            })
            .then((response) => {
                localStorage.setItem("userId", response.data.userId);
                window.location.href = '/';
                // this.context.checkUser(); // You may need to modify this part depending on your code
                setRedirectToHomePage(true);
            })
            .catch((error) => {
                if (error.response) {
                console.log(error.response.data);
                console.log(error.response.status);
                console.log(error.response.headers);
                } else if (error.request) {
                console.log(error.request);
                } else {
                console.log("Error", error.message);
                }
                alert("Invalid username or password");
            });
        } else {
            setErrors(errors);
        }
    }

    const handleClickShowPassword = () => setShowPassword((showPassword) => !showPassword);

    // const handleMouseDownPassword = (event: React.MouseEvent<HTMLButtonElement>) => {
    //     event.preventDefault();
    // };

    const handleKeyDown = (event) => {
        if (event.key === 'Enter') {
            handleSubmit(event);
        }
    }


    return(
        <>
            {redirectToHomePage && (<Navigate to="/" />)}
            <div className="LoginRegistration">
                <div className="LoginRegister"><br/><h2>Login</h2>
                    <Box
                    sx={{
                        width: '100%',
                        '& > :not(style)': { m: 1, width: '90%', textAlign: 'center' },
                      }}
                    >
                    <TextField
                        id="userName"
                        label="username"
                        name="Username"
                        autoComplete="username"
                        value={userName}
                        onChange={handleChange}
                    />
                    </Box>
                    {errors.name && <div className="error">{errors.name}</div>}
                    <br/>
                    <FormControl sx={{ m: 1, width: '90%', textAlign:'center' }} variant="outlined">
                        <InputLabel htmlFor="outlined-adornment-password">Password</InputLabel>
                        <OutlinedInput
                            type={showPassword ? 'text' : 'password'}
                            endAdornment={
                            <InputAdornment position="end">
                                <IconButton
                                    aria-label="toggle password visibility"
                                    onClick={handleClickShowPassword}
                                    edge="end"
                                >
                                {showPassword ? <VisibilityOff /> : <Visibility />}
                                </IconButton>
                            </InputAdornment>
                             }
                            label="Password"
                            id="password"
                            name="password"
                            value={password} // Added value prop
                            onChange={handleChange}
                            onKeyDown={handleKeyDown}
                        />
                    </FormControl>
                    {errors.password && <div className="error">{errors.password}</div>}
                    <br/>
                    <div className="button1"onClick={handleSubmit}>Login</div>
                    <br/>
                    <div className="PromptRegister">
                        <body>Don't have an account?</body>
                        <div className="LinkText"><Link to="/register">SignUp Instead</Link></div>
                    </div>
                    <br/><br/>
                </div>
            </div>
        </>
    );
}

LoginPage.contextType = UserLoggedIn;  

export default LoginPage;