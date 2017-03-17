package com.rnaudiotranscoder;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public final class RNAudioTranscoder extends ReactContextBaseJavaModule {

	private final FFmpeg ffmpeg;

	public final String COMMAND_FORMAT = "-i %s -codec:a libmp3lame -qscale:a 2 %s";
	public final String TAG = "RNAudioTranscoder";

	public RNAudioTranscoder (final ReactApplicationContext context) {
		super(context);
		ffmpeg = FFmpeg.getInstance(context);
	}

	@Override
	public final String getName() {
		return "RNAudioTranscoder";
	}

	@ReactMethod
	public final void transcode(final ReadableMap options, final Promise promise) {
		final Optional<String> paramErrors = this.checkRequiredOptions(options);
		if (paramErrors.exists) {
			promise.reject(paramErrors.value);
		} else {
			try {
				ffmpeg.loadBinary(new LoadBinaryResponseHandler() {
					@Override
					public void onFailure() {
						Log.e(TAG, "Failed to load ffmpeg");
						promise.reject("Failed to load ffmpeg binary");
					}
					@Override
					public void onSuccess() {
						final String input = options.getString("input");
						final String output = options.getString("output");
						final String[] command = createFFmpegCommand(input, output);
						try {
							ffmpeg.execute(command, new ExecuteBinaryResponseHandler() {
								@Override
								public void onFailure(String s) {
									promise.reject(s);
								}

								@Override
								public void onSuccess(String s) {
									promise.resolve(makeMessagePayload(s));
								}

								@Override
								public void onProgress(String s) {
									Log.d(TAG, "PROGRESS");
									Log.d(TAG, s);
								}

								@Override
								public void onStart() {
									Log.d(TAG, "Started command : ffmpeg " + command);
								}

								@Override
								public void onFinish() {
									Log.d(TAG, "Finished command : ffmpeg "+command);
								}
							});
						} catch (FFmpegCommandAlreadyRunningException e) {
							promise.reject(e.getMessage());
						}
					}
				});
			} catch (FFmpegNotSupportedException e) {
				Log.e(TAG, "FFMPEG NOT SUPPORTED");
				promise.reject(e.getMessage());
			}
		}
	}

	private final ReadableMap makeMessagePayload(final String message) {
		final WritableMap payload = Arguments.createMap();
		payload.putString("message", message);
		return payload;
	}

	private final String[] createFFmpegCommand(final String input, final String output) {
		return String.format(COMMAND_FORMAT, input, output).split(" ");
	}

	private final Optional<String> checkRequiredOptions(final ReadableMap options) {
		if (!options.hasKey("input")) return Optional.of("Missing required parameter 'input'");
		if (!options.hasKey("output")) return Optional.of("Missing required parameter 'output'");

		return Optional.empty();
	}
}
