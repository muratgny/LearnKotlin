package com.example.learnkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learnkotlin.ui.theme.LearnKotlinTheme

class GridExampleApp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnKotlinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopicGrid(
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                    )
                }
            }
        }
    }
}



@Composable
fun TopicGrid(modifier: Modifier = Modifier) {
    //The LazyVerticalGrid and LazyHorizontalGrid composables provide support for displaying
    // items in a grid. A Lazy vertical grid will display its items in a vertically
    // scrollable container, spanned across multiple columns, while the Lazy horizontal
    // grids will have the same behaviour on the horizontal axis.
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        modifier = modifier
    ) {
        items(DataSource.topics) { topic ->
            TopicCard(topic)
        }
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card {
        Row {
            Box {
                Image(
                    painter = painterResource(id = topic.imageRes),
                    contentDescription = null,
                    modifier = modifier
                        .size(width = 68.dp, height = 68.dp)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }

            Column {
                Text(
                    text = stringResource(id = topic.name),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_small)
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(androidx.core.R.drawable.ic_call_answer),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = dimensionResource(R.dimen.padding_medium))
                    )
                    Text(
                        text = topic.availableCourses.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopicPreview() {
    LearnKotlinTheme {
        val topic = Topic(R.string.affirmation2, 321, R.drawable.image1)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopicCard(topic = topic)
        }
    }
}

object DataSource {
    val topics = listOf(
        Topic(R.string.affirmation1,52, R.drawable.image1),
        Topic(R.string.affirmation2, 52,R.drawable.image2),
        Topic(R.string.affirmation3, 52,R.drawable.image3),
        Topic(R.string.affirmation4, 52,R.drawable.image4),
        Topic(R.string.affirmation5, 52,R.drawable.image5),
        Topic(R.string.affirmation6, 52,R.drawable.image6),
        Topic(R.string.affirmation7, 52,R.drawable.image7),
        Topic(R.string.affirmation8, 52,R.drawable.image8),
        Topic(R.string.affirmation9, 52,R.drawable.image9),
        Topic(R.string.affirmation10, 52,R.drawable.image10),
        Topic(R.string.affirmation1,52, R.drawable.image1),
        Topic(R.string.affirmation2, 52,R.drawable.image2),
        Topic(R.string.affirmation3, 52,R.drawable.image3),
        Topic(R.string.affirmation4, 52,R.drawable.image4),
        Topic(R.string.affirmation5, 52,R.drawable.image5),
        Topic(R.string.affirmation6, 52,R.drawable.image6),
        Topic(R.string.affirmation7, 52,R.drawable.image7),
        Topic(R.string.affirmation8, 52,R.drawable.image8),
        Topic(R.string.affirmation9, 52,R.drawable.image9),
        Topic(R.string.affirmation10, 52,R.drawable.image10)
    )
}

data class Topic(
    @StringRes val name: Int,
    val availableCourses: Int,
    @DrawableRes val imageRes: Int
)