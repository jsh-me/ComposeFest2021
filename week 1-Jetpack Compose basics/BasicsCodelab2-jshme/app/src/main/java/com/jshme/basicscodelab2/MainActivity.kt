package com.jshme.basicscodelab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jshme.basicscodelab2.ui.theme.BasicsCodelab2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val names: List<String> = List(1000) { "$it" }
        setContent {
            BasicsCodelab2Theme {
                MyApp(names)
            }
        }
    }
}

@Composable
private fun MyApp(names: List<String> = List(1000) { "$it" }) {
    //delegate pattern 을 이용해 value 로 직접 접근하는 것을 방지
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

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
    LazyColumn(modifier = Modifier.padding(vertical = 14.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

// 이 어노테이션은 다른 composable function을 부를 수 있음.
@Composable
fun Greeting(name: String) {
    // 뷰가 재생성되어도 expand 상태는 유지해야하기 때문에,
    // rememberSaveable 설정.
    var expanded by rememberSaveable { mutableStateOf(false) }

    val extraPadding by animateDpAsState(
        targetValue = if(expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        
        Row(
            modifier = Modifier.padding(24.dp)
        ) {
            // modifier 해당 요소를 어떻게 보여줄 것인지에 대한 설정
            Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = extraPadding.coerceAtLeast(0.dp))
                ) {
                Text(
                    text = "hello !"
                )
                Text(
                    text = "Hello $name!"
                )
            }

            OutlinedButton(
                onClick = { expanded = !expanded },
                ) {
                Text(text = if(expanded) "show less" else "show more")
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




