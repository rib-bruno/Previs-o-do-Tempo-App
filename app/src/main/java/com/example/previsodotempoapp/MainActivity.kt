package com.example.previsodotempoapp

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.previsodotempoapp.presentation.WeatherCard
import com.example.previsodotempoapp.presentation.WeatherForecast
import com.example.previsodotempoapp.presentation.WeatherViewModel
import com.example.previsodotempoapp.presentation.ui.theme.DarkBlue
import com.example.previsodotempoapp.presentation.ui.theme.DeepBlue
import com.example.previsodotempoapp.presentation.ui.theme.PrevisãoDoTempoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint //ser capaz de injetar dependências em uma activity usando hilt
class MainActivity : ComponentActivity() {

    //referencia ao viewmodel
    private val viewModel: WeatherViewModel by viewModels()

    //lançando/solicitar permissões, usando oS resultados da activity Api
    //e inicia-la com uma array de permissões que será solicitado
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //especificando um contrato
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            //callback obter um map para cada permissão e se aceitará ou não - já está sendo feito
            viewModel.loadWeatherInfo()
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION

            )
        )
        setContent {
            PrevisãoDoTempoAppTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(DarkBlue)
                    ) {
                        //primeiro elemento
                        WeatherCard(
                            state = viewModel.state,
                            backGroundColor = DeepBlue
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        WeatherForecast(state = viewModel.state)
                    }
                    if(viewModel.state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    //verificar se o erro não é null
                    viewModel.state.error?.let { error ->
                        Text(
                            text = error,
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}
