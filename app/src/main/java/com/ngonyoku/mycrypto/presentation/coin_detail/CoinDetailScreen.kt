package com.ngonyoku.mycrypto.presentation.coin_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowRow
import com.ngonyoku.mycrypto.presentation.Screen
import com.ngonyoku.mycrypto.presentation.coin_detail.components.CoinTag
import com.ngonyoku.mycrypto.presentation.coin_detail.components.TeamListItem
import com.ngonyoku.mycrypto.presentation.coin_list.components.CoinListItem

/**
 * The screen that will display details about the Coin
 * */
@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        state.coin?.let { coin ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item { //This will automatically be treated as a row
                    //The Title Bar
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                            style = MaterialTheme.typography.h2,
                            modifier = Modifier.weight(8f) //Wont overlapp the active texts
                        )
                        Text(
                            text = if (coin.isActive) "active" else "inactive",
                            color = if (coin.isActive) Color.Green else Color.Red,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier
                                .align(CenterVertically)
                                .weight(2f) //Space between the above text and the description
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    //Description
                    Text(text = coin.description, style = MaterialTheme.typography.body2)
                    Spacer(modifier = Modifier.height(15.dp))
                    //Tags Title
                    Text(text = "tags", style = MaterialTheme.typography.h3)
                    Spacer(modifier = Modifier.height(15.dp))
                    //Tags
                    FlowRow( //The flow row will shift items to the next line if they exceed screen bounds
                        mainAxisSpacing = 10.dp, //Space between items on main axis
                        crossAxisSpacing = 10.dp, //Space between items on cross axis
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        coin.tags.forEach { tag ->
                            CoinTag(tag = tag)
                        }
                    }
                    //Members Title
                    Text(text = "Team Members", style = MaterialTheme.typography.h3)
                    Spacer(modifier = Modifier.height(15.dp))
                }

                items(coin.team) { teamMember ->
                    TeamListItem(
                        teamMember = teamMember,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                    Divider()
                }
            }
        }

        if (state.error.isNotBlank()) {
            //If an error exists...then display the following
            //Error message
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            ) //Show Progress Bar at the center of the screen
        }
    }
}