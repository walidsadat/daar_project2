import { Link } from "react-router-dom";

const Cv = ({
    id,
    content,
    created
}) => {
  return (
    <div className="card text-center mb-3 mr-3" style={{ width: "18rem" }}>
      <div className="center-block mt-3">
          id
      </div>
      <div className="card-body">
        <h5 className="card-id">{id}</h5>
        <p className="card-content">Content: {content}$</p>
      </div>
    </div>
  );
};

export default Cv;
