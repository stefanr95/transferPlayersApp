import React from "react";
import { Container, Nav, Navbar} from "react-bootstrap";
import {
  HashRouter as Router,
  Switch,
  Route,
  Redirect,
  Link,
} from "react-router-dom";
import ReactDOM from "react-dom";
import Login from "./components/authorization/Login";

class App extends React.Component {
  render() {
    const jwt = window.localStorage["jwt"];

    if (jwt) {
      return (
        <div>
          <Router>
              <Navbar.Brand as={Link} to="/">
                Home
              </Navbar.Brand>
              <Nav className="mr-auto">
                <Nav.Link as={Link} to="/igraci">
                  Igraci
                </Nav.Link>
              </Nav>                        
            <Container style={{ paddingTop: "10px" }}>             
            </Container>
          </Router>
        </div>
      );
    } else {
      return (
        <Container>
          <Router>
            <Switch>
            <Route exact path="/login" component={Login} />
              <Route render={() => <Redirect to="/login" />} />      
            </Switch>
          </Router>
        </Container>
      );
    }
  }
}

ReactDOM.render(<App />, document.querySelector("#root"));
