import axios from 'axios';
import React, { Component } from 'react';
import CvIcon from "../assets/5112.png"
import "./Home.css"

export default class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      cvs: [],
      img: "../assets/5112.png",
      search: ""
    }
  }

  componentDidMount() {
    axios.defaults.withCredentials = false;
    axios.post("http://localhost:8080/api/cv/all",
    {},
    {
      params: {
        sortBy:"created",
        withCredentials : false
      }
    }
    ).then((response) => {this.setState({cvs: response.data})}
    ).catch(error => console.log(error));
  }

  handleSearchChange = (event) => {
    this.setState({search: event.target.value})
  }

  handleSearch = () =>{
    if(this.state.search.length > 0){
      axios.post("http://localhost:8080/api/cv/search",{
        fields : ["content"],
        searchTerm : this.state.search
      })
      .then((response) => {this.setState({cvs: response.data})}
      ).catch(error => console.log(error));
    }
    else{
      axios.defaults.withCredentials = false;
      axios.post("http://localhost:8080/api/cv/all",
      {},
      {
        params: {
          pageSize:60,
          sortBy:"created",
          withCredentials : false
        }
      }
      ).then((response) => {this.setState({cvs: response.data})}
      ).catch(error => console.log(error));
    }
  }

  render() {
    return (
      <div>
        <div className ="center">
          <input 
          type="text"
          id="search-bar"
          placeholder="Skill to search"
          onChange={this.handleSearchChange}/>
          <button onClick={this.handleSearch}>
            Search
          </button>
        </div>
        {this.state.cvs.map((cv) => (
          <div className="center">
          <a href={"http://localhost:3000/"+ cv.id}>
          <img src={CvIcon} width={50} height={50} alt={cv.id}/>
          {"added on :" + cv.created.split("T")[0]}
          <br/>
          {"id : " + cv.id.split("-")[0]}
          <br/>
          </a>
          </div>
        ))}
      </div>
    );
  }
}