package agency.five.codebase.android.movieapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class DbFavoriteMovie(
    @PrimaryKey @ColumnInfo(name = "row_id") val id: Int,
    @ColumnInfo(name = "poster_Url") val posterUrl: String
)
