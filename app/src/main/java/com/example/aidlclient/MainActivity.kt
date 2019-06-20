package com.example.aidlclient

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.aidlservices.IMyAidlInterface
import com.example.aidlservices.Num

class MainActivity : AppCompatActivity() {

    private var myAidlInterface : IMyAidlInterface? = null


    var connection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

            Toast.makeText(this@MainActivity, "解绑服务成功", Toast.LENGTH_LONG).show()
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {

            myAidlInterface = IMyAidlInterface.Stub.asInterface(service)
            Toast.makeText(this@MainActivity, "服务绑定成功", Toast.LENGTH_LONG).show()


        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         *
         * val service = Intent()
         * service.action = "com.example.aidlservices"
         * bindService(service, this, BIND_AUTO_CREATE)
         * android5.0以上不支持隐式调用服务
         */
        //
        val intent = Intent()
        intent.action = "com.example.aidlservices"
//        intent.component = ComponentName("com.example.aidlservices","com.example.aidlservices.MyService")
        val eintent = Intent(createExplicitFromImplicitIntent(this, intent))
        bindService(eintent, connection, Context.BIND_AUTO_CREATE)
//        bindService(intent, connection, Context.BIND_AUTO_CREATE)
        val tv3 = findViewById<TextView>(R.id.textView3)
        val et2 = findViewById<EditText>(R.id.editText2)
        val et3 = findViewById<EditText>(R.id.editText3)
        findViewById<Button>(R.id.button).setOnClickListener {
//            tv3.text = myAidlInterface?.getSum(et2.text.toString().toInt() ,et3.text.toString().toInt()).toString()
            tv3.text = myAidlInterface?.getSumInNum(Num(et2.text.toString().toInt() ,et3.text.toString().toInt())).toString()
        }
    }


    private fun createExplicitFromImplicitIntent(context : Context, implicitIntent :Intent ):Intent?{
        val pm = context.packageManager
        val resolveInfo = pm.queryIntentServices(implicitIntent, 0)

        if (resolveInfo == null || resolveInfo.size != 1) {
            return null
        }

        val serviceInfo = resolveInfo[0]
        val packageName = serviceInfo.serviceInfo.packageName
        val className = serviceInfo.serviceInfo.name
        val component = ComponentName(packageName, className)

        val explicitIntent = Intent(implicitIntent)

        explicitIntent.setComponent(component)

        return explicitIntent
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)

    }

}
