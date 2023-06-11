package com.example.contacts

import com.example.contacts.Contact
import com.example.contacts.SortType

data class ContactState (
    val contacts: List<Contact> = emptyList(),
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val isFiltering: Boolean = false,
    val isAddingContact: Boolean = false,
    val sortType: SortType = SortType.FIRST_NAME
)