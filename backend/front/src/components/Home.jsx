import React from 'react';

class Home extends React.Component {

    // eslint-disable-next-line
    constructor (props) {
        super (props);
    }
    
    render() {
        console.log("home page");
        return (
            
            <div className="mt-5">
                <h2>RPO Art Frontend</h2>
            </div>
        );
    }
}

export default Home;
