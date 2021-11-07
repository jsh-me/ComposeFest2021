package com.jshme.basicscodelab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jshme.basicscodelab2.ui.theme.BasicsCodelab2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelab2Theme {
                MyApp()
            }
        }
    }
}

@Composable
private fun MyApp(names: List<String> = listOf("World", "Compose")) {
    //delegate pattern 을 이용해 value 로 직접 접근하는 것을 방지
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    if(shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = {
            shouldShowOnboarding = false
        })
    } else {
        Greetings(names)
    }
}

@Composable
fun Greetings(names: List<String>) {
    Column(modifier = Modifier.padding(vertical = 14.dp)) {
        for(name in names) {
            Greeting(name)
        }
    }
}

// 이 어노테이션은 다른 composable function을 부를 수 있음.
@Composable
fun Greeting(name: String) {
    val expanded = remember { mutableStateOf(false) }

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        
        Row(
            modifier = Modifier.padding(24.dp)
        ) {
            // modifier 해당 요소를 어떻게 보여줄 것인지에 대한 설정
            Column(
                    modifier = Modifier.weight(1f)
                ) {
                Text(
                    text = "hello !"
                )
                Text(
                    text = "Hello $name!"
                )
            }

            OutlinedButton(
                onClick = { expanded.value = !expanded.value },
                ) {
                Text(text = if(expanded.value) "show less" else "show more")
            }

        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    BasicsCodelab2Theme {
        MyApp()
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
//    //delegate pattern 을 이용해 value 로 직접 접근하는 것을 방지
//   var shouldShowOnboarding by remember { mutableStateOf(true) }

    Surface {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = " Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = {
                    onContinueClicked()
                }
            ) {
                Text("Continue!")
            }
        }
    }
}

// preview 는 동시에 띄울 수 있음.
@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    BasicsCodelab2Theme {
        OnboardingScreen(onContinueClicked = {})
    }
}




