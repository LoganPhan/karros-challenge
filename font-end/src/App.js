import React from 'react';
import './App.scss';
import Header from './components/header/Header';
import Content from './components/content/Content';
import Footer from './components/footer/Footer';
import Container from 'react-bootstrap/Container';

class App extends React.Component {

  constructor(props) {
    super(props)
     this.state={
      isLoading: true
     }
    this.onScroll = this.onScroll.bind(this);
    this.childRef = React.createRef();
  }
   
  onScroll(ele) {
    if (this.isBottom(ele)) {
      this.childRef.current.loadData();
    }
  }


  isBottom(ele) {
    ele.preventDefault();
    ele.stopPropagation();
    let afterCalc = Math.floor(ele.currentTarget.scrollHeight - ele.currentTarget.scrollTop);
    return afterCalc <= ele.currentTarget.clientHeight;
  }

  render() {
    return (
      <Container className="main-container" onScroll={this.onScroll}>
        <Header></Header>
        <Content ref={this.childRef}></Content>
        <Footer></Footer>
      </Container>

    );
  }
}

export default App;
