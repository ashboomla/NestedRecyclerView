package com.example.nestedrecyclerview.data

data class MovieItem(
  val id: String,
  val title: String,
  val posterUrl: String,
  val rating: Double,
  val year: Int
)

data class CategoryRow(
  val id: String,
  val title: String,
  val movies: List<MovieItem>,
  var scrollPosition: Int = 0
)

object DataGenerator {

  private val categories = listOf(
    "Trending Now",
    "Action Movies",
    "Romantic Hits",
    "Sci-Fi Specials",
    "Comedy Nights",
    "Horror Zone",
    "Thrillers",
    "Drama Originals",
    "Top Rated",
    "New Releases"
  )

  fun generateCategories(
    categoryCount: Int = 20,
    moviesPerCategory: Int = 50
  ): List<CategoryRow> {

    val result = mutableListOf<CategoryRow>()

    repeat(categoryCount) { catIndex ->
      val movies = mutableListOf<MovieItem>()

      repeat(moviesPerCategory) { movieIndex ->
        val uniqueId = "${catIndex}_${movieIndex}"

        movies.add(
          MovieItem(
            id = uniqueId,
            title = "Movie $uniqueId",
            posterUrl = "https://picsum.photos/300/450?random=${catIndex * 1000 + movieIndex}",
            rating = (5..9).random() + Math.random(),
            year = (1995..2025).random()
          )
        )
      }

      result.add(
        CategoryRow(
          id = "cat_$catIndex",
          title = categories.random(),
          movies = movies
        )
      )
    }

    return result
  }
}