package com.example.contacts.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.contacts.ContactEvent
import com.example.contacts.ContactState
import com.example.contacts.R
import com.example.contacts.SortType.*
import com.example.contacts.SortType
import com.example.contacts.ui.theme.Purple80


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterDialog(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit,
    modifier: Modifier = Modifier
        .background(color = Purple80)
        .border(16.dp, Color.White)
    ) {
   Dialog(onDismissRequest = { onEvent(ContactEvent.HideFilterDialog) }) {
       Column(
           horizontalAlignment = Alignment.Start,
           modifier = Modifier
               .background(Color(red = 255, blue = 0, green = 191))
               .width(150.dp)
           ) {
           SortType.values().forEach{ SortType->
               Row(modifier = Modifier.clickable {
                   onEvent(ContactEvent.SortContacts(SortType))
               },
               verticalAlignment = CenterVertically
                   ) {
                   RadioButton(
                       selected = state.sortType == SortType,
                       onClick = {
                           onEvent(ContactEvent.SortContacts(SortType))
                       })
                   Text(text = when(SortType) {
                       FIRST_NAME -> stringResource(R.string.first_name)
                       LAST_NAME -> stringResource(R.string.last_name)
                       PHONE_NUMBER -> stringResource(R.string.phone_number)
                   })
               }
           }
       }

   }
}
@Preview()
@Composable
fun Test() {
    FilterDialog(state = ContactState(), onEvent = {})
}