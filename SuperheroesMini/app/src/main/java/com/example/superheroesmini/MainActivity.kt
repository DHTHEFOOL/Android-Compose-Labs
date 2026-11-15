package com.example.superheroesmini

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroesmini.ui.theme.SuperheroesMiniTheme

data class Hero(
    val name: String,
    val power: String,
    val description: String,
    @DrawableRes val image: Int? = null
)

val heroes = listOf(
    Hero("Iron Man", "Bộ giáp công nghệ", "Thiên tài, tỷ phú, nhà phát minh bộ giáp Mark.", image = R.drawable.android_superhero1),
    Hero("Thor", "Điều khiển sấm sét", "Thần sấm của Asgard, sử dụng búa Mjolnir.", image = R.drawable.android_superhero2),
    Hero("Black Widow", "Điệp viên tinh nhuệ", "Chuyên gia chiến đấu và tình báo.", image = R.drawable.android_superhero3)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesMiniTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    HeroesApp()
                }
            }
        }
    }
}

// Phan 2: Scaffold + TopAppBar, LazyColumn ap dung innerPadding, cac the cach nhau 8dp
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroesApp() {
    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.app_title)) }) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(heroes) { hero -> HeroCard(hero) }
        }
    }
}

@Composable
fun HeroCard(hero: Hero, modifier: Modifier = Modifier) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var favorite by rememberSaveable { mutableStateOf(false) }

    // Phan 4: animateContentSize de the co gian muot
    Card(modifier = modifier.fillMaxWidth().animateContentSize()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Phan 1 + 3: 56dp, shapes.small, primaryContainer; thu tu size -> clip -> background
                // Phan 5: hinh trang tri nen TalkBack bo qua
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(MaterialTheme.shapes.small)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .clearAndSetSemantics { }
                ) {
                    hero.image?.let {
                        Image(
                            painter = painterResource(it),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = hero.name, style = MaterialTheme.typography.titleMedium)
                    Text(text = hero.power, style = MaterialTheme.typography.bodyMedium)
                }
                // IconButton tu dam bao vung cham toi thieu 48dp
                IconButton(onClick = { favorite = !favorite }) {
                    Icon(
                        imageVector = if (favorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = stringResource(
                            if (favorite) R.string.remove_favorite else R.string.add_favorite,
                            hero.name
                        ),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            // Nhan nut doi theo trang thai
            TextButton(onClick = { expanded = !expanded }) {
                Text(stringResource(if (expanded) R.string.collapse else R.string.show_more))
            }
            // Phan 4: AnimatedVisibility cho phan mo ta chi tiet
            AnimatedVisibility(visible = expanded) {
                Text(text = hero.description, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeroesAppPreview() {
    SuperheroesMiniTheme(darkTheme = false) { HeroesApp() }
}

@Preview(showBackground = true)
@Composable
fun HeroesAppDarkPreview() {
    SuperheroesMiniTheme(darkTheme = true) { HeroesApp() }
}