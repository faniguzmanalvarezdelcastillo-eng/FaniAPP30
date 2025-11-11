package com.example.app30dias.ui.theme.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

//Para los elementos de lista que tienen varios datos, como una imagen y texto,
// necesitarás una clase que contenga todas estas propiedades.
// Las clases de datos son un tipo de clase que solo contiene propiedades y pueden
// proporcionar algunos métodos de utilidad para trabajar con esas propiedades.
class Sugerencias (
    //Cada sugerenci consiste en una imagen y una cadena
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)
//stringResourceId representa un ID para el texto de sugerencia almacenado en un recurso de cadenas.
//imageResourceId representa un ID para la imagen de sugerencia almacenada en un recurso de elementos de diseño.