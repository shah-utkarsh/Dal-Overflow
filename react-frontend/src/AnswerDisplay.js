import { useState, useEffect, useContext } from "react";
import ButtonGroup from '@mui/material/ButtonGroup';
import Button from '@mui/material/Button';
import "./styles.css";
import Comment from "./Comment";
import CommentDisplay from "./CommentDisplay";
import axios from "axios";
import UserLoggedIn from './UserLoggedIn';


function AnswersDisplay(props){
  const [hasUpvoted, setHasUpvoted] = useState(false);
  const [hasDownvoted, setHasDownvoted] = useState(false);
  const answerId = props.data.answerID;

  useEffect(() => {
    const upvoted = localStorage.getItem(`answer-${answerId}-upvoted`);
    const downvoted = localStorage.getItem(`answer-${answerId}-downvoted`);

    if (upvoted) {
      setHasUpvoted(true);
      setHasDownvoted(false);
    } else if (downvoted) {
      setHasUpvoted(false);
      setHasDownvoted(true);
    } else {
      setHasUpvoted(false);
      setHasDownvoted(false);
    }
  }, [answerId]);

  const handleUpVote = () => {
    const userId = localStorage.getItem("userId");

    if (!hasUpvoted && !localStorage.getItem(`answer-${answerId}-upvoted-${userId}`)) {
      axios
        .put(`http://localhost:8080/questions/${answerId}/votes?direction=1&userId=${userId}`)
        .then(response => {
          setHasUpvoted(true);
          setHasDownvoted(false);
          props.data.votes = response.data.votes;
          localStorage.setItem(`answer-${answerId}-upvoted-${userId}`, true);
          localStorage.removeItem(`answer-${answerId}-downvoted-${userId}`);
        })
        .catch(error => {
          console.log(error);
        });
    }
  };

  const handleNotAUser = () =>{
      alert("Please login to vote the answers");
  }

  const handleDownVote = () =>{
    const userId = localStorage.getItem("userId");

    if (!hasDownvoted && !localStorage.getItem(`answer-${answerId}-downvoted-${userId}`)) {
      const votesChange = hasUpvoted ? -2 : -1;
      axios.put(`http://localhost:8080/questions/${answerId}/votes?direction=-1&userId=${userId}`)
        .then(response => {
          setHasDownvoted(true);
          setHasUpvoted(false);
          props.data.votes += votesChange;
          localStorage.setItem(`answer-${answerId}-downvoted-${userId}`, true);
          localStorage.removeItem(`answer-${answerId}-upvoted-${userId}`);
        })
        .catch(error => {
          console.log(error);
        });
    }
  };

  const {user} = useContext(UserLoggedIn)
  return (
    <div className="jsBeginnerWantToInsertTeParent">
      <div class="answer_row">
        <div class="votes_col">
          {user && (
          <ButtonGroup
            disableElevation
            variant="contained"
            aria-label="Disabled elevation buttons"
            orientation="vertical"
          >

            <Button sx={{ fontSize: '10px', padding: '5px 12px' }} onClick={handleUpVote} disabled={hasUpvoted}>
              Up
            </Button>
            <votes>{props.data.votes}</votes>
            <Button sx={{ fontSize: '10px', padding: '5px 12px' }}  onClick={handleDownVote} disabled={hasDownvoted}>
              Down
            </Button>
          </ButtonGroup>)}

          {!user && (
             <ButtonGroup
               disableElevation
               variant="contained"
               aria-label="Disabled elevation buttons"
               orientation="vertical"
               >
               <Button sx={{ fontSize: '10px', padding: '5px 12px' }} onClick={handleNotAUser} disabled={hasUpvoted}>
                  Up
               </Button>
               <votes>{props.data.votes}</votes>
               <Button sx={{ fontSize: '10px', padding: '5px 12px' }} onClick={handleNotAUser} disabled={hasDownvoted}>
                  Down
               </Button>
               </ButtonGroup>)}
                  </div>
                  <div class="answer_col">
                    <div class="answer_des">
                      <body>{props.data.answerDescription}</body>
                    </div>
                    <div class="question_answer_code">{props.data.answerCode}</div>  
                  </div>
                  <div class="comment_col">
                    <div class="comment">
                      <h4>Comments</h4>
                      <CommentDisplay allComments = {props.data.allComments}/>
                      <Comment answerID = {props.data.answerID}/>
                    </div>
                  </div>
                </div>
          </div>

            );
          }

          export default AnswersDisplay;

