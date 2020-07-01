import React, { useState } from "react";
import "./Header.scss";
import Carousel from 'react-bootstrap/Carousel';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
import Button from "react-bootstrap/Button";
const Header = (props) => {

    const [banners] = useState(['assets/images/Banner.png', 'assets/images/Banner.png', 'assets/images/Banner.png'])

    return (
        <header>
            <Navbar className="header-top">
                <Nav className="title">
                    <span>THEMOVIEBOX</span>
                </Nav>
                <Nav className="group-button-login">
                    <i className="search-icon"></i>
                    <Button className="login-btn">LOG IN</Button>
                    <Button className="sign-up-btn">SIGN UP</Button>
                </Nav>
            </Navbar>
            <Carousel>
                {banners.map((ban, idx) => {
                    return (
                        <Carousel.Item key={idx}>
                            <img className="d-block w-100" src={ban} alt="Top Trending"/>
                        </Carousel.Item>
                    )
                })}
            </Carousel>
            <Navbar className="header-bottom">
                <Nav className="bottom-left">
                    <h3 className="">WRATH OF THE TITANS</h3>
                    <Nav className="genres padding-bottom-2em">
                        <span>Fantasy</span>
                        <span>Animation</span>
                        <span>Family</span>
                        <span>|</span>
                        <span>Duration: 1h52m</span>
                    </Nav>
                    <Nav className="padding-bottom-2em">
                        <Button className="watch-btn">WATCH MOVIE</Button>
                        <Button className="view-btn">VIEW INFO</Button>
                        <span>+ ADD TO WISHLIS</span>
                    </Nav>
                </Nav>
                <Nav className="bottom-right">
                    <section>
                        <label><b>Rating</b><span> based on 3.546 views</span></label>
                        <div className="group-stars">
                            <i className="start-icon"></i>
                            <i className="start-icon"></i>
                            <i className="start-icon"></i>
                            <i className="start-half-icon"></i>
                            <i className="start-less-icon"></i>
                            <span className="rating">3.4</span>
                        </div>
                    </section>
                </Nav>
            </Navbar>
        </header>
    );
}

export default Header;