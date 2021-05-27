import React from 'react';
// import {connect} from "react-redux";
import { withRouter, Link } from 'react-router-dom';
// import history from "../utils/history";
import { Navbar, Nav } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHome } from '@fortawesome/free-solid-svg-icons';
// import Utils from '../utils/utils'
// import BackendService from "../services/BackendService";
// import {userActions} from "../utils/redux";

class NavigationBar extends React.Component {

    // eslint-disable-next-line
    constructor (props) {
        super (props);

        this.goHome = this.goHome.bind(this);
    }

    goHome() {
        this.props.history.push("/home");
    }

    render() {
        return (
            <Navbar bg="light" expand="lg">
                <Navbar.Brand><FontAwesomeIcon icon={ faHome }/>{' '}myRP0</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav"/>
                <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="mr-auto">
                    {/* <Nav.Link href="/home">Home</Nav.Link> */}
                    <Nav.Link as={Link} to="/home">Home</Nav.Link>
                    <Nav.Link onClick={this.goHome}>Another home</Nav.Link>
                    <Nav.Link onClick={() => { this.props.history.push("/home") }}>Yet another home</Nav.Link>
                </Nav>
                </Navbar.Collapse>
            </Navbar>
        );
    }
}

export default withRouter(NavigationBar);
