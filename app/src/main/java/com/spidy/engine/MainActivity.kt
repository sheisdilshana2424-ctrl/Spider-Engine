package com.spidy.engine

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.spidy.engine.databinding.ActivityMainBinding
import top.niunaijun.blackbox.BlackBoxCore
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var selectedApkUri: Uri? = null
    private var selectedObbUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Initialize the Engine on Startup
        initVirtualEngine()

        binding.addGameButton.setOnClickListener {
            pickApkFile()
        }
    }

    private fun initVirtualEngine() {
        try {
            // Initialize BlackBox Core
            BlackBoxCore.get().doAttachBaseContext(baseContext)
            // Determine if we are running in the virtual process or main process
            if (BlackBoxCore.get().isVirtualProcess) {
                // If we are inside the VM, don't re-initialize UI
                return
            }
        } catch (e: Exception) {
            binding.statusTextView.text = "Engine Error: ${e.message}"
        }
    }

    // --- File Pickers ---
    private val apkPicker = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            selectedApkUri = result.data?.data
            binding.statusTextView.text = "APK Selected. Now select OBB."
            pickObbFile()
        }
    }

    private val obbPicker = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            selectedObbUri = result.data?.data
            installGameToEngine()
        }
    }

    private fun pickApkFile() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            type = "application/vnd.android.package-archive"
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        apkPicker.launch(intent)
    }

    private fun pickObbFile() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            type = "*/*" // Allow selecting OBB/Zip
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        obbPicker.launch(intent)
    }

    // --- The Nostalgia Installation Logic ---
    private fun installGameToEngine() {
        val apkUri = selectedApkUri ?: return
        val obbUri = selectedObbUri ?: return

        binding.progressBar.visibility = View.VISIBLE
        binding.statusTextView.text = "Virtualizing Android Environment..."
        binding.addGameButton.isEnabled = false

        Thread {
            try {
                // 1. Install APK into Virtual Engine
                val installResult = BlackBoxCore.get().installPackageAsUser(
                    apkUri, 
                    0 // User ID
                )

                if (!installResult.success) {
                    runOnUiThread { 
                        Toast.makeText(this, "Failed to install APK inside Engine", Toast.LENGTH_LONG).show()
                        resetUI()
                    }
                    return@Thread
                }
                
                val packageName = installResult.packageName

                // 2. Handle OBB
                runOnUiThread { binding.statusTextView.text = "Importing OBB Data..." }
                
                val virtualEnvPath = BlackBoxCore.get().getExternalStorageDir(0) // Get virtual SD card
                val obbDir = File(virtualEnvPath, "Android/obb/$packageName")
                if (!obbDir.exists()) obbDir.mkdirs()
                
                val obbName = "main.1.${packageName}.obb" // Simplified naming
                val destFile = File(obbDir, obbName)
                
                contentResolver.openInputStream(obbUri)?.use { input ->
                    destFile.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }

                // 3. Launch the Game
                runOnUiThread {
                    binding.statusTextView.text = "Launching Nostalgia Mode..."
                    binding.progressBar.visibility = View.GONE
                    
                    // Launch the app inside the container
                    BlackBoxCore.get().launchApk(packageName, 0)
                }

            } catch (e: Exception) {
                runOnUiThread {
                    binding.statusTextView.text = "Error: ${e.message}"
                    resetUI()
                }
            }
        }.start()
    }

    private fun resetUI() {
        binding.progressBar.visibility = View.GONE
        binding.addGameButton.isEnabled = true
    }
}
