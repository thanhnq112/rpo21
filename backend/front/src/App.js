import './App.css';
import { Router, Switch, Route, Redirect } from "react-router-dom";
import { createBrowserHistory } from "history";
import NavigationBar from './components/NavigationBar';
import Home from './components/Home';
import Login from './components/Login';
import Utils from './utils/Utils';
import { connect } from 'react-redux';
// import { Provider } from 'react-redux';
// import { store } from "./utils/Rdx";
// import { useState } from 'react';

const AuthRoute = props => {
	let user = Utils.getUserName();
	if (!user) return <Redirect to="/login" />;
	return <Route {...props} />;
}

const history = createBrowserHistory();

function App(props) {
	// const [isExpanded, setExpanded] = useState(false);

	return (
		<div className="App">
			<Router history = { history }>
				<NavigationBar /*store={store} toggleSizeBar={() => setExpanded(!isExpanded)}*/ />
				<div className="container-fluid">
					{props.error_message &&
					<div className="alert alert-danger m-1">{props.error_message}</div>
					}
					<Switch>
						<AuthRoute path="/home" exact component={Home} />
						<Route path="/login" exact component={Login} />
					</Switch>
				</div>
			</Router>
		</div>
		// <Provider store={store}>
		// </Provider>
	);
}

function mapStateToProps(state) {
    return { errorMessage: state.alert }
};

export default connect(mapStateToProps)(App);
// export default App;
