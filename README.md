# 📱 App – Reproductor y Grabador Multimedia

Aplicación Android desarrollada en **Kotlin** que permite al usuario reproducir sonidos, escuchar música y grabar audio utilizando diferentes APIs de Android.

La app implementa funcionalidades multimedia utilizando **SoundPool, MediaPlayer y MediaRecorder**, permitiendo la reproducción de efectos de sonido, música y la grabación de voz.

Esta aplicación cumple con los requisitos de manejo de audio en Android, interacción con el usuario y control de recursos multimedia.

---

# 🎯 Objetivo de la Aplicación

Permitir al usuario:

- Reproducir efectos de sonido con SoundPool.
- Reproducir música con MediaPlayer.
- Grabar audio utilizando el micrófono.
- Visualizar eventos y acciones en tiempo real mediante logs.

---

# ⚙️ Tecnologías Utilizadas

- **Kotlin**
- **Android Studio**
- **XML para diseño de interfaces**
- **SoundPool**
- **MediaPlayer**
- **MediaRecorder**
- **CardView**
- **ScrollView**
- **Toast**
- **findViewById**

---

# 🧩 Componentes Utilizados

La aplicación utiliza distintos componentes multimedia de Android:

### 🔊 SoundPool
Permite reproducir efectos de sonido cortos de manera eficiente.

### 🎵 MediaPlayer
Se utiliza para reproducir archivos de audio más largos, como música.

### 🎙️ MediaRecorder
Permite grabar audio desde el micrófono del dispositivo.

### 🪵 TextView (Log)
Muestra eventos en tiempo real dentro de la aplicación.

### 🎴 CardView
Se utiliza para mejorar el diseño visual del área de logs.

### 🔘 Button
Permite al usuario interactuar con las funcionalidades de audio.

---

# 🧠 Funcionalidades Implementadas

## 1️⃣ Reproducción de sonidos (SoundPool)

El usuario puede reproducir efectos de sonido presionando botones.

---

## 2️⃣ Reproducción de música (MediaPlayer)

Permite iniciar y detener la reproducción de una canción.

Ejemplo:

> "Reproduciendo Audio con MediaPlayer"

---

## 3️⃣ Grabación de audio (MediaRecorder)

El usuario puede grabar audio desde el micrófono del dispositivo.

---

## 4️⃣ Registro de eventos (Logs)

La aplicación muestra mensajes en tiempo real sobre las acciones realizadas.

Ejemplo:

> "Grabando conversación"  
> "Fin Reproducción MediaPlayer"

---

## 5️⃣ Control de botones

Mientras se reproduce o graba audio, se deshabilitan otros botones para evitar conflictos.

---

# 🎨 Diseño de Interfaz

La aplicación utiliza:

- **LinearLayout** con orientación vertical
- **TextView** como título principal
- **Button** para acciones
- **CardView** para mostrar logs
- **ScrollView** para desplazamiento

Esto permite una interfaz:

- Moderna
- Clara
- Intuitiva

---

# 📁 Estructura del Proyecto

```text
app
├── java/com/example/appexample
│   └── MainActivity.kt
│
├── res
│   ├── layout
│   │   └── activity_main.xml
│   │
│   ├── raw
│   │   ├── bigben.mp3
│   │   ├── alarma.mp3
│   │   └── beethoven_para_elisa.mp3
│   │
│   └── values
│       └── strings.xml
│
└── gradle
├── build.gradle
├── build.gradle.kts
└── settings.gradle.kts
```
# 👨‍💻 Desarrolladores

- **Jonás García Corniel – 1-18-4259**
- **Adonis Rodríguez – 117-4399**
- **Euris Joel Acosta – 1-19-3584**
- **Gabriel Hernández Galván – 2-21-3988**
- **Erik Miguel Gil Cruz – 1-22-5249**

---

## 🚀 Cómo Ejecutar el Proyecto

1. Clonar el repositorio

```bash
git clone https://github.com/jonas-gc22/AppExample.git
```

2. Abrir **Android Studio**

3. Seleccionar **Open Project**

4. Buscar la carpeta del proyecto clonado

5. Esperar la sincronización de **Gradle**

6. Ejecutar la aplicación en:

- Un **emulador de Android**
- O un **dispositivo físico**

---

# 📌 Conclusión

La aplicación Reproductor y Grabador Multimedia cumple con los requisitos de desarrollo Android al implementar:
•	Reproducción de audio con SoundPool
•	Reproducción de música con MediaPlayer
•	Grabación de audio con MediaRecorder
•	Manejo de eventos en tiempo real
•	Interfaz mejorada con CardView

Esta app demuestra el uso de componentes multimedia fundamentales en Android, proporcionando una base sólida para futuras mejoras como streaming, almacenamiento en la nube o integración con servicios avanzados 
