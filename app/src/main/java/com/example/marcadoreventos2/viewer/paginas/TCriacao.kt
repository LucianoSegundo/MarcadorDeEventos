package com.example.marcadoreventos2.viewer.paginas

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.marcadoreventos.model.entidades.eventos
import com.example.marcadoreventos2.model.MainViewModel
import com.example.marcadoreventos2.ui.nav.Route
import com.example.marcadoreventos2.ui.theme.corTextoTopBar
import com.example.marcadoreventos2.ui.theme.corTopBar
import com.example.marcadoreventos2.ui.theme.fundo
import com.example.marcadoreventos2.viewer.componentes.botao
import com.example.marcadoreventos2.viewer.componentes.caixaTexto

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState"
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun tCriacao(navController: NavController, viewModel: MainViewModel){

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = corTopBar,
                    scrolledContainerColor = corTopBar,
                    navigationIconContentColor = corTextoTopBar,
                    titleContentColor = corTextoTopBar,
                    actionIconContentColor = corTextoTopBar,
                ),
                title = {
                    Text(
                        text = "Criar Evento",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White)
                },


            )
        },
        containerColor = fundo
    )  { innerPadding ->

            var nomeEvento by remember { mutableStateOf("") }
            var  dataInicio by remember { mutableStateOf("") }
            var  datTermino by remember { mutableStateOf("") }
            var  cidade by remember { mutableStateOf("") }
            var  estado by remember { mutableStateOf("") }
            var  nVagas by remember { mutableStateOf(0) }
            var  descricao by remember { mutableStateOf("") }

        var largura =180.dp

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .verticalScroll(
                rememberScrollState()
            )) {

            caixaTexto(
                titulo = "Nome do Evento",
                valor = nomeEvento,
                nLinhas = 1,
                onValueChange = {nomeEvento = it},
                modifier = Modifier
                    .padding(top = 50.dp, bottom = 20.dp, start = 15.dp, end = 15.dp)
                    .fillMaxWidth(),
                keyboardType = KeyboardType.Text,

                )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 20.dp, start = 15.dp, end = 15.dp)){
            caixaTexto(
                titulo = "Data de Inicio",
                valor = dataInicio,
                nLinhas = 1,
                onValueChange = {dataInicio = it},
                modifier = Modifier
                    .padding(end = 10.dp,)
                    .width(largura),
                keyboardType = KeyboardType.Text
            )

            caixaTexto(
                titulo = "Data de Termino",
                valor = datTermino,
                nLinhas = 1,
                onValueChange = {datTermino = it},
                modifier = Modifier
                    .padding(start = 10.dp,)
                    .width(largura),
                keyboardType = KeyboardType.Text
            )
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 20.dp, start = 15.dp, end = 15.dp)){
            caixaTexto(
                titulo = "Cidade",
                valor = cidade,
                nLinhas = 1,
                onValueChange = {cidade = it},
                modifier = Modifier
                    .padding(end = 10.dp,)
                    .width(largura),
                keyboardType = KeyboardType.Text
            )

            caixaTexto(
                titulo = "Estado",
                valor = estado,
                nLinhas = 1,
                onValueChange = {estado = it},
                modifier = Modifier
                    .padding(start = 10.dp,)
                    .width(largura),
                keyboardType = KeyboardType.Text

            )
        }

            caixaTexto(
                titulo = "Total de Vagas",
                valor = nVagas.toString(),
                nLinhas = 1,
                onValueChange = {nVagas = it.toInt()},
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 20.dp, start = 15.dp, end = 15.dp)
                    .fillMaxWidth(),
                keyboardType = KeyboardType.Number,

            )

        caixaTexto(
            titulo = "Descrição do Evento",
            valor = descricao,
            nLinhas = 5,
            onValueChange = {descricao = it},
            modifier = Modifier
                .padding(top = 5.dp, bottom = 20.dp, start = 15.dp, end = 15.dp)
                .fillMaxWidth(),
            keyboardType = KeyboardType.Text

        )

        botao(
            texto = "Salvar",
            enabled = nomeEvento.isNotBlank() && dataInicio.isNotBlank() && dataInicio.isNotBlank() && cidade.isNotBlank() && estado.isNotBlank() && nVagas >0   ,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(20.dp),
            onCLick = {

                var autor = viewModel.user
                var evento = eventos(
                    nomeEvento = nomeEvento,
                    autor = autor!!,
                    estadoEvento = estado,
                    cidadeEvento = cidade,
                    numVagas = nVagas,
                    numeroConfirmacoes = 0,
                    inicio = dataInicio,
                    termino = datTermino,
                    descricao = descricao,
                    location = viewModel.localizacaoSelecionada!!
                )

                viewModel.addEvento(evento);

            }
        )
        botao(
            texto = "Voltar",
            enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(20.dp),
            onCLick = {
                navController.popBackStack()
            }
        )

        }
    }

}