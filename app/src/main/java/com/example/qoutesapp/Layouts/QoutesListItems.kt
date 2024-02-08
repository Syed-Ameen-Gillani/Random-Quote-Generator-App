package com.example.qoutesapp.Layouts

import  androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.qoutesapp.ui.theme.QoutesAppTheme

@Composable
fun PreviewFunction() {
    Column (
        Modifier
            .verticalScroll(rememberScrollState())
    ){
        QoutesListItem()
        QoutesListItem()
        QoutesListItem()
        QoutesListItem()
        QoutesListItem()
    }
}
@Composable
fun QoutesListItem() {
    Card(elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .padding(8.dp)
        )
    {
        Row(Modifier.padding(16.dp,16.dp,8.dp,16.dp)) {
            Image(imageVector = Icons.Filled.Create,
                colorFilter = ColorFilter.tint(Color.White),
                contentDescription = "quoteIcon",
                modifier = Modifier
                    .background(Color.Black)
                    .padding(4.dp))
            Spacer(modifier = Modifier.padding(4.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Time is money that  you spend on your self and others",
                    Modifier.padding(0.dp,0.dp,0.dp,8.dp), style = MaterialTheme.typography.bodyLarge
                )
                Box(
                    Modifier
                        .background(Color.Gray)
                        .fillMaxWidth(.4f)
                        .height(1.dp)
                )
                {
                }
                Text(text = "williams words worth",
                    Modifier.padding(0.dp,4.dp,0.dp,0.dp),style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Light
                )

            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun QouteDetails() {
    var theme = rememberSaveable{ mutableStateOf(false) }
    QoutesAppTheme(theme.value){
        Box(
            Modifier
                .fillMaxSize(1f)
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {

            Card(elevation = CardDefaults.cardElevation(4.dp),
                modifier = Modifier.padding(50.dp))
            {
                Column(Modifier.padding(20.dp)) {
                    Image(imageVector = Icons.Filled.Create,
                        colorFilter = ColorFilter.tint(Color.White),
                        contentDescription = "quoteIcon",
                        modifier = Modifier
                            .background(Color.Black)
                            .padding(4.dp))
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = "Time is money that you spend on yourself",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Box(
                        Modifier
                            .background(Color.Gray)
                            .fillMaxWidth(.4f)
                            .height(1.dp)
                    )
                    Text(text = "Syed Ameen",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Light)

                }
            }
        }
        Button(onClick = { theme.value = !theme.value},
            Modifier.padding(80.dp,5.dp,0.dp,20.dp).background(MaterialTheme.colorScheme.background)
        ) {
            Text(text = "switch Theme")
        }
    }

}

