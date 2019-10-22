package sanjiaotie.com.goodcooder.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;


import java.util.ArrayList;
import java.util.List;

import sanjiaotie.com.goodcooder.ABook;
import sanjiaotie.com.goodcooder.Book;

/**
 * author：yuxinfeng on 2019-10-22 19:08
 * email：yuxinfeng@corp.netease.com
 */
public class ServerService extends Service {

    private List<Book> mBookList = new ArrayList<>();

    private ABook.Stub mbinder = new ABook.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void addBook(Book book) throws RemoteException {
            if(!mBookList.contains(book)) {
                mBookList.add(book);
            }
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }
    };


    public ServerService() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        for (int i = 1; i < 6; i++) {
            Book book = new Book();
            book.setBookName("book#" + i);
            book.setBookId(i);
            mBookList.add(book);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mbinder;
    }
}
