package com.dicoding.mytourcompose.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.dicoding.mytourcompose.model.Tour
import com.dicoding.mytourcompose.model.TourData
import com.dicoding.mytourcompose.ui.navigation.Screen
import com.dicoding.mytourcompose.ui.theme.MyTourComposeTheme

@Composable
fun HomeScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        LazyColumn {
            items(TourData.tourList, key = { it.tourId }) { tour ->
                TourListItem(
                    tourName = tour.tourName,
                    alamat = tour.alamat,
                    image = tour.image,
                    deskripsi = tour.deskripsi,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { navController.navigate(Screen.DetailTour.createRoute(tour.tourId)) }
                )
            }
        }
    }
}

@Composable
fun TourListItem(
    tourName: String,
    alamat: String,
    deskripsi: String,
    image: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.clickable { onClick() }
        ) {
            val painter = rememberImagePainter(image)
            androidx.compose.foundation.Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = tourName,
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = alamat,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MyTourComposeTheme {
        val navController = rememberNavController()
        HomeScreen(navController = navController)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun TourAppPreview() {
//    MyTourComposeTheme {
//        HomeScreen()
//    }
//}