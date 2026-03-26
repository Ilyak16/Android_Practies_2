package ci.nsu.moble.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ci.nsu.moble.main.ui.theme.PracticeTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ColorPickerScreen()
                }
            }
        }
    }
}

@Composable
fun ColorPickerScreen(
    viewModel: ColorPickerViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // 🔷 Предпросмотр цвета
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(
                    color = uiState.color,
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = 2.dp,
                    color = Color.DarkGray,
                    shape = RoundedCornerShape(12.dp)
                )
        )

        // 🔤 HEX-код цвета
        Text(
            text = uiState.hexCode.uppercase(),
            style = MaterialTheme.typography.titleMedium,
            fontFamily = FontFamily.Monospace
        )

        // 🎚️ Слайдер Red
        SliderWithLabel(
            label = "Red",
            value = uiState.red.toFloat(),
            onValueChange = viewModel::onRedChanged,
            valueRange = 0f..255f,
            thumbColor = Color(255, 0, 0)
        )

        // 🎚️ Слайдер Green
        SliderWithLabel(
            label = "Green",
            value = uiState.green.toFloat(),
            onValueChange = viewModel::onGreenChanged,
            valueRange = 0f..255f,
            thumbColor = Color(0, 255, 0)
        )

        // 🎚️ Слайдер Blue
        SliderWithLabel(
            label = "Blue",
            value = uiState.blue.toFloat(),
            onValueChange = viewModel::onBlueChanged,
            valueRange = 0f..255f,
            thumbColor = Color(0, 0, 255)
        )

        // 🔘 Кнопка "Случайный цвет"
        Button(
            onClick = viewModel::generateRandomColor,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("🎲 Случайный цвет")
        }
    }
}

// 🔁 Вспомогательный компонент для слайдера с подписью
@Composable
private fun SliderWithLabel(
    label: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float> = 0f..255f,
    thumbColor: androidx.compose.ui.graphics.Color
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$label: ${value.toInt()}",
            style = MaterialTheme.typography.bodyMedium
        )
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            steps = 254,
            // ✅ Правильный способ задать цвета через SliderDefaults.colors()
            colors = SliderDefaults.colors(
                thumbColor = thumbColor,
                activeTrackColor = thumbColor.copy(alpha = 0.6f),
                inactiveTrackColor = thumbColor.copy(alpha = 0.2f)
            ),
            modifier = Modifier.fillMaxWidth(0.9f)
        )
    }
}