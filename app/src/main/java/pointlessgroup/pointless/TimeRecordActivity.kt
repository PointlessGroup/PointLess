package pointlessgroup.pointless

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class TimeRecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_record)

        findViewById(R.id.btn_mainactivity_timerecord).setOnClickListener {
            (findViewById(R.id.timer) as TextView).text = "00:01"
        }

    }
}
