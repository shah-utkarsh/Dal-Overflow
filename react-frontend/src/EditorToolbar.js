import React from "react";
import "./CheckBoxes.css"


// Modules object for setting up the Quill editor
export const modules =(props)=>({
  toolbar: {
    container: "#" + props,

  },
  history: {
    delay: 500,
    maxStack: 100,
    userOnly: true,
    
  }
});

// Formats objects for setting up the Quill editor
export const formats = [
  "code-block"
];


// Quill Toolbar component
export const QuillToolbar = (props) => {
  return  (<> 
  {props.toolbarId !== undefined && 
  <div id={props.toolbarId}>
    <span className="ql-formats">
      <button className="ql-code-block" />
    </span>
    
  </div>
 }
 </>)
 }
export default QuillToolbar;