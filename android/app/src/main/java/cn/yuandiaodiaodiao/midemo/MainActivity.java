package cn.yuandiaodiaodiao.midemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.widget.Button;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

public class MainActivity extends AppCompatActivity {
    public Button button1;
    public BridgeWebView webView1;
    public EditText edittext;
    public String url="http://yuandiaodiaodiao.cn:8888";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button);

        webView1 = (BridgeWebView) findViewById(R.id.webView);

        edittext = (EditText) findViewById(R.id.textView);
        edittext.setText(url);

        webView1.registerHandler("submitFromWeb", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Toast.makeText(getApplicationContext(), "msg is " + data, Toast.LENGTH_SHORT).show();
                function.onCallBack("submitFromWeb exe, response data from Java");
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //刷新网页
                url = edittext.getText().toString();
                webView1.loadUrl(url);
                Toast.makeText(getApplicationContext(), "reload", Toast.LENGTH_SHORT).show();
            }
        });

        webView1.loadUrl(url); //加载默认链接
    }

    @Override
    protected void onResume() {
        //恢复时重加载
        super.onResume();
        webView1.loadUrl(url);
    }
}
