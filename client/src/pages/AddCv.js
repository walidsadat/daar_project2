import axios from 'axios';
import React, { Component } from 'react';
import { CLIENT_URL, API_URL } from "../App"

export default class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      content: "",
      file: null
    }
  }

  handleContentChange = (event) => {
    this.setState({ content: event.target.value })
    console.log(this.state)
  }
  handleAdd = () => {
    if (this.state.content.length > 0) {
      axios.post(API_URL, {
        content: this.state.content
      }).catch(error => console.log(error));
    }
  }

  onFileChange = event => {
    this.setState({ file: event.target.files[0] })
  }

  onFileUpload = () => {
    if (this.state.file) {
      const formData = new FormData();
      formData.append('file', this.state.file)
      axios.post(API_URL + "file", formData)
        .then(alert("Cv ajouté"))
        .then(window.location.replace(CLIENT_URL))
        .catch(error => console.log(error));
    }
  }

  render() {
    return (
      <div>
        <form className='center'>
          <textarea type="text" onChange={this.handleContentChange} />
          <button onClick={this.handleAdd}>
            Add
          </button>
        </form>
        <br />
        <h3 className='center'> Upload your cv</h3>
        <div className='center'>
          <input type="file" accept=".pdf" onChange={this.onFileChange} />
          <button onClick={this.onFileUpload}>
            Upload
          </button>
        </div>
      </div>
    );
  }
}