package com.rnaudiotranscoder;

import java.util.*;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

public final class RNAudioTranscoderPackage implements ReactPackage {

	@Override
	public final List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
		List<NativeModule> modules = new ArrayList<>();
		modules.add(new RNAudioTranscoder(reactContext));
		return modules;
	}

	// Deprecated in RN 0.47
	public final List<Class<? extends JavaScriptModule>> createJSModules() {
		return Collections.emptyList();
	}

	@Override
	public final List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
	return Arrays.<ViewManager>asList();
	}
}