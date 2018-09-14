package com.example.sergey.codeforcesapplication.extension

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Date.format() = SimpleDateFormat("HH:mm, dd.MM.yyyy").format(this)!!