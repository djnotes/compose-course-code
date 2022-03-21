package ninja.codezombie.mynativesourceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener{
            val deepIntent = Intent(
                Intent.ACTION_VIEW,
                "myapp://greeting/John".toUri()
            )

            startActivity(deepIntent)
        }
    }
}