package com.example.mock1exam.views.app_reusables

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mock1exam.backend.models.CartItem
import com.example.mock1exam.backend.models.Cat
import com.example.mock1exam.backend.models.ShopItem
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.views.reusables.AppImageWithUrl
import com.example.mock1exam.views.reusables.CircularIconButton
import com.example.mock1exam.views.reusables.VerticalSpacer

@Composable
fun ShopItemCard(
    shopItem: ShopItem,
    cartItemCount: Int = 0,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    imageWidth: Dp = 200.dp,
    imageRatio: Float = 1f,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    var internalCount by remember { mutableStateOf(cartItemCount) }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        // item image
        AppImageWithUrl(
            url = shopItem.imageURL,
            aspectRatio = imageRatio,
            width = imageWidth,
            modifier = Modifier.clip(shape = MaterialTheme.shapes.large)
        )

        VerticalSpacer(Dm.marginSmall)

        // name
        Text(
            text = shopItem.name,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        // price
        Text(
            text = "$" + shopItem.price.toString(),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary,
            textAlign = TextAlign.Center
        )

        // inventory count
        Text(
            text = "Amount left: " + shopItem.count.toString(),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary,
            textAlign = TextAlign.Center
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            // on decrement cart item count
            CircularIconButton(
                imageVector = Icons.Filled.KeyboardArrowDown,
                buttonSize = 25.dp
            ) {
                // if current count = 0, no calling to decrement
                if (internalCount > 0) {
                    onDecrement()
                    internalCount--
                }
            }

            // cart item count
            Text(
                text = internalCount.toString()
            )

            // on increment cart item count
            CircularIconButton(
                imageVector = Icons.Filled.KeyboardArrowUp,
                buttonSize = 25.dp
            ) {
                // only allow to increment if inventory greater than demand
                if (shopItem.count > internalCount) {
                    onIncrement()
                    internalCount++
                }
            }
        }
    }
}