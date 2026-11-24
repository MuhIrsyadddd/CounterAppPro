package com.example.counterapppro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.counterapppro.ui.theme.CounterAppProTheme

/**
 * MainActivity adalah titik masuk (entry point) utama dari aplikasi Counter App Pro.
 * Kelas ini bertanggung jawab untuk menginisialisasi UI menggunakan Jetpack Compose.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Mengaktifkan tampilan layar penuh (edge-to-edge), status bar transparan
        enableEdgeToEdge()

        setContent {
            CounterAppProTheme {
                // Scaffold menyediakan struktur layout dasar dari Material Design
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Memanggil Composable utama aplikasi
                    CounterAppScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

/**
 * Composable utama yang menampilkan antarmuka aplikasi Counter App Pro.
 *
 * Fitur:
 * 1. Menambah dan mengurangi nilai (Increment/Decrement)
 * 2. Mereset nilai kembali ke 0
 * 3. Mengubah besaran langkah penambahan/pengurangan (Step)
 *
 * @param modifier Modifier untuk memberikan penyesuaian layout pada komponen.
 */
@Composable
fun CounterAppScreen(modifier: Modifier = Modifier) {
    // State 'count' menyimpan nilai angka saat ini.
    // Menggunakan remember agar state tidak hilang (bertahan) saat recomposition.
    var count by remember { mutableIntStateOf(0) }

    // State 'step' menyimpan besaran lompatan penambahan atau pengurangan.
    var step by remember { mutableIntStateOf(1) }

    // Column menyusun elemen-elemen di dalamnya secara vertikal dari atas ke bawah
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // Memosisikan elemen di tengah secara vertikal
    ) {
        // --- Bagian Header Aplikasi ---
        Text(
            text = "Counter App Pro",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // --- Bagian Tampilan Angka ---
        Text(
            text = count.toString(),
            fontSize = 80.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // --- Bagian Tombol Utama (Kurang, Reset, Tambah) ---
        // Row menyusun elemen secara horizontal dari kiri ke kanan
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly, // Jarak antar elemen merata
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Tombol Kurang (Decrement)
            Button(
                onClick = { count -= step }, // Mengurangi nilai count sebesar nilai step
                modifier = Modifier.size(72.dp),
                shape = MaterialTheme.shapes.large
            ) {
                Text("-", fontSize = 32.sp, fontWeight = FontWeight.Bold)
            }

            // Tombol Reset (mengembalikan nilai ke 0)
            Button(
                onClick = { count = 0 },
                modifier = Modifier.size(64.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("0", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }

            // Tombol Tambah (Increment)
            Button(
                onClick = { count += step }, // Menambahkan nilai count sebesar nilai step
                modifier = Modifier.size(72.dp),
                shape = MaterialTheme.shapes.large
            ) {
                Text("+", fontSize = 32.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        // --- Bagian Pengaturan Langkah (Step) ---
        Text(
            text = "Pilih Langkah (Step): $step",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            // Opsi untuk mengatur langkah penambahan sebesar 1
            StepButton(
                text = "+1",
                isSelected = step == 1,
                onClick = { step = 1 }
            )
            Spacer(modifier = Modifier.width(12.dp))

            // Opsi untuk mengatur langkah penambahan sebesar 5
            StepButton(
                text = "+5",
                isSelected = step == 5,
                onClick = { step = 5 }
            )
            Spacer(modifier = Modifier.width(12.dp))

            // Opsi untuk mengatur langkah penambahan sebesar 10
            StepButton(
                text = "+10",
                isSelected = step == 10,
                onClick = { step = 10 }
            )
        }
    }
}

/**
 * Composable pembantu untuk menampilkan tombol pemilihan step.
 * Jika tombol ini terpilih, tampilannya berupa Button biasa (filled),
 * sebaliknya jika tidak terpilih akan berbentuk OutlinedButton (hanya garis tepi).
 *
 * @param text Teks angka langkah yang ditampilkan pada tombol (contoh: "+5").
 * @param isSelected Status boolean apakah tombol ini sedang aktif/terpilih.
 * @param onClick Fungsi atau aksi yang dijalankan ketika tombol diklik.
 */
@Composable
fun StepButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    if (isSelected) {
        Button(onClick = onClick) {
            Text(text)
        }
    } else {
        OutlinedButton(onClick = onClick) {
            Text(text)
        }
    }
}

/**
 * Preview UI di Android Studio untuk memudahkan pengembangan UI
 * tanpa harus menjalankan aplikasi di emulator (meningkatkan efisiensi).
 */
@Preview(showBackground = true, name = "Counter App Pro Preview")
@Composable
fun CounterAppScreenPreview() {
    CounterAppProTheme {
        CounterAppScreen()
    }
}
