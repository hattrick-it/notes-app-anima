package com.example.notas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notas.ui.theme.NotasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

data class Note(val title: String, val text: String)

@Composable
fun MainScreen() {
    var noteTitle by remember { mutableStateOf("") }
    var noteText by remember { mutableStateOf("") }
    var noteList by remember {
        mutableStateOf(mutableStateListOf<Note>())
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column(Modifier.fillMaxWidth()) {
            Row() {
                OutlinedTextField(
                    value = noteTitle,
                    placeholder = { Text(text = "Title") },
                    onValueChange = { nuevoValor ->
                        noteTitle = nuevoValor
                    })
                Spacer(modifier = Modifier.width(10.dp))
                Button(onClick = {
                    val nuevaNota = Note(title = noteTitle, text = noteText)
                    noteList.add(nuevaNota)
                }) {
                    Text(
                        text = "Add Note",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = noteText,
                onValueChange = { nuevoValor ->
                    noteText = nuevoValor
                },
                Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Note") },
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        LazyColumn() {
            items(noteList) { nota ->
                NoteCompose(titulo = nota.title, textoNota = nota.text)
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}

@Composable
fun NoteCompose(titulo: String, textoNota: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color.Yellow)
            .border(
                width = 1.dp,
                color = Color.Black,
                RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Column {
            Text(text = titulo, fontWeight = FontWeight.Bold)
            Text(text = textoNota)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotasTheme {
        MainScreen()
    }
}