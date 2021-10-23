import './App.css';
import Navbar from './components/Navbar'
import "./components/Navbar.css"
import "./App.css"
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Home from './pages/Home';
import AddCv from './pages/AddCv';
import SearchCv from './pages/SearchCv';

function App() {
  return (
    <>
      <Router>
        <Navbar />
        <Switch>
          <Route path='/' exact component={Home} />
          <Route path='/addcv' component={AddCv} />
          <Route path='/searchcv' component={SearchCv} />
        </Switch>
      </Router>
    </>
  );
}

export default App;
