package com.dsoft.mynewtestapp.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dsoft.mynewtestapp.R
import com.dsoft.mynewtestapp.domain.model.Item
import com.dsoft.mynewtestapp.ui.screen.dialog.change.ChangeCountDialog
import com.dsoft.mynewtestapp.ui.screen.dialog.delete.DeleteProductDialog
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ItemCard(
    modifier: Modifier,
    item: Item,
    onDeleteEvent: (Item) -> Unit,
    onCountChangeEvent: (Item, Int) -> Unit
) {
    var openDeleteDialog by remember {
        mutableStateOf(false)
    }

    var openChangeDialog by remember {
        mutableStateOf(false)
    }

    if (openDeleteDialog) {
        DeleteProductDialog(
            onDismissRequest = { openDeleteDialog = false },
            onConfirmation = {
                openDeleteDialog = false
                onDeleteEvent(item)
            },
            dialogTitle = stringResource(id = R.string.delete_title),
            dialogText = stringResource(id = R.string.delete_text),
            icon = ImageVector.vectorResource(id = R.drawable.ic_warning)
        )
    }

    if (openChangeDialog) {
        ChangeCountDialog(
            onDismissRequest = { openChangeDialog = false },
            onConfirmation = { count ->
                openChangeDialog = false
                onCountChangeEvent(item, count)
            },
            item = item,
            dialogTitle = stringResource(id = R.string.change_count_title),
            icon = ImageVector.vectorResource(id = R.drawable.ic_settings)
        )
    }

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            Row {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp, top = 8.dp),
                    text = item.name,
                    fontWeight = FontWeight.W500,
                    fontSize = 22.sp
                )
                IconButton(
                    modifier = Modifier,
                    onClick = { openChangeDialog = true },
                    content = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_pen),
                            tint = colorResource(id = R.color.purple_500),
                            contentDescription = "count change"
                        )
                    }
                )
                IconButton(
                    modifier = Modifier,
                    onClick = { openDeleteDialog = true },
                    content = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bucket),
                            tint = colorResource(id = R.color.orange),
                            contentDescription = "delete item"
                        )
                    }
                )
            }
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)

            ) {
                item.tags.forEach { tag ->
                    OutlinedText(text = tag)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(
                    Modifier.weight(0.5f),
                    verticalArrangement = Arrangement.spacedBy((-5).dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.in_warehouse),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W500,
                    )
                    Text(
                        text = item.amount.toString(),
                        fontSize = 14.sp
                    )
                }
                Column(Modifier.weight(0.5f), verticalArrangement = Arrangement.spacedBy((-5).dp)) {
                    Text(
                        text = stringResource(id = R.string.date),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W500,
                    )
                    Text(
                        text = item.date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                        fontSize = 14.sp
                    )
                }
            }
        }

    }
}