package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.print.PrintAttributes
import android.print.PrintManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Print
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.BadgesGallerySection
import com.example.ui.components.TactileCard
import com.example.ui.components.TactilePrimaryButton
import com.example.ui.components.TactileSecondaryButton
import com.example.ui.theme.IndigoMid
import com.example.ui.theme.LocalMazColors
import com.example.ui.viewmodel.MazUiState

@Composable
fun ProgressScreen(
    uiState: MazUiState,
    isWideScreen: Boolean,
    buildPlainTextReport: () -> String,
    buildPrintableHtmlReport: () -> String
) {
    val context = LocalContext.current

    val onShare: () -> Unit = {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Rekod Prestasi MAZ Mandarin")
            putExtra(Intent.EXTRA_TEXT, buildPlainTextReport())
        }
        context.startActivity(Intent.createChooser(intent, "Kongsi Rekod Prestasi"))
    }

    val onPrintPdf: () -> Unit = {
        printHtmlReport(context, buildPrintableHtmlReport())
    }

    if (isWideScreen) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1.1f)
                    .fillMaxHeight(),
                contentPadding = PaddingValues(top = 10.dp, bottom = 28.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item { ProgressHeroSummaryCard(uiState) }
                item {
                    BadgesGallerySection(
                        badges = uiState.badges,
                        isWideScreen = true
                    )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .weight(0.9f)
                    .fillMaxHeight(),
                contentPadding = PaddingValues(top = 10.dp, bottom = 28.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text(
                        text = "Pecahan Kemajuan Mengikut Bab (${uiState.prefs.currentLevel.label})",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                items(uiState.chapters) { chap ->
                    ChapterProgressRow(uiState, chap.id, chap.numLabel, chap.emoji, chap.title)
                }
                item {
                    Spacer(Modifier.height(4.dp))
                    ReportActionsRow(onPrintPdf = onPrintPdf, onShare = onShare)
                }
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 10.dp, bottom = 28.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item { ProgressHeroSummaryCard(uiState) }
            item {
                BadgesGallerySection(
                    badges = uiState.badges,
                    isWideScreen = false
                )
            }
            item {
                Text(
                    text = "Pecahan Kemajuan Mengikut Bab",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            items(uiState.chapters) { chap ->
                ChapterProgressRow(uiState, chap.id, chap.numLabel, chap.emoji, chap.title)
            }
            item {
                Spacer(Modifier.height(4.dp))
                ReportActionsRow(onPrintPdf = onPrintPdf, onShare = onShare)
            }
        }
    }
}

@Composable
private fun ProgressHeroSummaryCard(uiState: MazUiState) {
    val maz = LocalMazColors.current
    val level = uiState.prefs.currentLevel

    TactileCard(cornerRadius = 24.dp, depth = 4.dp) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_maz_mandarin_foreground_1795482484183),
                    contentDescription = "Logo MAZ Mandarin",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(14.dp))
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = uiState.prefs.studentName,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "MAZ MANDARIN · ${level.label}" +
                            if (uiState.prefs.schoolCode.isNotBlank()) " · ${uiState.prefs.schoolCode}" else "",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Surface(shape = CircleShape, color = maz.amberSoft) {
                    Text(
                        text = "${uiState.rank.emoji} ${uiState.rank.titleBm}",
                        style = MaterialTheme.typography.labelSmall,
                        color = maz.onAmberSoft,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                }
            }

            // 2x2 Summary KPI Grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                StatKpiTile("Stesen Selesai", "${uiState.levelCompletedCount}/49", maz.indigoSoft, maz.onIndigoSoft, Modifier.weight(1f))
                StatKpiTile("Bintang (${level.shortLabel})", "⭐ ${uiState.levelStars}", maz.amberSoft, maz.onAmberSoft, Modifier.weight(1f))
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                StatKpiTile("Jumlah XP", "⚡ ${uiState.totalXp}", maz.jadeSoft, maz.onJadeSoft, Modifier.weight(1f))
                StatKpiTile("Ketepatan & Streak", "${uiState.accuracyPercent}% · 🔥${uiState.streakDays}h", maz.coralSoft, maz.onCoralSoft, Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun StatKpiTile(
    label: String,
    value: String,
    containerColor: androidx.compose.ui.graphics.Color,
    contentColor: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(15.dp),
        color = containerColor
    ) {
        Column(modifier = Modifier.padding(13.dp)) {
            Text(text = label, style = MaterialTheme.typography.labelSmall, color = contentColor.copy(alpha = 0.85f))
            Spacer(Modifier.height(3.dp))
            Text(text = value, style = MaterialTheme.typography.titleLarge, color = contentColor, fontWeight = FontWeight.Black)
        }
    }
}

@Composable
private fun ChapterProgressRow(
    uiState: MazUiState,
    chapterId: String,
    numLabel: String,
    emoji: String,
    title: String
) {
    val maz = LocalMazColors.current
    val level = uiState.prefs.currentLevel
    val done = uiState.stationsMeta.count { st ->
        uiState.progressMap.containsKey("${level.key}_${chapterId}_${st.id}")
    }
    val pct = done / 7f

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.2.dp, if (done == 7) maz.jade else maz.cardBorder)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = emoji, fontSize = 20.sp)
                    Text(
                        text = "$numLabel: $title",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Text(
                    text = "$done/7 Stesen",
                    style = MaterialTheme.typography.labelMedium,
                    color = if (done == 7) maz.jade else MaterialTheme.colorScheme.primary
                )
            }
            Spacer(Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { pct },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(7.dp)
                    .clip(CircleShape),
                color = if (done == 7) maz.jade else IndigoMid,
                trackColor = maz.cardBorder
            )
        }
    }
}

@Composable
private fun ReportActionsRow(
    onPrintPdf: () -> Unit,
    onShare: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TactileSecondaryButton(
            text = "Kongsi",
            leadingIcon = Icons.Rounded.Share,
            onClick = onShare,
            modifier = Modifier
                .weight(0.9f)
                .testTag("share_report_btn")
        )
        TactilePrimaryButton(
            text = "Cetak / Simpan PDF",
            leadingIcon = Icons.Rounded.Print,
            onClick = onPrintPdf,
            modifier = Modifier
                .weight(1.3f)
                .testTag("print_pdf_btn")
        )
    }
}

private fun printHtmlReport(context: Context, htmlContent: String) {
    val webView = WebView(context)
    webView.webViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean = false

        override fun onPageFinished(view: WebView, url: String?) {
            val printManager = context.getSystemService(Context.PRINT_SERVICE) as? PrintManager
            val jobName = "Rekod_Prestasi_MAZ_Mandarin"
            val printAdapter = view.createPrintDocumentAdapter(jobName)
            printManager?.print(jobName, printAdapter, PrintAttributes.Builder().build())
        }
    }
    webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)
}
