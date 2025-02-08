package com.example.marcadoreventos2.viewer.paginas

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import com.example.marcadoreventos2.model.MainViewModel
import com.example.marcadoreventos2.ui.nav.Route
import com.example.marcadoreventos2.ui.theme.corTextoTopBar
import com.example.marcadoreventos2.ui.theme.corTopBar
import com.example.marcadoreventos2.ui.theme.fundo
import com.example.marcadoreventos2.viewer.componentes.botao
import com.example.marcadoreventos2.viewer.componentes.caixaTexto

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun tLogin(navController: NavController, viewModel: MainViewModel){
    Scaffold(
    topBar = {
        TopAppBar(
            colors = TopAppBarColors(
                containerColor = corTopBar,
                scrolledContainerColor = corTopBar,
                navigationIconContentColor =corTextoTopBar,
                titleContentColor =corTextoTopBar,
                actionIconContentColor = corTextoTopBar,
            ),
            title = {
            Text(
                text = "Marcador de Eventos",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White)
            },

        )
    },
        containerColor = fundo


    ){ innerPadding ->
        Column( verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize().padding(innerPadding).verticalScroll(
            rememberScrollState()
        ))
        {
            var login:String by remember { mutableStateOf("") }
            var senha: String by remember { mutableStateOf("") }

            caixaTexto(
                valor = login,
                titulo = "Login",
                onValueChange =  {login = it},
                nLinhas = 1,
                keyboardType = KeyboardType.Text,
                modifier = Modifier.padding(top = 90.dp, bottom = 40.dp, start = 15.dp, end = 15.dp).fillMaxWidth(),
            )
            caixaTexto(
                valor = senha,
                titulo = "Senha",
                onValueChange = {senha = it},
                nLinhas = 1,
                keyboardType = KeyboardType.Password,
                modifier = Modifier.padding(top = 20.dp, bottom = 60.dp, start = 15.dp, end = 15.dp).fillMaxWidth(),

                )

                botao(
                    texto = "Logar",
                    enabled = senha != "" && login != "",
                    modifier = Modifier.fillMaxWidth().height(80.dp).padding(20.dp),
                    onCLick = { navController.navigate(Route.tHome) },
                    )
            botao(
                texto = "Criar Conta",
                enabled = true,
                modifier = Modifier.fillMaxWidth().height(80.dp).padding(20.dp),
                onCLick = { navController.navigate(Route.tCadastro) },
            )

        }
    }
}