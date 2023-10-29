import QuestionList from './QuestionList';
import { useLocation } from 'react-router-dom';


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


function SearchResult() {
    const location = useLocation();
    const results = location.state.results;
    return (
        <div className='jsBeginnerWantToInsertTeParent'>
            <div className="TopRow">
                <h2>Search Results</h2>
            </div>
            <DisplayQuestionList questions={results} />
        </div> 
        
    );
}

export default SearchResult;