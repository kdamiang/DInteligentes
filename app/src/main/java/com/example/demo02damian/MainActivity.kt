package com.example.demo02damian
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import com.example.demo02damian.ui.theme.Demo02damianTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Demo02damianTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "RESULTADO DE APRENDIZAJE",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Center,

                            )
                        Text(
                            text = "Unidad II.Aplicaciones para Wearables",
                            fontSize = 10.sp, // Tamaño de fuente más pequeño
                            modifier = Modifier.padding(top = 20.dp, bottom =70.dp) // Ajusta el padding superior e inferior
                        )



                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 16.dp)
                        ) {
                            Column(modifier = Modifier.padding(bottom = 16.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "Desarrollo para Dispositivos Móviles",
                                            fontSize = 14.sp,
                                        )
                                        Text(
                                            text = "Armando Méndez Morales",
                                            fontSize = 14.sp,
                                        )
                                        Text(
                                            text = "9A DyGS",
                                            fontSize = 14.sp,
                                        )
                                        Text(
                                            text = "Carlos Damian Gomez Santiz",
                                            fontSize = 14.sp,
                                        )
                                    }
                                    Image(
                                        painter = painterResource(R.drawable.foto),
                                        contentDescription = "Imagen",
                                        modifier = Modifier.size(100.dp)
                                    )
                                }
                            }
                        }

                        Column(modifier = Modifier.padding(bottom = 16.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(bottom = 16.dp)
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "Desarrollo para Dispositivos Móviles",
                                        fontSize = 14.sp,
                                    )
                                    Text(
                                        text = "Armando Méndez Morales",
                                        fontSize = 14.sp,
                                    )
                                    Text(
                                        text = "9A DyGS",
                                        fontSize = 14.sp,
                                    )

                                }
                                Spacer(modifier = Modifier.width(16.dp))
                                Image(
                                    painter = painterResource(R.drawable.foto),
                                    contentDescription = "Imagen",
                                    modifier = Modifier.size(100.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(bottom = 8.dp)
                            ) {
                            }
                        }


                        Box(
                            modifier = Modifier.padding(bottom = 14.dp)
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Button(onClick = {
                                    val integrante = Integrante(
                                        titulo = "Notificación de Carlos Damian",
                                        cuerpo = "Mira esta nueva actualización",
                                        url = "https://www.facebook.com/profile.php?id=100056381017309&mibextid=ZbWKwL",
                                        figura = R.drawable.foto
                                    )
                                    showNotificacion(context, integrante)
                                }) {
                                    Text(text = "Notificación ")
                                }
                                Spacer(modifier = Modifier.height(16.dp))

                            }
                        }


                    }
                }
            }
        }
    }
}
data class Integrante(val titulo: String, val cuerpo: String, val url: String, val figura: Int)

private fun showNotificacion(context: Context, integrante: Integrante) {
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val channelId = "message_channel"
    val channelName = "message_name"
    if (Build.VERSION.SDK_INT >= 26) {
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)
    }
    val idNotificacion = Random.nextInt(111111111, 999999999)
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(integrante.url))
    val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(integrante.figura)
        .setContentTitle(integrante.titulo)
        .setContentText(integrante.cuerpo)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .setGroup("MyApp")
    notificationManager.notify(idNotificacion, builder.build())
}
