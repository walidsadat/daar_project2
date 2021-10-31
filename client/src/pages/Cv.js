import React, { Component } from 'react';
import axios from 'axios';


export default class Cv extends Component {
  constructor(props) {
    super(props);
    this.state = {
      id: this.props.match.params.id,
      content: "",
      created: ""
    }
  }

  componentDidMount() {
    axios.defaults.withCredentials = false;
    axios.get("http://localhost:8080/api/cv/" + this.state.id).then((response) =>{console.log(response); this.setState({id: response.data.id, content: response.data.content, created: response.data.created})}
    ).catch(error => console.log(error));
  }

 render() {
   let day = this.state.created.split("T")[0]
   let time = this.state.created.split("T")[1]
   if(time)
      time = time.split(".")[0]
  return (
    <div>
    <div className="center">
      <h4>Id: </h4><br/>
      {this.state.id} <br/>
    </div>
    <div className="center">
      <h4>Created: </h4> <br/>
      {day} <br/>
    </div>
    <div className="center">
      <h4>On: </h4> <br/>
      {time} <br/>
    </div>
      
      <div className="center">
    <h4> Content: </h4> <br/>
    {this.state.content}<br/>

      </div>
      </div>
      
  );
}
}