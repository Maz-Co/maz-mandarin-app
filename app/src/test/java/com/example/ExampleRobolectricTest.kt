package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.SyllabusRepository
import com.example.data.model.LevelId
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read app name from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("MAZ Mandarin", appName)
    }

    @Test
    fun `syllabus repository loads all 735 BCK KSSM learning items`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val repo = SyllabusRepository(context)
        var total = 0
        LevelId.entries.forEach { lvl ->
            val chapters = repo.getChapters(lvl)
            assertEquals(7, chapters.size)
            chapters.forEach { chap ->
                total += repo.getFlashcards(lvl, chap.id).size
                listOf("s2", "s3", "s4", "s5", "s6", "s7").forEach { st ->
                    total += repo.getQuizItems(lvl, chap.id, st).size
                }
            }
        }
        assertEquals(735, total)
        assertEquals(105, repo.getAllFlashcards().size)
        assertTrue(repo.generateSpeedDrill(LevelId.T1, 10).size == 10)
    }
}
