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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
import com.example.marcadoreventos2.viewer.componentes.caixaLeituraTexto

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ContextCastToActivity",
    "SuspiciousIndentation"
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun tLeitura(navController: NavController, viewModel: MainViewModel){

    val evento: eventos? = viewModel.EventoManuseado
    val user = viewModel.user;

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
                        text = evento!!.nomeEvento?:"ALgo deu errado",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White)
                },
            )
        },
        containerColor = fundo
    ){  innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding).verticalScroll(
            rememberScrollState()
        ))
        {


            caixaLeituraTexto(
                titulo = "Autor",
                valor = evento?.autor?.name?:"Algo deu errado",
                nLinhas = 1,
                onValueChange = {},
                modifier = Modifier.padding(top = 50.dp, bottom = 20.dp, start = 15.dp, end = 15.dp).fillMaxWidth(),
            )
            var largura =180.dp

            Row(Modifier.fillMaxWidth().padding( top = 5.dp, bottom =  20.dp, start = 15.dp, end = 15.dp)){
                caixaLeituraTexto(
                    titulo = "Data de Inicio",
                    valor = evento?.inicio?:"Algo deu errado",
                    nLinhas = 1,
                    onValueChange = {},
                    modifier = Modifier.padding(end = 10.dp,).width(largura)
                )

                caixaLeituraTexto(
                    titulo = "Data de Termino",
                    valor = evento?.termino?:"Algo deu errado",
                    nLinhas = 1,
                    onValueChange = {},
                    modifier = Modifier.padding(start = 10.dp,).width(largura)
                )
            }

                caixaLeituraTexto(
                    titulo = "Cidade",
                    valor = evento?.cidadeEvento?:"Algo deu errado",
                    nLinhas = 1,
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth().padding( top = 5.dp, bottom =  20.dp, start = 15.dp, end = 15.dp)
                )


            Row(Modifier.fillMaxWidth().padding( top = 5.dp, bottom =  20.dp, start = 15.dp, end = 15.dp)){
                caixaLeituraTexto(
                    titulo = "Numero de Vagas Ocupadas",
                    valor = evento?.numeroConfirmacoes.toString(),
                    nLinhas = 1,
                    onValueChange = {},
                    modifier = Modifier.padding(end = 10.dp,).width(largura)
                )

                caixaLeituraTexto(
                    titulo = "Total de Vagas",
                    valor = evento?.numVagas.toString(),
                    nLinhas = 1,
                    onValueChange = {},
                    modifier = Modifier.padding(start = 10.dp,).width(largura)
                )
            }

            caixaLeituraTexto(
                titulo = "Descrição do Evento",
                valor = evento?.descricao?:"Algo deu errado",
                nLinhas = 7,
                onValueChange = {},
                modifier = Modifier.padding(top = 5.dp, bottom = 20.dp, start = 15.dp, end = 15.dp).fillMaxWidth(),
            )
            var validarBotao:Boolean

                if (evento != null) {
                    val user = viewModel.user;


                    val usuario  = evento?.participantes?.find { it.name  == user?.name && it.email  == user?.email  }

                    validarBotao = evento.numeroConfirmacoes!! < evento.numVagas!! && usuario == null

                }else{
                    validarBotao = false
                }

                    botao(
                        texto = "Participar",
                        enabled = validarBotao && (evento?.autor?.equals(user) == false),
                        modifier = Modifier.fillMaxWidth().height(80.dp).padding(20.dp),
                        onCLick = {

                            evento?.numeroConfirmacoes = evento?.numeroConfirmacoes?.plus(1);
                            evento?.participantes?.add(viewModel.user!!)
                            viewModel.updateEvento(evento!!)
                            navController.popBackStack()

                        }
                    )
                    botao(
                        texto = "Voltar",
                        enabled = true,
                        modifier = Modifier.fillMaxWidth().height(80.dp).padding(20.dp),
                        onCLick = {
                            navController.popBackStack()
                        }
                    )


        }

    }
}