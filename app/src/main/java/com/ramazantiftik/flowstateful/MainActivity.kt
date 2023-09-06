package com.ramazantiftik.flowstateful

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ramazantiftik.flowstateful.ui.theme.FlowStatefulTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowStatefulTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController= rememberNavController()
                    NavHost(navController = navController, startDestination = "firstScreen" ){

                        composable(route = "firstScreen"){

                            val viewModel=viewModel<FirstScreenVM>()

                            val time by viewModel.counter.collectAsStateWithLifecycle() //lifeCycle aware

                            FirstScreen(time = time, onButtonClicked = {
                                navController.navigate("secondScreen")
                            })

                        }

                        composable(route = "secondScreen"){
                            SecondScreen()
                        }

                    }

                }

            }
        }
    }
}

