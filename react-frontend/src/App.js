import { Reset } from 'styled-reset';
import {createGlobalStyle} from 'styled-components';
import Header from "./Header";
import Footer from "./Footer";
import QuestionsFrontPage from "./QuestionsFrontPage";
import {
  BrowserRouter as Router,
  Routes,
  Route
}from "react-router-dom";
import React from 'react';
import { useState,  useEffect } from 'react';
import UserLoggedIn from './UserLoggedIn';
import LoginPage from './LoginPage';
import axios from 'axios';
import RegistrationPage from './RegistrationPage';
import UserProfile from './UserProfile';
import QuestionAnswer from './QuestionAnswer';
import SearchResult from './SearchResult';
import Register from './Register';
import QuestionAsk from './QuestionAsk';


function App() {
  const[user, setUser] = useState(null);
  const userId = localStorage.getItem("userId")
  function checkUser(){
    if (!user) {
      axios.get(`http://127.0.0.1:8080/user/login/${userId}`)
        .then(response => {
          setUser({userName: response.data});
          localStorage.setItem("username", response.data);
        })
        .catch(() => {
          setUser(null);
        })
    }
  }

  return (
    <div>
      <Reset />  
      <Router>
        <UserLoggedIn.Provider value={{user, checkUser}}>
          <Header/>
          <Routes>
            <Route path="/ask" element = {<QuestionAsk/>} />
            <Route path="/register" element = {<Register/>} />
            <Route path="/profile" element = {<UserProfile/>} />
            <Route path="/" element = {<QuestionsFrontPage/>} />
            <Route path="/question-answer" element = {<QuestionAnswer/>} />
            <Route path="/login" element = {<LoginPage/>} />
            <Route path="/registration" element = {<RegistrationPage/>} />
            <Route path="/search-result" element = {<SearchResult/>} />
          </Routes >
          <Footer/>
          </UserLoggedIn.Provider> 
      </Router>

    </div>
  );
}

export default App;
