package io.henrikhorbovyi.hacktoberfestissues.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.henrikhorbovyi.hacktoberfestissues.ui.components.EmptyStateView
import io.henrikhorbovyi.hacktoberfestissues.ui.components.IssueView
import io.henrikhorbovyi.hacktoberfestissues.ui.entity.issues.Issue
import io.henrikhorbovyi.hacktoberfestissues.viewmodel.IssueViewState
import io.henrikhorbovyi.hacktoberfestissues.viewmodel.IssuesViewModel
import io.henrikhorbovyi.hacktoberfestissues.viewmodel.handle

@Composable
fun IssuesScreen(issuesViewModel: IssuesViewModel, onIssueClicked: (Issue) -> Unit) {

    val issueViewState: IssueViewState by issuesViewModel.issuesFlow.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(true)

    issuesViewModel.fetchIssues()


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { issuesViewModel.fetchIssues() },
            indicator = { state, trigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = trigger,
                    scale = true,
                    contentColor = MaterialTheme.colors.primary,
                )
            }
        ) {
            LazyColumn(
                content = {
                    issueViewState.handle(
                        onLoading = { swipeRefreshState.isRefreshing = true },
                        onSuccess = { response ->
                            swipeRefreshState.isRefreshing = false
                            items(response.issues) {
                                IssueView(issue = it, onClicked = onIssueClicked)
                            }
                        },
                        onFailure = { throwable ->
                            swipeRefreshState.isRefreshing = false
                            item {
                                Column(
                                    modifier = Modifier.fillParentMaxHeight(),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    EmptyStateView(
                                        throwable,
                                        onRetry = { issuesViewModel.fetchIssues() })
                                }
                            }
                        }
                    )
                }
            )
        }


    }
}

@Composable
@Preview(
    name = "IssuesScreenPreview",
    showSystemUi = true,
    showBackground = true
)
fun IssuesScreenPreview() {

}