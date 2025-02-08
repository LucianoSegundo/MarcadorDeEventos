package com.example.marcadoreventos2.viewer.componentes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.unit.sp
import com.example.marcadoreventos2.ui.theme.corFocoInpute
import com.example.marcadoreventos2.ui.theme.corInpute
import com.example.marcadoreventos2.ui.theme.corInputeBorda

@Composable
fun caixaLeituraTexto(
    valor:String,
    titulo:String,
    onValueChange: (String) -> Unit,
    nLinhas: Int,
    modifier: Modifier,

){
    OutlinedTextField(
        value = valor,
        readOnly = true,
        onValueChange = onValueChange,
        label = { Text(text = titulo , fontWeight = FontWeight.Bold, fontSize = 12.sp) },
        maxLines = nLinhas,
        minLines = nLinhas,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = corInpute,
            unfocusedContainerColor = corInpute,
            focusedIndicatorColor = corInputeBorda,
            cursorColor = corFocoInpute,
            focusedLabelColor = Color.Black,
            unfocusedIndicatorColor = corInputeBorda,
            focusedTextColor = Color.Black
        ),
        modifier = modifier

    )

}

@Composable
fun caixaTexto(
    valor:String,
    titulo:String,
    onValueChange: (String) -> Unit,
    nLinhas: Int,
    modifier: Modifier,
    keyboardType: KeyboardType
){
    OutlinedTextField(
        value = valor,
        onValueChange = onValueChange,
        label = { Text(text = titulo) },
        maxLines = nLinhas,
        minLines = nLinhas,
        keyboardOptions =  KeyboardOptions(keyboardType = keyboardType),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = corInpute,
            unfocusedContainerColor = corInpute,
            focusedIndicatorColor = corFocoInpute,
            cursorColor = corFocoInpute,
            focusedLabelColor = corFocoInpute,
            unfocusedIndicatorColor = corInputeBorda,
            focusedTextColor = Color.Black
        ),
        modifier = modifier

    )

}

