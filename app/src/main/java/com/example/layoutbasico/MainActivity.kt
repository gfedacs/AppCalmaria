package com.example.layoutbasico


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.layoutbasico.ui.theme.LayoutBasicoTheme


class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       enableEdgeToEdge()
       setContent {
           CalmariaApp()
       }
   }
}


@Composable
fun SearchBar(
   query: String,
   onQueryChange: (String) -> Unit,
   modifier: Modifier = Modifier
) {
   TextField(
       value = query,
       onValueChange = onQueryChange,
       leadingIcon = {
           Icon(imageVector = Icons.Default.Search, contentDescription = null)
       },
       placeholder = {
           Text("Digite para pesquisar")
       },
       colors = TextFieldDefaults.colors(
           focusedIndicatorColor = MaterialTheme.colorScheme.primary,
           unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary
       ),
       modifier = modifier
           .fillMaxWidth()
           .heightIn(min = 56.dp)
   )
}


@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
   LayoutBasicoTheme {


       SearchBar(query = "", onQueryChange = {})
   }
}


@Composable
fun FilteredAlignYourBodyRow(
   query: String,
   modifier: Modifier = Modifier
) {
   val filteredItems = if (query.isBlank()) {
       alignYourBodyData
   } else {
       alignYourBodyData.filter { pair ->
           stringResource(id = pair.text).contains(query, ignoreCase = true)
       }
   }


   LazyRow(
       horizontalArrangement = Arrangement.spacedBy(8.dp),
       contentPadding = PaddingValues(horizontal = 16.dp),
       modifier = modifier
   ) {
       items(filteredItems) { item ->
           AlignYourBodyElement(item.drawable, item.text)
       }
   }
}


@Composable
fun AlignYourBodyElement(
   @DrawableRes imagem: Int,
   @StringRes texto: Int,
   modifier: Modifier = Modifier
) {
   Column(
       horizontalAlignment = Alignment.CenterHorizontally,
       modifier = modifier
   ) {
       Image(
           painter = painterResource(imagem),
           contentDescription = null,
           contentScale = ContentScale.Crop,
           modifier = Modifier
               .size(88.dp)
               .clip(CircleShape)
       )
       Text(
           text = stringResource(texto),
           modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
           style = MaterialTheme.typography.bodyMedium
       )
   }
}


@Preview(showBackground = true)
@Composable
fun AlignYourBodyElementPreview() {
   LayoutBasicoTheme {
       AlignYourBodyElement(
           texto = R.string.ab1_inversions,
           imagem = R.drawable.ab1_inversions,
           modifier = Modifier.padding(8.dp)
       )
   }
}


// Card
@Composable
fun FavoriteCollectionCard(
   @DrawableRes imagem: Int,
   @StringRes texto: Int,
   modifier: Modifier = Modifier
) {
   Surface(
       shape = MaterialTheme.shapes.medium,
       color = MaterialTheme.colorScheme.surfaceVariant,
       modifier = modifier
   ) {
       Row(
           verticalAlignment = Alignment.CenterVertically,
           modifier = Modifier.width(255.dp)
       ) {
           Image(
               painter = painterResource(imagem),
               contentDescription = null,
               contentScale = ContentScale.Crop,
               modifier = Modifier.size(80.dp)
           )
           Text(
               text = stringResource(texto),
               style = MaterialTheme.typography.titleMedium,
               modifier = Modifier.padding(horizontal = 16.dp)
           )
       }
   }
}


@Preview(showBackground = true)
@Composable
fun FavoriteCollectionCardPreview() {
   LayoutBasicoTheme {
       FavoriteCollectionCard(
           texto = R.string.fc2_nature_meditations,
           imagem = R.drawable.fc2_nature_meditations,
           modifier = Modifier.padding(8.dp)
       )
   }
}


// Linha
@Composable
fun AlignYourBodyRow(
   modifier: Modifier = Modifier
) {
   LazyRow(
       horizontalArrangement = Arrangement.spacedBy(8.dp),
       contentPadding = PaddingValues(horizontal = 16.dp),
       modifier = modifier
   ) {
       items(alignYourBodyData) { item ->
           AlignYourBodyElement(item.drawable, item.text)
       }
   }
}


@Preview(showBackground = true)
@Composable
fun AlignYourBodyRowPreview() {
   LayoutBasicoTheme {
       AlignYourBodyRow()
   }
}


// Grade
@Composable
fun FavoriteCollectionsGrid(
   modifier: Modifier = Modifier
) {
   LazyHorizontalGrid(
       rows = GridCells.Fixed(2),
       contentPadding = PaddingValues(horizontal = 16.dp),
       horizontalArrangement = Arrangement.spacedBy(16.dp),
       verticalArrangement = Arrangement.spacedBy(16.dp),
       modifier = modifier.height(168.dp)
   ) {
       items(favoriteCollectionsData) { item ->
           FavoriteCollectionCard(item.drawable, item.text)
       }
   }
}


@Preview(showBackground = true)
@Composable
fun FavoriteCollectionsGridPreview() {
   LayoutBasicoTheme {
       FavoriteCollectionsGrid()
   }
}


// Tela Inicial
@Composable
fun HomeSection(
   @StringRes title: Int,
   modifier: Modifier = Modifier,
   content: @Composable () -> Unit
) {
   Column(modifier) {
       Text(
           text = stringResource(title),
           style = MaterialTheme.typography.titleMedium,
           modifier = Modifier
               .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
               .padding(horizontal = 16.dp)
       )
       content()
   }
}


@Preview(showBackground = true)
@Composable
fun HomeSectionPreview() {
   LayoutBasicoTheme {
       HomeSection(R.string.align_your_body) {
           AlignYourBodyRow()
       }
   }
}


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
   var searchQuery by rememberSaveable { mutableStateOf("") }


   Column(
       modifier.verticalScroll(rememberScrollState())
   ) {
       Spacer(Modifier.height(16.dp))
       SearchBar(
           query = searchQuery,
           onQueryChange = { newQuery -> searchQuery = newQuery }
       )
       HomeSection(title = R.string.align_your_body) {
           FilteredAlignYourBodyRow(query = searchQuery)
       }
       HomeSection(title = R.string.favorite_collections) {
           FavoriteCollectionsGrid()
       }
       Spacer(Modifier.height(16.dp))
   }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
   LayoutBasicoTheme {
       HomeScreen()
   }
}


// Navegação
@Composable
private fun BarraNavegacao(modifier: Modifier = Modifier) {
   NavigationBar(
       containerColor = MaterialTheme.colorScheme.surfaceVariant,
       modifier = modifier
   ) {
       NavigationBarItem(
           icon = {
               Icon(
                   imageVector = Icons.Default.Home,
                   contentDescription = null
               )
           },
           label = { Text(stringResource(R.string.bottom_navigation_home)) },
           selected = true,
           onClick = {}
       )
       NavigationBarItem(
           icon = {
               Icon(
                   imageVector = Icons.Default.AccountCircle,
                   contentDescription = null
               )
           },
           label = { Text(stringResource(R.string.bottom_navigation_profile)) },
           selected = false,
           onClick = {}
       )
   }
}


// Scaffold
@Composable
fun CalmariaApp() {
   LayoutBasicoTheme {
       Scaffold(
           bottomBar = { BarraNavegacao() }
       ) { padding ->
           HomeScreen(Modifier.padding(padding))
       }
   }
}


// Dados de exemplo
private val alignYourBodyData = listOf(
   R.drawable.ab1_inversions to R.string.ab1_inversions,
   R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
   R.drawable.ab3_stretching to R.string.ab3_stretching,
   R.drawable.ab4_tabata to R.string.ab4_tabata,
   R.drawable.ab5_hiit to R.string.ab5_hiit,
   R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }


private val favoriteCollectionsData = listOf(
   R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
   R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
   R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
   R.drawable.fc4_self_massage to R.string.fc4_self_massage,
   R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
   R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }


private data class DrawableStringPair(
   @DrawableRes val drawable: Int,
   @StringRes val text: Int
)

