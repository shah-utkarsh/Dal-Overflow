import "./styles.css";
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { useState } from "react";
import axios from 'axios';
import UserLoggedIn from './UserLoggedIn';
import {useContext} from 'react';


// function GetAnswer(props){
//   const[answerBody,setAnswerBody] = useState('')
//   const[code, setCode] = useState('')

//   const handleClick = (e)=>{
//     e.preventDefault()
//     const answer = {answerBody,code}
//     console.log(answer)
//     fetch(`http://localhost:8080/questions/${props.questionID}/answers/{userId}`,{
//       method:"POST",
//       headers:{"Content-Type":"application/json"},
//       body: JSON.stringify(answer)
//     }).then(()=>{
//       console.log("Answer Updated")
//     })
//   } 
function GetAnswer(props){
  const[answerDescription,setAnswerDescription] = useState()
  const[answerCode, setAnswerCode] = useState()
  const notAUser = () => {
            alert("Please login to ask questions");
      };
  const handleClick = (e)=>{
    e.preventDefault()
    if (!answerDescription) {
        alert("Empty answer cannot be posted");
        return;
      }
    const answer = {answerDescription,answerCode }
   const questionId = localStorage.getItem("selectedQuestionId")
   const userId = localStorage.getItem("userId")
   const URL = `http://localhost:8080/questions/${questionId}/answers/${userId}`
   axios.post(URL, answer)
                   .then(response => {
                       window.alert("Your answer was posted successfully!");
                       window.location.reload(false);
                   })
                   .catch(error => {
                       if (error.response && error.response.status === 500)
                       {
                       }
                       else
                       {
                           console.error(error);
                       }
                   });
  }
  const {user} = useContext(UserLoggedIn)
  return(
    <div class="get_answer_col">
      <div>
        <h3>Your Answer</h3>
        <Box
          sx={{
            '& .MuiTextField-root': { m: 1, width: '100%' },
          }}
        >
        <TextField
          id="outlined-multiline-static"
          label="Answer"
          multiline
          rows={6}
          value = {answerDescription}
          onChange = {(e) => setAnswerDescription(e.target.value)}
        />
        </Box>
        <h3>Add Code</h3>
        {/* <pre>
          <code>

          </code>
        </pre> */}
        <Box
          sx={{
              '& .MuiTextField-root': { m: 1, width: '100%' },
          }}
        >
        <TextField
          id="outlined-multiline-static"
          label="Code"
          multiline
          rows={6}
          value = {answerCode}
          onChange = {(e) => setAnswerCode(e.target.value)}
        />
        </Box>
      </div>
      {user && (
           <button className="button1" onClick={handleClick}>Submit</button>
      )}
      {!user && (
           <button className="button1" onClick={notAUser}>Submit</button>
      )}

    </div>
  );
}
export default GetAnswer;
  