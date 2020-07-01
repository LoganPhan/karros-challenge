import React from "react";
import "./Footer.scss";
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
const Footer = () =>{

    return (
        <footer>
            <Row>
                <Col className="left-box">
                    <span>THEMOVIEBOX</span>
                </Col>
                <Col className="right-box">
                    <span>About</span>
                    <span>Movie</span>
                    <span>Ratings</span>
                    <span>Contact</span>
                </Col>
            </Row>
            <Row>
                <Col className="left-box">
                    <span>Design by Milan Houster. All rights to reserved</span>
                </Col>
                <Col className="right-box">
                    <i className="fa-icon"></i>
                    <i className="pin-icon"></i>
                    <i className="tw-icon"></i>
                    <i className="in-icon"></i>
                </Col>
            </Row>
        </footer>
    )
}

export default Footer;