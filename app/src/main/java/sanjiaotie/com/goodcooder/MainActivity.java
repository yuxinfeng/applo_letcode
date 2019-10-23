package sanjiaotie.com.goodcooder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

// import com.demo.aidl.ABook;


public class MainActivity extends Activity  implements View.OnClickListener {

    private Button mBinderButton;
    private Button mAddButton;
    private ABook mIBookManager;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            //通过服务端onBind方法返回的binder对象得到IBookManager的实例，得到实例就可以调用它的方法了
            mIBookManager = ABook.Stub.asInterface(binder);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIBookManager = null;
        }
    };

    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if (mIBookManager == null) {
                return;
            }
            mIBookManager.asBinder().unlinkToDeath(deathRecipient, 0);
            mIBookManager = null;
            // 重新绑定binder
            Intent intentService = new Intent();
            intentService.setAction("sanjiaotie.com.goodcooder.aidldemo.ServerService");
            intentService.setPackage(getPackageName());
            intentService.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            bindService(intentService, mServiceConnection, BIND_AUTO_CREATE);
            Toast.makeText(getApplicationContext(),"绑定了服务",Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_main);
        mBinderButton = (Button) findViewById(R.id.bindService);
        mAddButton = (Button) findViewById(R.id.addBook);
        mBinderButton.setOnClickListener(this);
        mAddButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bindService:{
                Intent intentService = new Intent();
                intentService.setAction("sanjiaotie.com.goodcooder.aidldemo.ServerService");
                intentService.setPackage(getPackageName());
                intentService.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                this.bindService(intentService, mServiceConnection, BIND_AUTO_CREATE);
                Toast.makeText(getApplicationContext(),"绑定了服务",Toast.LENGTH_SHORT).show();
                try {
                    mIBookManager.asBinder().linkToDeath(deathRecipient, 0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.addBook:{
                if(mIBookManager!=null){
                    try {
                        mIBookManager.addBook(new Book(18, "新添加的书"));
                        Toast.makeText(getApplicationContext(),mIBookManager.getBookList().size()+"",Toast.LENGTH_SHORT).show();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mIBookManager!=null){
            unbindService(mServiceConnection);
        }
    }
}
