package com.jshme.basicscodelab2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        for (name in names) {
            Greeting(name = name)
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

fun OnboardingScreen() {
    val shouldShowOnboarding by remember { mutableStateOf(true) }

    Su
}
