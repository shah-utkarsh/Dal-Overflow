import axios from "axios";
import React, { useState } from "react";
import './styles.css';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import IconButton from '@mui/material/IconButton';
import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import InputAdornment from '@mui/material/InputAdornment';
import FormControl from '@mui/material/FormControl';
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import { useNavigate } from "react-router-dom";

function Register(){
    const [userName, setUserName] = useState('');
    const [emailId, setEmailId] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [errors, setErrors] = useState({});
    const [showPassword, setShowPassword] = useState(false);
    const [showConfirmPassword, setShowConfirmPassword] = useState(false);

    const handleChange = (event) => {
        const { name, value } = event.target;
        if (name === 'userName') {
            setUserName(value);
        } else if (name === 'emailId') {
            setEmailId(value);
        } else if (name === 'password') {
            setPassword(value);
        } else if (name === 'confirmPassword') {
            setConfirmPassword(value);
        }
    }

    const navigate = useNavigate();

    const isValid = (str) => {
        return !/[\s~`!@#$%\^&*+=\-\[\]\\';,/{}|\\":<>\?()\._]/g.test(str);
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        
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
        } else if (isValid(password)){
            errors.password = 'Password should have at least 1 special character';
        } else if (confirmPassword !== password) {
            errors.confirmPassword = 'Password do not match';
        }

        if (Object.keys(errors).length === 0) {
            // Submit the form
            axios.post('http://127.0.0.1:8080/user/add', {userName, emailId, password})
                .then(response => {
                    navigate('/');
                    // handle success
                })
                .catch(error => {
                    // handle error
                    console.log(error);
                });
        } else {
            // Update state with errors
            setErrors(errors);
        }
    }

    const handleClickShowPassword = () => setShowPassword((showPassword) => !showPassword);

    const handleClickShowConfirmPassword = () => setShowConfirmPassword((showConfirmPassword) => !showConfirmPassword);


    return(
        <div className="LoginRegistration">
            <div className="LoginRegister">
                
                <br/><h2>Register</h2>
                <Box
                    sx={{
                        width: '100%',
                        '& > :not(style)': { m: 1, width: '90%', textAlign: 'center' },
                      }}
                    >
                    <TextField
                        id="userName"
                        label="Username"
                        name="userName"
                        value={userName}
                        onChange={handleChange}
                    />
                </Box>
                {errors.name && <div className="error">{errors.name}</div>}
                <Box
                    sx={{
                        width: '100%',
                        '& > :not(style)': { m: 1, width: '90%', textAlign: 'center' },
                      }}
                    >
                    <TextField
                        id="emailId"
                        label="Email ID"
                        name="emailId"
                        value={emailId}
                        onChange={handleChange}
                    />
                    </Box>
                    {errors.email && <div className="error">{errors.email}</div>}
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
                            placeholder="Password"
                            label="Password"
                            id="password"
                            name="password"
                            value={password} // Added value prop
                            onChange={handleChange}
                        />
                    </FormControl>
                    {errors.password && <div className="error">{errors.password}</div>}
                    <FormControl sx={{ m: 1, width: '90%', textAlign:'center' }} variant="outlined">
                        <InputLabel htmlFor="outlined-adornment-password">Confirm Password</InputLabel>
                        <OutlinedInput
                            type={showConfirmPassword ? 'text' : 'password'}
                            endAdornment={
                            <InputAdornment position="end">
                                <IconButton
                                    aria-label="toggle password visibility"
                                    onClick={handleClickShowConfirmPassword}
                                    edge="end"
                                >
                                {showConfirmPassword ? <VisibilityOff /> : <Visibility />}
                                </IconButton>
                            </InputAdornment>
                             }
                            placeholder="Confirm Password"
                            label="Password"
                            id="confirmPassword"
                            name="confirmPassword"
                            value={confirmPassword} // Added value prop
                            onChange={handleChange}
                        />
                    </FormControl>
                    {errors.confirmpassword && <div className="error">{errors.confirmpassword}</div>}
                    <div className="button1"onClick={handleSubmit}>Register</div> <br/>
            </div>
        </div>
    );
}


export default Register;
