# TVPulse

## Endpoints

- /shows
- /search/shows?q={query}
- /shows/{id}

## Pages

- Home
    - Home
        - Show only 30 movies
        - filter item on repo
    - Favorite
        - collected on local DB
        - able to remove item

- Detail Page
    - show movie detail
    - destination for deeplink
    - favorite add

## Page | Component Detail

- Home : Tab(Home, Favorite)
    - Home
        - search
        - movie list : Vertical | Grid(2)
            - poster
            - title
            - rate
            - shimmer
        - empty state
    - Favorite
        - movie list : Horizontal
            - poster
            - title
            - genre
            - shimmer
        - empty state
- Movie Detail
    - title
    - backdrop
    - tags(genres, duration, status)
    - favorite button
    - sinopsis
- Popup
    - Title
    - message
    - dismiss
    - action(retry)