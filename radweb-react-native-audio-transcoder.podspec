Pod::Spec.new do |s|
  s.name         = "radweb-react-native-audio-transcoder"
  s.version      = "0.7.0"
  s.summary      = "Transcode audio format in react-native"
  s.description  = <<-DESC
    Transcode audio format in react-native
                   DESC

  s.homepage     = "https://github.com/Radweb/react-native-audio-transcoder"
  s.license      = "Apache-2.0"
  s.author             = "Radweb"
  s.source       = { :git => "https://github.com/Radweb/react-native-audio-transcoder.git", :tag => "#{s.version}" }
  s.source_files  = "RNAudioTranscoder/RNAudioTranscoder"
  s.framework = "AVFoundation"
  s.dependency "React"
  
  s.platform     = :ios, "8.0"
end
