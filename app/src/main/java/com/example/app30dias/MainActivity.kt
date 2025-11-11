package com.example.app30dias

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app30dias.ui.theme.App30DiasTheme
import com.example.app30dias.ui.theme.data.DataSource
import com.example.app30dias.ui.theme.model.Sugerencias



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App30DiasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AffirmationsApp()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AffirmationsApp(modifier: Modifier = Modifier) {
    Column () {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.headlineMedium,

                )
            },
            modifier = modifier
        )


        //En la función de componibilidad AffirmationsApp,
        //recupera las direcciones de diseño actuales y guárd
        // alas en una variable.
        //Estas se usarán para configurar el padding más adelante.
        val layoutDirection = LocalLayoutDirection.current
        //Pasa un elemento Modifier a la función de componibilidad Surface que completa el ancho y la altura máximos de su elemento superior,
        //establece el padding de la barra de estado y establece el padding inicial y final en layoutDirection.
        //Este es un ejemplo de cómo traducir un objeto LayoutDirection en padding: WindowInsets.safeDrawing.asPaddingValues().calculateStartPadding(layoutDirection).
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(
                    start = WindowInsets.safeDrawing.asPaddingValues()
                        .calculateStartPadding(layoutDirection),
                    end = WindowInsets.safeDrawing.asPaddingValues()
                        .calculateEndPadding(layoutDirection),
                )
        ) {

            //llama a la función de componibilidad AffirmationList y pasa DataSource().loadAffirmations() al parámetro affirmationList.
            AffirmationList(
                sugerenciasList = DataSource().Sugerencias()
            )
        }
    }
}

@Composable
//Se recomienda pasar un modificador a cada elemento componible y establecerlo en un valor predeterminado.
fun AffirmationCard(sugerencias: Sugerencias, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {// Esto te permite colocar una imagen sobre el texto asociado
            Image(//un elemento Image componible siempre requiere un recurso para mostrarse y una contentDescription.
                //El recurso debe ser un painterResource que se pase al parámetro painter.
                //El método painterResource cargará elementos de diseño vectoriales o formatos de elementos de trama, como PNG.
                //Además, pasa un elemento stringResource para el parámetro contentDescription.
                painter = painterResource(sugerencias.imageResourceId),
                contentDescription = stringResource(sugerencias.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop//Una contentScale determina cómo se debe escalar y mostrar la imagen.
                //El objeto Modifier debe tener establecido el atributo fillMaxWidth y una altura de 194.dp.
                //El contentScale debe ser ContentScale.Crop.
            )
            Text(
               // modifier = Modifier.background(color = Color.Red)//Fondo del texto
                text = LocalContext.current.getString(sugerencias.stringResourceId),
                modifier = Modifier.padding(16.dp),
                //modifier=Modifier.background(color = Color.Red),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}



@Composable
fun AffirmationList(sugerenciasList : List<Sugerencias>, modifier: Modifier = Modifier) {
    //Un elemento LazyColumn puede agregar contenido a pedido, lo que lo hace útil para listas largas y,
    //en especial, cuando se desconoce la longitud de la lista.
    //Un LazyColumn también proporciona desplazamiento de manera predeterminada, sin código adicional.
    LazyColumn(modifier = modifier) {
        items(sugerenciasList) { sugerencias ->
            //El método items() es la manera en la que agregas elementos a LazyColumn.
            // Este método es algo único de ese elemento y no se suele usar para la mayoría de los elementos componibles.
            com.example.app30dias.AffirmationCard(//Para cada afirmación de la lista, llama al elemento componible AffirmationCard().
                //Pásale la affirmation y un objeto Modifier con el atributo padding establecido en 8.dp.
                sugerencias = sugerencias,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayLarge,
            )
        },
        modifier = modifier
    )
}

 */


@Preview
@Composable
private fun AffirmationCardPreview() {
    // AffirmationCard(Affirmation(R.string.affirmation1, R.drawable.image1))
    AffirmationsApp()

}