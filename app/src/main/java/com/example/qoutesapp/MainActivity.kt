package com.example.qoutesapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.qoutesapp.Layouts.PreviewFunction
import com.example.qoutesapp.Layouts.QouteDetails
import com.example.qoutesapp.ui.theme.QoutesAppTheme
import dagger.multibindings.IntoMap
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            QoutesAppTheme {
                LazyVerticalStaggeredGrid(columns  = StaggeredGridCells.Fixed(2),
                    Modifier.background(Color.White.copy(alpha = .3f)).padding(8.dp).fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalItemSpacing =8.dp){
                    items(list){item ->
                        sections(text = item)
                    }
                }
            }
        }
    }
}


@Composable
fun sections(text:String) {
    val context = LocalContext.current
    Box(modifier = Modifier
        .clickable {
            val intent = Intent(context, QuotesActivity::class.java)
            intent.putExtra("Data", text)
            context.startActivity(intent)
        }
        .height(180.dp)
        .background(Color.Gray.copy(alpha = .3f)),
        contentAlignment = Alignment.Center){
        Image(
            painter = painterResource(id = R.drawable.kotlinlenvelop),
            contentDescription = "coding Quotes",
                    modifier = Modifier
                        .fillMaxHeight(1f),
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(Color.Gray)
            )

        Text(text = text, modifier = Modifier
            .align(Alignment.TopCenter).padding(0.dp, 10.dp),
            color = Color.Black.copy(alpha = 1f))
        Text(text = "Quotes", modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(0.dp, 30.dp),
            color = Color.Black.copy(alpha = .5f)
            )
    }
}
 var list = mutableListOf<String>(
     "Coding",
     "Motivation",
     "Funny",
     "Nature",
     "Imagination",
     "Relationship",
     "Friendship",
     "Positive",
     "Success",
     "Health",
     "Horror",
     "Leadership",
     "Islamic"
 )



