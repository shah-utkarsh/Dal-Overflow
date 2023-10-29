const CommentDisplay = ({ allComments }) => {
    if (!allComments) {
        return <div>No comments</div>;
    }
    return(
        <div>
        {allComments.map((data) => (
            <div className="CommentDisplay">
                <div class="CommentText"><body>{data.commentText}</body></div>
                <div class ="CommentDate"><body>Asked at {data.commentDate}</body></div>
            </div>
        ))}
        </div>
        
    );
}

export default CommentDisplay;