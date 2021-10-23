import axios from 'axios';
import React, { Component } from 'react';
import Cv from '../components/Cv'

export default class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      cv: {},
      sortby: "id",

    }
  }

  componentDidMount() {
    axios.defaults.withCredentials = false;
    axios.get("http://localhost:8080/api/cv",
    {},
    {
      params: {
        pageSize:60,
        sortBy:this.state.sortby,
        withCredentials : false
      }
    }
    ).then((response) => {this.setState({cv: response.data})}
    ).catch(error => console.log(error));
  }
  render() {
    return (
      <div className='home'>
         <Cv
        id = {this.cv.id}
        content = {this.cv.content}
        />
      </div>
    );
  }
}