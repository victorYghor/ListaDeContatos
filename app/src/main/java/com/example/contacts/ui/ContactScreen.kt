package com.example.contacts.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contacts.ContactEvent
import com.example.contacts.ContactState
import com.example.contacts.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(ContactEvent.ShowDialog)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(R.string.add_contact))
            }
        },
        modifier = Modifier.padding(16.dp)
    ) {
        if (state.isAddingContact) {
            AddContactDialog(state = state, onEvent = onEvent)
        }

        if (state.isFiltering) {
            FilterDialog(state = state, onEvent = onEvent)
        }
        LazyColumn(
            contentPadding = it,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)

                ) {
                    Icon(
                        painterResource(
                            id = R.drawable.baseline_filter_alt_24
                        ),
                        contentDescription = stringResource(id = R.string.filter_icon),
                        modifier = Modifier.clickable {
                            onEvent(ContactEvent.ShowFilterDialog)
                        }
                    )
                    Text(text = "The contacts are sort by ${state.sortType}")
                }
            }
            items(state.contacts){contact ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "${contact.firstName} ${contact.lastName}",
                            fontSize = 20.sp
                        )
                        Text(text = contact.phoneNumber, fontSize = 12.sp)
                    }
                    IconButton(onClick = {
                        onEvent(ContactEvent.DeleteContact(contact))
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = stringResource(R.string.delete_contact)
                        )
                    }
                }
            }

        }
    }
}