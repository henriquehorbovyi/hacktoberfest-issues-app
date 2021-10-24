package io.henrikhorbovyi.hacktoberfestissues.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.net.UnknownHostException

@Composable
fun EmptyStateView(throwable: Throwable?, onRetry: () -> Unit) {
    throwable?.let {
        val message = when (it) {
            is UnknownHostException -> "Check your connection and try again!"
            else -> "Something went wrong!"
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = message)
            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = onRetry) {
                Text("Try again!")
            }
        }
    }
}