import {Link} from 'react-router-dom';
import QuestionList from './QuestionList';
import React, { useState, useEffect } from 'react';
import UserLoggedIn from './UserLoggedIn';
import {useContext} from 'react';


function DisplayQuestionList(props) {
    return (
        <ul>
            {props.questions.map(question => (
                <li key={question.questionId}>
                    <QuestionList question={question}/>
                </li>
            ))}
        </ul>
    );
}


function QuestionsFrontPage() {
    const [questions, setQuestions] = useState([]);
    useEffect(() => {
        fetch('http://localhost:8080/api/question/fetchTopQuestions')
            .then(response => response.json())
            .then(data => setQuestions(data));
    }, []);
    const handleClick = () => {
        if (localStorage.getItem("userId") === null) {
          alert("Please login to ask questions");
        }
    };
    const {user} = useContext(UserLoggedIn)
    return (
        <main>
            <div className="TopRow">
                <h2>Top Questions</h2>
                {user && (
                      <Link to={'/ask'}>
                        <div className="button1" >Ask Question</div>
                       </Link>      
                       )}
                {!user && (
                    <div>
                        <div className="button1" onClick={handleClick}>Ask Question</div>
                     </div>
                       )}

            </div>
            <DisplayQuestionList questions={questions} />
        </main>
    );
}

export default QuestionsFrontPage;