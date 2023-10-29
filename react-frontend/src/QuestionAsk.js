import axios from 'axios';
import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import Autocomplete from '@mui/material/Autocomplete';
import Stack from '@mui/material/Stack';
import { useNavigate } from "react-router-dom";

export default function QuestionAsk(){

    const Tags = [
        { title: 'Java'},
        { title: 'Python'},
        { title: 'C'},
        { title: 'C++'},
        { title: 'HTML'},
        { title: "Cloud Computing"},
        { title: 'SQL'},
    ];

    const [userInfo, setuserInfo] = useState({
        title: '',
        description: '',
        information: '',
    });
    
    const ondescription = (event) => {
        const { value } = event.target;
        setuserInfo({
          ...userInfo,
          description: value
        });
    }

    const oninformation = (event) => {
        const { value } = event.target;
        setuserInfo({ ...userInfo,
            information:value
          });
    }

    const ontitle = (event) => {
        const { value } = event.target;
        setuserInfo({ ...userInfo,
            title:value
          });
    }

    const [setSuccess] = useState(false);
    const [setErrorMessage] = useState('');
    const api = axios.create({
        baseURL: "http://localhost:8080"
    });

    const [selectedTags, setSelectedTags] = useState([]);

    const navigate = useNavigate();

    const handleAutocompleteChange = (event, values) => {
        setSelectedTags(values);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        const question = {
            questionTitle: userInfo.title,
            questionDescription: userInfo.description,
            questionCode: userInfo.information,
            userID: localStorage.getItem("userId"),
            tags: selectedTags.map((tag) => tag.title),
        };
        const confirmed = window.confirm("Are you sure you want to submit the form?");
        if(confirmed){
            await axios.post('http://localhost:8080/api/question/postQuestion', question)
                .then(response => {
                    setSuccess(true);
                    window.alert("Your question was posted successfully!");
                    navigate('/');
                })
                .catch(error => {
                    if (error.response && error.response.status === 500) {
                        setErrorMessage('We had some trouble processing. Please try again');
                    }
                    else{
                        console.error(error);
                    }
                });
        }
    };

    return(
        <div className="jsBeginnerWantToInsertTeParent">
            <div className="AskQuestion">
                <div className="QuestionColumn">
                    <div className="AskTitle"><h2>Describe your question</h2></div>
                    <Box
                    sx={{
                        width: '100%',
                        '& > :not(style)': { m: 1, width: '90%' },
                    }}
                    >
                    <TextField
                        placeholder="Introduce the problem."
                        name="title"
                        value={userInfo.title}
                        onChange={ontitle}
                    />
                    </Box>
                    <Box
                    sx={{
                        width: '100%',
                        '& > :not(style)': { m: 1, width: '90%' },
                    }}
                    >                                    
                    <TextField
                        id="outlined-multiline-static"
                        placeholder="Describe any difficulties that have prevented you from solving it yourself"
                        name="body"
                        multiline
                        value={userInfo.description}
                        onChange={ondescription}
                        rows={6}
                    />
                    </Box> 
                    <h3>Additional Information and Code</h3>
                    <Box
                    sx={{
                        width: '100%',
                        '& > :not(style)': { m: 1, width: '90%' },
                    }}
                    >                        
                    <TextField
                        id="outlined-multiline-static"
                        placeholder="Please enter additional information and code"
                        multiline
                        value={userInfo.information}
                        onChange={oninformation}
                        rows={6}
                    />
                    </Box>
                    <h3>Select appropriate tags</h3>
                    <Stack spacing={1} sx={{ width: '91%' }}>
                        <Autocomplete
                            multiple
                            id="tags-outlined"
                            options={Tags}
                            getOptionLabel={(option) => option.title}
                            filterSelectedOptions
                            value={selectedTags}
                            onChange={handleAutocompleteChange}
                            renderInput={(params) => (
                            <TextField
                                {...params}
                                label="Tags"
                                placeholder="Tags"
                            />
                            )}
                        />
                    </Stack>
                    <br/>
                    <button className="button1" onClick={handleSubmit}>Submit</button>
                </div>
            </div>
        </div>        
    );
}
