import { useNavigate } from "react-router-dom";


function QuestionList(props) {

  const navigate = useNavigate();

  function handleQuestionClickEvent(question){
    localStorage.setItem("selectedQuestionId",question.questionID)
    localStorage.setItem("questionUserId",question.userID)
    navigate('/question-answer');
  }

    const { question } = props;
    if (!question) {
        return <div>Loading...</div>;
    }
    if (question.length === 0) {
        return <div>No questions found.</div>;
    } else if (question.error) {
        return <div>Error retrieving questions.</div>;
    }

    return (
      <div className='jsBeginnerWantToInsertTeParent'>
        <div className='Questions'>
          <div className='QuestionListCol'>
            <div className='QuestionInfoList'>
              <div style={{ position: 'absolute', top: 65, left: 300  }}><strong><span>{question.answerCount}</span></strong><span> </span>Answers</div>
            </div>
          </div>
          <div className='QuestionDesCol'>
            {/* <div onClick={() => handleQuestionClickEvent(question.questionID)} ><Link to={"./question-answer"}><h3>{question.questionTitle}</h3></Link></div> */}
            <div onClick={() => handleQuestionClickEvent(question)} ><h3>{question.questionTitle}</h3></div>
            <div>{question.tags.map(tag => (
                        <tags key={tag}>{tag}</tags>
                    ))}
            </div>
            <div className='QuestionStats' style={{ position: 'absolute', top: 85, right: 150  }}>Asked by {question.userName} at {question.questionDate}</div>
          </div>
        </div>
      </div>
    );
}

export default QuestionList;
