const { NativeModules } = require('react-native')
const RNAudioTranscoder = NativeModules.RNAudioTranscoder

/**
 * Attempt to transcode the audio file at the specified input path to the one at the specified output path
 * Return value indicates success.
 * @param input Path to the input file
 * @param output Path ot the output file. NOTE: Currently this must be an mp3 file
 * @param log Optionally catch and log errors, returning false ot indicate failure. Defaults to false, where thrown
 *            errors in native code will propagate
 * @returns {Promise.<boolean>}
 */
async function transcode(input, output, log = false) {
	if (log) {
		try {
			await RNAudioTranscoder.transcode({ input, output })
		} catch (e) {
			console.error(e.message)
			return false
		}
		return true
	}

	await RNAudioTranscoder.transcode({ input, output })
	return true
}

module.exports = { transcode }
