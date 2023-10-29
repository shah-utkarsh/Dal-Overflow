import { useContext  } from "react";
import "./styles.css";
import {Link} from "react-router-dom";
import UserLoggedIn from './UserLoggedIn';
import axios from "axios";

function QuestionDetails(props) {

  const questionDate = props.questionDate;
  const questionUserId = localStorage.getItem("questionUserId");
  const userLoggedin = localStorage.getItem("userId");

  const ifUser = () => {
    if(questionUserId === userLoggedin){
        deleteQuestion();
    }
    else{
        alert("Only the owner can delete the question");
    }
  }
  const loginUser = () =>{
     alert("Please login to ask questions");
  }
  const deleteQuestion = async () => {
        try {
            const questionID = localStorage.getItem("selectedQuestionId");
            const response = await axios.delete(`http://localhost:8080/api/questions/${questionID}`);
            alert("Question deleted successfully!");
        } catch (error) {
            console.error(error);
            alert("Failed to delete question!");
        }
  };
  const notUser = () => {
    alert("Please login to delete the question");
  }
    const {user} = useContext(UserLoggedIn)
    return(
      <div className="jsBeginnerWantToInsertTeParent">
        <div class="question_details_row">
          <div class="question_col">
            <div class="question"><h2>{props.questionTitle}</h2></div>
          </div>
          {user && (<div class="button_col">
            <Link to="/ask">
              <div class="button1" >Ask Question</div>
            </Link>
          </div>)}
          {!user && (<div class="button_col">
               <div class="button1" onClick={loginUser} >Ask Question</div>
          </div>)}
          {user && (<div>
               <div class="button1" onClick={ifUser}>Delete Question</div>
          </div>)}
          {!user && (<div>
                <div class="button1" onClick={notUser}>Delete Question</div>
          </div>)}
        </div>
  
        <div class="question_details">
          <div class="details_module"><p><strong><span>Asked</span></strong><span> </span>{questionDate}</p></div>
        </div>
        <div class="question_des_row">
          <div class="question_des"><body>{props.questionDescription}</body></div>
          <div class="question_answer_code">{props.questionCode}</div>
          <div class="question_tags_col">

          {props.questionTags?.map(tag => (
            <tags>{tag}</tags>
            ))}
          </div>
        </div>
        <div class="question_answer_count"><h3>{props.questionAnswerCount}<span> </span>Answers</h3></div>
      </div>
    );

}

export default QuestionDetails;