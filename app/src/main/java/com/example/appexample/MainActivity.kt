package com.example.appexample

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Intent
import android.media.*
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var sPool: SoundPool
    private var mPlayer: MediaPlayer? = null

    private var soundID1 = -1
    private var soundID2 = -1

    private lateinit var logTextView: TextView
    private lateinit var scrollview: ScrollView

    private var recorder: MediaRecorder? = null
    private var audiofile: File? = null

    private lateinit var boton_spool1: Button
    private lateinit var boton_spool2: Button
    private lateinit var boton_mplayer: Button
    private lateinit var boton_mrecorder: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logTextView = findViewById(R.id.Log)
        scrollview = findViewById(R.id.ScrollView)

        volumeControlStream = AudioManager.STREAM_MUSIC

        sPool = SoundPool.Builder().setMaxStreams(2).build()

        sPool.setOnLoadCompleteListener { _, sampleId, _ ->
            log("Tono $sampleId cargado con SoundPool")
        }

        soundID1 = sPool.load(this, R.raw.bigben, 1)
        soundID2 = sPool.load(this, R.raw.alarma, 1)

        val clickListener = View.OnClickListener { v ->
            val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager

            val volumenActual = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC).toFloat()
            val volumenMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC).toFloat()
            val volumen = volumenActual / volumenMax

            when (v.tag.toString()) {
                "1" -> if (soundID1 > -1) sPool.play(soundID1, volumen, volumen, 1, 0, 1f)
                "2" -> if (soundID2 > -1) sPool.play(soundID2, volumen, volumen, 1, 0, 1f)
            }
        }

        boton_spool1 = findViewById(R.id.soundpool1)
        boton_spool2 = findViewById(R.id.soundpool2)

        boton_spool1.setOnClickListener(clickListener)
        boton_spool2.setOnClickListener(clickListener)

        boton_mplayer = findViewById(R.id.mediaplayer)
        boton_mplayer.setOnClickListener {
            if (mPlayer != null && mPlayer!!.isPlaying) {
                mPlayer!!.stop()
                boton_mplayer.text = "Reproducir Audio con MediaPlayer"
                enableButtons(true)
                log("Cancelada reproducción MediaPlayer")
            } else {
                boton_mplayer.text = "Cancelar"
                enableButtons(false)
                log("Reproduciendo Audio con MediaPlayer")

                mPlayer = MediaPlayer.create(this, R.raw.beethoven_para_elisa)
                mPlayer!!.start()

                mPlayer!!.setOnCompletionListener {
                    log("Fin Reproducción MediaPlayer")
                    enableButtons(true)
                }
            }
        }

        boton_mrecorder = findViewById(R.id.mediarecorder)
        boton_mrecorder.setOnClickListener {
            if (boton_mrecorder.text == "Parar grabación") {
                recorder?.stop()
                recorder?.release()
                recorder = null

                addRecordingToMediaLibrary()

                boton_mrecorder.text = "Grabar conversación"
                enableButtons(true)
                log("Parada grabación MediaRecorder")
            } else {
                boton_mrecorder.text = "Parar grabación"
                enableButtons(false)
                log("Grabando conversación")

                val directorio = Environment.getExternalStorageDirectory()

                try {
                    audiofile = File.createTempFile("sonido", ".3gp", directorio)
                } catch (e: IOException) {
                    Log.e("ERROR", "No se puede acceder a la SD")
                    return@setOnClickListener
                }

                recorder = MediaRecorder().apply {
                    setAudioSource(MediaRecorder.AudioSource.MIC)
                    setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                    setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                    setOutputFile(audiofile!!.absolutePath)

                    try {
                        prepare()
                        start()
                    } catch (e: Exception) {
                        Log.e("ERROR", "Error al grabar")
                    }
                }
            }
        }

        log("")
    }

    private fun enableButtons(enable: Boolean) {
        boton_spool1.isEnabled = enable
        boton_spool2.isEnabled = enable
        boton_mrecorder.isEnabled = enable
    }

    private fun addRecordingToMediaLibrary() {
        val values = ContentValues()
        val tiempoActual = System.currentTimeMillis()

        values.put(MediaStore.Audio.Media.TITLE, "audio${audiofile!!.name}")
        values.put(MediaStore.Audio.Media.DATE_ADDED, (tiempoActual / 1000).toInt())
        values.put(MediaStore.Audio.Media.MIME_TYPE, "audio/3gpp")
        values.put(MediaStore.Audio.Media.DATA, audiofile!!.absolutePath)

        val resolver: ContentResolver = contentResolver
        val uri: Uri = resolver.insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values)!!

        sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri))

        Toast.makeText(this, "Audio añadido a la librería", Toast.LENGTH_LONG).show()
    }

    private fun log(s: String) {
        logTextView.append("$s\n")
        scrollview.post { scrollview.fullScroll(View.FOCUS_DOWN) }
    }
}