package io.henrikhorbovyi.hacktoberfestissues.di

import io.henrikhorbovyi.hacktoberfestissues.data.issue.IssueService
import io.henrikhorbovyi.hacktoberfestissues.data.issue.IssuesRepository
import io.henrikhorbovyi.hacktoberfestissues.data.remote.ServiceBuilder
import io.henrikhorbovyi.hacktoberfestissues.data.user.UserRepository
import io.henrikhorbovyi.hacktoberfestissues.data.user.AuthService
import io.henrikhorbovyi.hacktoberfestissues.viewmodel.IssuesViewModel
import io.henrikhorbovyi.hacktoberfestissues.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    viewModel { IssuesViewModel(get()) }
    viewModel { UserViewModel(get()) }

    factory { IssuesRepository(get()) }
    factory { UserRepository(get()) }

    factory { ServiceBuilder<IssueService>("https://api.github.com/") }
}