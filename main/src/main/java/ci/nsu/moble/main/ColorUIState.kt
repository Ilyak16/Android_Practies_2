package ci.nsu.moble.main
import androidx.compose.ui.graphics.Color
data class ColorUiState(
    val red: Int = 128,
    val green: Int = 128,
    val blue: Int = 128
) {
    // Вычисляемый цвет для Compose
    val color: androidx.compose.ui.graphics.Color
        get() = androidx.compose.ui.graphics.Color(red, green, blue)

    // HEX-код в формате #RRGGBB
    val hexCode: String
        get() = "#${red.toString(16).padStart(2, '0')}${green.toString(16).padStart(2, '0')}${blue.toString(16).padStart(2, '0')}"
}