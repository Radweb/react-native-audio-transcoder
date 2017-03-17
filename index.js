const { Platform, NativeModules } = require('react-native')
const RNAudioTranscoder = NativeModules.RNAudioTranscoder

const foo = () => {
	console.dir(RNAudioTranscoder)
}

const Interface = {
	foo,
	RNAudioTranscoder,
}

module.exports = Interface
