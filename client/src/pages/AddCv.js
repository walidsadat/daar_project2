import axios from 'axios';
import React, { Component } from 'react';

export default class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      content: "",
      file: null
    }
  }

  handleContentChange = (event) => {
    this.setState({content: event.target.value})
    console.log(this.state)
  }
  handleAdd = () =>{
    if(this.state.content.length > 0){
      axios.post("http://localhost:8080/api/cv/",{
        content : this.state.content
      }).catch(error => console.log(error));
    }
  }

  onFileChange = event => {
    this.setState({file: event.target.files[0]})
  }

  onFileUpload = () =>{
    const formData = new FormData();
    formData.append(
      this.state.file,
      this.state.file.name
    )

      const fs = require('fs');
      const pdf = require('pdf-parse');

      let dataBuffer = fs.readFileSync(this.state.file.path);


      pdf(dataBuffer).then(function(data) {
          axios.post("http://localhost:8080/api/cv",{
              content : data.text
          }).catch(error => console.log(error));
      });


  }

render() {
  return (
    <div>
      <form className='center'>
      <textarea type="text" onChange={this.handleContentChange}/>
      <button onClick={this.handleAdd}>
            Send
      </button>
      </form>
      <br/>
      <h3 className='center'> Upload your cv</h3>
      <div className='center'>
        <input type="file" accept=".pdf" onChange={this.onFileChange} />
        <button onClick={this.onFileUpload}>
          Send
        </button>
      </div>
    </div>
  );
}
}