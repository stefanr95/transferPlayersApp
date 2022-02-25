import React from "react";
import { Container, Nav, Navbar, Button } from "react-bootstrap";
import {
  HashRouter as Router,
  Switch,
  Route,
  Redirect,
  Link,
} from "react-router-dom";
import ReactDOM from "react-dom";
import Home from "./components/Home";
import NotFound from "./components/NotFound";
import { logout } from "./service/auth";
import Login from "./components/authorization/Login";
import Igraci from "./components/igraci/Igraci";
import Create from "./components/igraci/Create";
import Transfer from "./components/igraci/Transfer";

class App extends React.Component {
  render() {
    const jwt = window.localStorage["jwt"];

    if (jwt) {
      return (   
        <div>       
          <Router>
            <Navbar expand bg="dark" variant="dark">
              <Navbar.Brand as={Link} to="/">
                Home
              </Navbar.Brand>
              <Nav className="mr-auto">
                <Nav.Link as={Link} to="/igraci">
                  Igraci
                </Nav.Link>
              </Nav>
              <Button onClick={() => logout()}>Logout</Button>
            </Navbar>
            <Container style={{ paddingTop: "10px" }}>
              <Switch>
                <Route exact path="/" component={Home} />
                <Route
                  exact
                  path="/login"
                  render={() => <Redirect to="/" />}
                />
                  <Route exact path="/igraci" component={Igraci}/>
                  <Route exact path="/igraci/create" component={Create}/>
                  <Route exact path="/igraci/transfer/:id" component={Transfer}/>                              
                <Route component={NotFound} />
              </Switch>
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
