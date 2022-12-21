package agency.five.codebase.android.movieapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {
    @Query("SELECT * FROM favorite_movies")
    fun favorites(): Flow<List<DbFavoriteMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorites(vararg favoriteMovie: DbFavoriteMovie)

    @Query("DELETE FROM favorite_movies WHERE row_id=:movieId")
    suspend fun deleteFavorites(movieId: Int)
}