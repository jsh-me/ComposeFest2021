package com.jshme.basicscodelab2

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jshme.basicscodelab2.ui.theme.BasicsCodelab2Theme
import kotlin.math.exp

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

    if (shouldShowOnboarding) {
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
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}

@Composable
private fun CardContent(name: String) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioHighBouncy,
                    stiffness = Spring.StiffnessHigh
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(
                text = "hello !"
            )
            Text(
                text = "$name!",
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )

            if (expanded) {
                Text(text = "이야아아아....!!!!".repeat(10))
            }
        }

        IconButton(
            onClick = { expanded = !expanded },
        ) {
            Icon(imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded)
                    stringResource(id = R.string.show_less)
                else
                    stringResource(id = R.string.show_more)
            )
        }
    }
}

@Preview(showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
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




