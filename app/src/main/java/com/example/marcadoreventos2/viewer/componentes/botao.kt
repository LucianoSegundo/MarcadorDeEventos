package com.example.marcadoreventos2.viewer.componentes

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marcadoreventos2.ui.nav.Route
import com.example.marcadoreventos2.ui.theme.corBotoes
import com.example.marcadoreventos2.ui.theme.corBotoesDesabilitados

@Composable
fun botao(
    texto:String,
    enabled: Boolean,
    onCLick: () -> Unit,
    modifier: Modifier

){

    Button(
        enabled = enabled,
        onClick = onCLick,
        colors = ButtonColors(
            containerColor = corBotoes,
            contentColor = Color.White,
            disabledContainerColor = corBotoesDesabilitados,
            disabledContentColor = Color.White
        ),
        modifier= modifier,
        shape = RoundedCornerShape(5.dp)
    ) { Text(text = texto, fontWeight = FontWeight.Bold, fontSize = 18.sp) }
}