package com.example.marcadoreventos2.viewer.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marcadoreventos.model.entidades.eventos

@Composable
fun listarEventos(
    evento: eventos,
    onClick: () -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
    primeiroIcone: ImageVector = Icons.Rounded.Done,
    descriPrimeiroIcone: String = "",
    descriSegundoIcone: String = "close",
    segundoIcone: ImageVector = Icons.Filled.Delete

) {
    Row(
        modifier = modifier.fillMaxWidth().padding(8.dp).clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (evento.numeroConfirmacoes!! < evento.numVagas) {
            Icon(
                Icons.Rounded.Add,
                contentDescription = "",
                modifier.padding(end = 4.dp)
            )
        } else {
            Icon(
                primeiroIcone,
                contentDescription = descriPrimeiroIcone,
                modifier.padding(end = 4.dp)

            )
        }
        Column(modifier = modifier.weight(1f).padding(top = 12.dp)) {
            Text(
                modifier = Modifier,
                text = evento.nomeEvento,
                fontSize = 24.sp
            )
            Row(modifier = modifier) {
                Text(
                    modifier = Modifier,
                    text = "data: " + evento.inicio,
                    fontSize = 14.sp
                )

                Spacer(modifier = modifier.size(12.dp))

                Text(
                    modifier = Modifier,
                    text = "Vagas: " + evento.numeroConfirmacoes + "/" + evento.numVagas,
                    fontSize = 16.sp
                )

            }
        }


        IconButton(onClick = onClose) {
            Icon(segundoIcone, contentDescription = descriSegundoIcone)
        }
    }
}