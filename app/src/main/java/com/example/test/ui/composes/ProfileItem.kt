package com.example.test.ui.composes

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.test.data.ProfileEntity

@Composable
fun ProfileItem(
    profile: ProfileEntity,
    onClick: (ProfileEntity) -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
        ,
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Box {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Column(
                    modifier = Modifier.padding(10.dp)
                ){
                    Text(text = profile.name)
//                    Text(text = soname)
//                    Text(text = bday)
                }
                Column(
                    modifier = Modifier.padding(10.dp)
                ){
                    IconButton(
                        onClick = {
                            onClick(profile)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete"
                        )
                    }
                }
            }

        }
    }
}