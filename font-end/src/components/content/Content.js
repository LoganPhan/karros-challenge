import React, { useState, useEffect } from "react";
import "./Content.scss";
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import Nav from 'react-bootstrap/Nav';
import Card from 'react-bootstrap/Card';
import Container from 'react-bootstrap/Container';
import TheMovieAPI from '../../api/TheMovieApi';
import Constant from '../../api/Constant';

const { forwardRef, useImperativeHandle } = React;
const Content = forwardRef((props, ref) => {

    const [key, setKey] = useState('popular');
    const [cards, setCards] = useState([]);
    const [movieAPI] = useState(TheMovieAPI);
    const [genreMovie, setGenreMovie] = useState();
    const [genreTv, setGenreTv] = useState();
    const [isLoading, setIsLoading] = useState(props.isLoading);
    const [page, setPage] = useState(0);
    const [prev, setPrev] = useState('popular')

    const keyApis = new Map(
        [['popular', Constant.POPULAR_API],
        ['topRated', Constant.TOP_RATE_API],
        ['upcoming', Constant.UPCOMING_API],
        ['movie', Constant.GENRE_MOVIE_LIST_API],
        ['TV', Constant.GENRE_TV_LIST_API]],
    )
    
    useEffect(() => {
        if(!genreMovie){
            setIsLoading(true)
            getGenresMovie();
            getGenesTv()
        }
    },[])

    useEffect(() => {
        if(genreMovie){
            getMoviesByKey(keyApis.get(key), page);
        }
    }, [genreMovie, key])

  useImperativeHandle(ref, (e) => ({
    
    loadData(){
        if(page !== 0 && key === prev){
            getMoviesByKey(keyApis.get(key));
        }
    }
  }));

    const getMoviesByKey = async (apiUrl) => {
        setIsLoading(true)
        const res = await movieAPI.get(apiUrl, {params: {'page': page + 1},  timeout: 2000 });
        const data = bindGenres(res.data.results, genreMovie);
        setCards([...cards, ...data]);
        setIsLoading(false);
        setPage(res.data.page)
        setPrev(key)
    }

    const getGenresMovie = async () => {
        const res = await movieAPI.get(Constant.GENRE_MOVIE_LIST_API, { timeout: 2000 });
        setGenreMovie(new Map(res.data.genres.map(i => [i.id, i.name])));
    }

    const getGenesTv = async () => {
        const res = await movieAPI.get(Constant.GENRE_TV_LIST_API, { timeout: 2000 });
        setGenreTv(new Map(res.data.genres.map(i => [i.id, i.name])));
    }

    const bindGenres = (res, genres) => {
        return  res.map(data => {
            data['genres'] = data.genre_ids.map(gen => genres.get(gen)).join(", ");
            return data;
        })
    }
    const selectKey = (k) => {
        setKey(k);
        setPrev(key)
        setPage(0);
        setCards([]);
    }
    return (
        <Container className="body">
            <Navbar onSelect={(k) => selectKey(k)}>
                <Nav className="tabs">
                    <Nav eventKey="popular" className={key === 'popular' ? 'active' : ''}>Popular</Nav>
                    <Nav eventKey="topRated" className={key === 'topRated' ? 'active' : ''}>Top Rated</Nav>
                    <Nav eventKey="upcoming" className={key === 'upcoming' ? 'active' : ''}>Upcoming</Nav>
                    <NavDropdown className={key === 'movie' || key === 'TV' ? 'active' : ''} title="Genre" id="basic-nav-dropdown">
                        <NavDropdown.Item eventKey="movie">Movie List</NavDropdown.Item>
                        {genreMovie && Array.from(genreMovie.entries()).map((key, val) => {
                            return <NavDropdown.Item key={"move_" + val}>{key[1]}</NavDropdown.Item>
                        })}
                        <NavDropdown.Item eventKey="TV">TV List</NavDropdown.Item>
                        {genreTv && Array.from(genreTv.entries()).map((key, val) => {
                            return <NavDropdown.Item key={"tv_" + val}>{key[1]}</NavDropdown.Item>
                        })}
                    </NavDropdown>
                </Nav>
                <Nav>
                    <i className="collection-icon"></i>
                    <i className="list-icon"></i>
                </Nav>
            </Navbar>
            <Navbar className="content" key="content">
                {cards && cards.map((card, idx) => {
                    return (
                        <Card key={card.id + idx}>
                            <div className="thumnail">
                                <img alt={card.title} src={Constant.IMAGE_DOMAIN_URL + Constant.IMAGE_SIZE_ORIGINAL + card.poster_path}></img>
                                <span>{card.release_date.split('-')[0]}</span>
                            </div>
                            <div className="detail">
                                <span className="group-title">
                                    <span className="title">{card.title}</span>
                                    <span className="genres">{card.genres}</span>
                                </span>
                                <span className="vote">{(Math.round(card.vote_average * 100) / 100).toFixed(1)}</span>
                            </div>
                        </Card>
                    )
                })
                }
            </Navbar>
            {isLoading && <div className="loading"><span className="loading-icon"></span><span>LOADING</span></div>}
        </Container>
    )
})
export default Content;