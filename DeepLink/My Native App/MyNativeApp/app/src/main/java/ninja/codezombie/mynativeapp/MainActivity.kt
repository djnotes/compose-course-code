package ninja.codezombie.mynativeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.button).setOnClickListener {
            val text = findViewById<EditText>(R.id.editText).text.toString()

            val deepIntent = Intent(
                Intent.ACTION_VIEW,
                "myapp://greeting/$text".toUri()
            )

            startActivity(deepIntent)
        }

    }
}