package com.example.test.presentation.ui.composes

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.test.presentation.model.ProfileUI

@Composable
fun ProfileItem(
    profile: ProfileUI,
    onDeleteClick: (ProfileUI) -> Unit,
    isDeleteEnabled: Boolean,
    isHighlighted: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(
                width = if (isHighlighted) 2.dp else 0.dp,
                color = if (isHighlighted) MaterialTheme.colorScheme.primary else Color.Transparent,
                shape = RoundedCornerShape(15.dp)
            ),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Box {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(text = profile.userName)
                    Text(text = "др: " + profile.userBDay)
                }
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    IconButton(
                        onClick = {
                            if (isDeleteEnabled) {
                                onDeleteClick(profile)
                            }
                        },
                        enabled = isDeleteEnabled
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Удалить"
                        )
                    }
                }
            }
        }
    }
}