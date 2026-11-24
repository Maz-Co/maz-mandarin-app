package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.School
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.components.AudioChipButton
import com.example.ui.components.TactileCard
import com.example.ui.components.TactilePrimaryButton
import com.example.ui.theme.LocalMazColors
import com.example.ui.viewmodel.MazUiState

@Composable
fun SettingsScreen(
    uiState: MazUiState,
    onTogglePinyin: (Boolean) -> Unit,
    onToggleSoundFx: (Boolean) -> Unit,
    onToggleAutoSpeak: (Boolean) -> Unit,
    onToggleDarkTheme: (Boolean) -> Unit,
    onSelectSpeechRate: (Float) -> Unit,
    onSelectVoice: (String) -> Unit,
    onTestVoice: () -> Unit,
    onSaveProfile: (String, String) -> Unit,
    onResetLevelProgress: () -> Unit
) {
    val maz = LocalMazColors.current
    val prefs = uiState.prefs

    var studentNameInput by remember(prefs.studentName) { mutableStateOf(prefs.studentName) }
    var schoolCodeInput by remember(prefs.schoolCode) { mutableStateOf(prefs.schoolCode) }
    var profileSavedNotice by remember { mutableStateOf(false) }
    var showResetDialog by remember { mutableStateOf(false) }

    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = { Text("Set Semula Kemajuan ${prefs.currentLevel.label}?") },
            text = {
                Text("Semua rekod stesen selesai dan bintang bagi ${prefs.currentLevel.label} akan dipadamkan. Teruskan?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showResetDialog = false
                        onResetLevelProgress()
                    }
                ) {
                    Text("Ya, Padam", color = maz.coral, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) {
                    Text("Batal")
                }
            }
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 720.dp)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 10.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "⚙️ Tetapan & Suara Guru",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            // Student Profile Card (for PDF Report & School Code)
            item {
                TactileCard(cornerRadius = 22.dp, depth = 4.dp) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "🪪 Profil Murid (Untuk Rekod Prestasi)",
                            style = MaterialTheme.typography.titleMedium
                        )
                        OutlinedTextField(
                            value = studentNameInput,
                            onValueChange = {
                                studentNameInput = it
                                profileSavedNotice = false
                            },
                            label = { Text("Nama Murid") },
                            leadingIcon = { Icon(Icons.Rounded.Person, contentDescription = null) },
                            singleLine = true,
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("student_name_input")
                        )
                        OutlinedTextField(
                            value = schoolCodeInput,
                            onValueChange = {
                                schoolCodeInput = it
                                profileSavedNotice = false
                            },
                            label = { Text("Kod Kelas / Sekolah (cth: 1 ARIF / MAZ2026)") },
                            leadingIcon = { Icon(Icons.Rounded.School, contentDescription = null) },
                            singleLine = true,
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("school_code_input")
                        )
                        TactilePrimaryButton(
                            text = if (profileSavedNotice) "Profil Disimpan ✓" else "Simpan Profil Murid",
                            leadingIcon = Icons.Rounded.Check,
                            onClick = {
                                onSaveProfile(studentNameInput, schoolCodeInput)
                                profileSavedNotice = true
                            },
                            modifier = Modifier.testTag("save_profile_btn")
                        )
                    }
                }
            }

            // Learning & Audio Toggles Card
            item {
                TactileCard(cornerRadius = 22.dp, depth = 4.dp) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        SettingSwitchRow(
                            title = "Papar Bantuan Pinyin",
                            subtitle = "Tunjukkan ejaan Pinyin di bawah aksara Cina semasa kuiz.",
                            checked = prefs.showPinyin,
                            onCheckedChange = onTogglePinyin,
                            tag = "switch_pinyin"
                        )
                        HorizontalDivider(color = maz.cardBorder)
                        SettingSwitchRow(
                            title = "Kesan Bunyi Audio (Betul & Salah)",
                            subtitle = "Mainkan sorakan kejayaan dan bunyi maklum balas kuiz.",
                            checked = prefs.soundFx,
                            onCheckedChange = onToggleSoundFx,
                            tag = "switch_sfx"
                        )
                        HorizontalDivider(color = maz.cardBorder)
                        SettingSwitchRow(
                            title = "Sebutan Automatik",
                            subtitle = "Sebut perkataan Mandarin secara automatik apabila soalan dibuka.",
                            checked = prefs.autoSpeak,
                            onCheckedChange = onToggleAutoSpeak,
                            tag = "switch_autospeak"
                        )
                        HorizontalDivider(color = maz.cardBorder)
                        SettingSwitchRow(
                            title = "Mod Gelap Akademik",
                            subtitle = "Paparan gelap yang selesa untuk ulang kaji waktu malam.",
                            checked = prefs.darkTheme,
                            onCheckedChange = onToggleDarkTheme,
                            tag = "switch_dark_theme"
                        )
                    }
                }
            }

            // Mandarin Voice & Speech Speed Studio
            item {
                TactileCard(cornerRadius = 22.dp, depth = 4.dp) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "🎙️ Studio Suara Guru Mandarin (zh-CN)",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Kelajuan sebutan Mandarin:",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            val speeds = listOf(
                                0.68f to "Perlahan",
                                0.88f to "Biasa",
                                1.08f to "Pantas"
                            )
                            speeds.forEach { (rate, label) ->
                                val selected = kotlin.math.abs(prefs.speechRate - rate) < 0.08f
                                Surface(
                                    modifier = Modifier
                                        .weight(1f)
                                        .heightIn(min = 48.dp)
                                        .clip(RoundedCornerShape(13.dp))
                                        .clickable { onSelectSpeechRate(rate) },
                                    shape = RoundedCornerShape(13.dp),
                                    color = if (selected) maz.indigoSoft else maz.subtleSurface,
                                    border = BorderStroke(
                                        1.5.dp,
                                        if (selected) MaterialTheme.colorScheme.primary else maz.cardBorder
                                    )
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            text = label,
                                            style = MaterialTheme.typography.labelLarge,
                                            color = if (selected) maz.onIndigoSoft else MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                }
                            }
                        }

                        if (uiState.availableVoices.isNotEmpty()) {
                            Text(
                                text = "Pilih suara Mandarin pada peranti anda:",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                uiState.availableVoices.take(5).forEach { voice ->
                                    val selected = prefs.preferredVoiceName == voice.name ||
                                        (prefs.preferredVoiceName.isBlank() && voice == uiState.availableVoices.first())
                                    Surface(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .heightIn(min = 48.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                            .clickable { onSelectVoice(voice.name) },
                                        shape = RoundedCornerShape(12.dp),
                                        color = if (selected) maz.jadeSoft else maz.subtleSurface,
                                        border = BorderStroke(1.2.dp, if (selected) maz.jade else maz.cardBorder)
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = voice.displayName,
                                                style = MaterialTheme.typography.bodySmall,
                                                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                                                color = if (selected) maz.onJadeSoft else MaterialTheme.colorScheme.onSurface,
                                                modifier = Modifier.weight(1f)
                                            )
                                            if (selected) {
                                                Icon(Icons.Rounded.Check, contentDescription = null, tint = maz.jade)
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        AudioChipButton(
                            label = "Uji Suara Guru Mandarin",
                            onClick = onTestVoice,
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("test_voice_btn")
                        )
                    }
                }
            }

            // Reset Danger Zone
            item {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 52.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .testTag("reset_progress_btn")
                        .clickable { showResetDialog = true },
                    shape = RoundedCornerShape(16.dp),
                    color = maz.coralSoft,
                    border = BorderStroke(1.5.dp, maz.coral.copy(alpha = 0.55f))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Rounded.DeleteOutline, contentDescription = null, tint = maz.onCoralSoft)
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "Set Semula Kemajuan ${prefs.currentLevel.label}",
                            style = MaterialTheme.typography.labelLarge,
                            color = maz.onCoralSoft,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SettingSwitchRow(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    tag: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f).padding(end = 12.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.testTag(tag)
        )
    }
}
