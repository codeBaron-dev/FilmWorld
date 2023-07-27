package com.codebaron.filmworld.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codebaron.sharedlogc.R
import com.codebaron.sharedlogc.utils.AppConstants.HOT_NEW
import com.codebaron.sharedlogc.utils.AppConstants.MOVIES
import com.codebaron.sharedlogc.utils.AppConstants.SEARCH
import com.codebaron.sharedlogc.utils.AppConstants.TV_SHOWS

@Preview(showBackground = true)
@Composable
fun AccountEditHeader() {
    Column {
        Box(
            modifier = Modifier
                .height(30.dp)
                .fillMaxWidth()
                .background(color = Color.Black),
            contentAlignment = Alignment.TopCenter
        ) { }

        Box(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .background(color = Color.Black),
            contentAlignment = Alignment.TopCenter
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(150.dp),
                    painter = painterResource(id = R.drawable.net),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderBar() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        Box(
            modifier = Modifier
                .height(140.dp)
                .fillMaxWidth()
                .alpha(0.5f)
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(Color.Black, Color.Transparent),
                        startY = size.height / 19,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.Multiply)
                    }
                },
            contentAlignment = Alignment.TopCenter
        ) {}
        Column {
            Spacer(modifier = Modifier.size(35.dp))
            Row {
                Box(
                    contentAlignment = Alignment.TopStart
                ) {
                    Image(
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp)
                            .padding(5.dp),
                        painter = painterResource(id = R.drawable.netflixicon),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(7.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = SEARCH,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.size(15.dp))
                    Image(
                        modifier = Modifier
                            .height(35.dp)
                            .width(40.dp)
                            .clip(RoundedCornerShape(5.dp)),
                        painter = painterResource(id = R.drawable.anime),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = TV_SHOWS,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Justify,
                    maxLines = 1,
                    color = Color.White,
                    modifier = Modifier.padding(3.dp)
                )
                Text(
                    text = MOVIES,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Justify,
                    maxLines = 1,
                    color = Color.White,
                    modifier = Modifier.padding(3.dp)
                )
                Text(
                    text = HOT_NEW,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Justify,
                    maxLines = 1,
                    color = Color.White,
                    modifier = Modifier.padding(3.dp)
                )
            }
        }
    }
}