package com.example.previsodotempoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.previsodotempoapp.presentation.WeatherViewModel
import com.example.previsodotempoapp.presentation.ui.theme.PrevisãoDoTempoAppTheme
import dagger.hilt.android.AndroidEntryPoint
import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import com.example.previsodotempoapp.presentation.WeatherCard
import com.example.previsodotempoapp.presentation.ui.theme.DarkBlue
import com.example.previsodotempoapp.presentation.ui.theme.DeepBlue

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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DarkBlue)
                ) {
                    //primeiro elemento
                    WeatherCard(state = viewModel.state,
                        backGroundColor = DeepBlue )
                }
            }
        }
    }
}
