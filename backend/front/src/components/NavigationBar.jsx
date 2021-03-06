import React from 'react';
import { connect } from 'react-redux';
import { withRouter, Link } from 'react-router-dom';
// import history from "../utils/history";
import { Navbar, Nav } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHome, faUser } from '@fortawesome/free-solid-svg-icons';
import Utils from '../utils/Utils';
import BackendService from "../services/BackendService";
import {userActions} from "../utils/Rdx";

class NavigationBar extends React.Component {

    // eslint-disable-next-line
    constructor (props) {
        super (props);

        this.goHome = this.goHome.bind(this);
        this.logout = this.logout.bind(this);
        this.goLogin = this.goLogin.bind(this);
    }

    goHome() {
        this.props.history.push("/home");
        window.location.reload();
    }
    
    goLogin() {
        this.props.history.push("/login");
        window.location.reload();
    }

    logout() {
        BackendService.logout().finally( () => {
            // Utils.removeUser();
            this.props.dispatch(userActions.logout());
            // this.goHome();
            this.props.history.push('/login');
        })
    }

    render() {
        let uname = Utils.getUserName();
        return (
            <Navbar bg="light" expand="lg">
                <Navbar.Brand><FontAwesomeIcon icon={ faHome }/>{' '}myRP0</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav"/>
                <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="mr-auto">
                    {/* <Nav.Link href="/home">Home</Nav.Link> */}
                    <Nav.Link as={Link} onClick={this.goHome}>Home</Nav.Link>
                    <Nav.Link onClick={this.goHome}>Another home</Nav.Link>
                    <Nav.Link onClick={() => { this.props.history.push("/home"); window.location.reload(); }}>Yet another home</Nav.Link>
                </Nav>
                </Navbar.Collapse>
                <Navbar.Text>{uname}</Navbar.Text>
                {/* <Navbar.Text>{this.props.user && this.props.user.name}</Navbar.Text> */}
                { this.props.user &&
                    <Nav.Link onClick={this.logout}><FontAwesomeIcon icon={faUser} fixedWidth/>{' '}??????????</Nav.Link>
                }
                { !this.props.user &&
                    <Nav.Link onClick={this.goLogin}><FontAwesomeIcon icon={faUser} fixedWidth/>{' '}????????</Nav.Link>
                }
            </Navbar>
        );
    }
}

function mapStateToProps(state) {
    const { user } = state.authentication;
    return { user };
}

export default connect(mapStateToProps)(withRouter(NavigationBar));
