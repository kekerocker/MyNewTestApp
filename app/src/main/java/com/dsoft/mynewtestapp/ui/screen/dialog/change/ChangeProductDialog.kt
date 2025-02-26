package com.dsoft.mynewtestapp.ui.screen.dialog.change

import androidx.compose.foundation.layout.Arrangement
import com.dsoft.mynewtestapp.domain.model.Item
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dsoft.mynewtestapp.R

@Composable
fun ChangeCountDialog(
    item: Item,
    onDismissRequest: () -> Unit,
    onConfirmation: (Int) -> Unit,
    dialogTitle: String,
    icon: ImageVector
) {
    var count by rememberSaveable { mutableIntStateOf(item.amount) }

    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        onDismissRequest = { onDismissRequest() },
        text = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { count-- }, enabled = count > 0) {
                    Icon(
                        modifier = Modifier
                            .height(32.dp)
                            .width(32.dp),
                        painter = painterResource(id = R.drawable.ic_remove_circle),
                        tint = colorResource(id = R.color.purple_500),
                        contentDescription = "decrement"
                    )
                }
                Spacer(modifier = Modifier.width(24.dp))
                Text(text = count.toString(), fontSize = 22.sp)
                Spacer(modifier = Modifier.width(24.dp))
                IconButton(onClick = { count++ }) {
                    Icon(
                        modifier = Modifier
                            .height(32.dp)
                            .width(32.dp),
                        painter = painterResource(id = R.drawable.ic_add_circle),
                        tint = colorResource(id = R.color.purple_500),
                        contentDescription = "increment"
                    )
                }

            }
        },
        confirmButton = {
            TextButton(
                onClick = { onConfirmation(count) }
            ) {
                Text(stringResource(id = R.string.accept))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(stringResource(id = R.string.decline))
            }
        }
    )
}