package com.candybytes.taco.ui.category

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.candybytes.taco.ui.vm.category.CategoriesContract
import com.candybytes.taco.ui.vm.category.CategoriesViewModel
import kotlinx.coroutines.flow.collect

@Composable
fun CategoriesPage(){
    val viewModel  = hiltViewModel<CategoriesViewModel>()
    val context =  LocalContext.current
    
    val state by viewModel.viewState.collectAsState()
    
    LaunchedEffect(Unit){
        viewModel.effect.collect { 
            when(it){
                is CategoriesContract.Effect.ErrorMessage -> {
                    Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
                }
                is CategoriesContract.Effect.NavigateTo.CategoryScreen -> TODO()
            }
        }
    }
    
    LazyColumn {

        items(state.categories){
            Column {
                Text(text = it.name,style = TextStyle(fontWeight = FontWeight.Bold))
                Text(text = it.count.toString(),style = TextStyle(fontWeight = FontWeight.Light))
                Divider()
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
