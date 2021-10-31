import './App.css';
import Navbar from './components/Navbar'
import "./components/Navbar.css"
import "./App.css"
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Home from './pages/Home';
import AddCv from './pages/AddCv';
import Cv from './pages/Cv';

function App() {
  return (
    <>
      <Router>
        <Navbar />
        <Switch>
          <Route path='/' exact component={Home} />
          <Route path='/addcv' component={AddCv} />
          <Route path={'/:id'} component={Cv} />
        </Switch>
      </Router>
    </>
  );
}

export default App;
