package com.rnaudiotranscoder;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.database.Cursor;
import android.util.Log;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;

public class RNAudioTranscoder extends ReactContextBaseJavaModule {

	public RNAudioTranscoder(ReactApplicationContext reactContext) {
		super(reactContext);
	}

	@Override
	public String getName() {
		return "RNAudioTranscoder";
	}

	private WritableMap makeErrorPayload(Exception ex) {
		WritableMap error = Arguments.createMap();
		error.putString("message", ex.getMessage());
		return error;
	}

	@ReactMethod
	public void foo(String str, Callback callback) {
		Log.v("RNAudioTranscoder", str);
	}

}
