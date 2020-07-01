
const Constant = {
    POPULAR_API: `${process.env.REACT_APP_MOVIE_SERVICE}/popular`,
    TOP_RATE_API: `${process.env.REACT_APP_MOVIE_SERVICE}/top_rated`,
    UPCOMING_API: `${process.env.REACT_APP_MOVIE_SERVICE}/upcoming`,
    GENRE_MOVIE_LIST_API: `/genre/movie/list`,
    GENRE_TV_LIST_API: `/genre/tv/list`,
    IMAGE_DOMAIN_URL: `${process.env.REACT_APP_IMAGE_DOMAIN_URL}`,
    IMAGE_SIZE_ORIGINAL: `/original`,
    IMAGE_SIZE_W200: `/w200`,
    IMAGE_SIZE_W300: `/w300`,
    IMAGE_SIZE_W500: `/w500`
}

export default Constant;