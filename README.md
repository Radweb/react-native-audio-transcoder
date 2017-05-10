# Setup

- Install this repository via yarn
- Run `react-native link react-native-audio-transcoder`

## Android

No additional steps required

## iOS

- Add the `AVMediaFoundation` library to your root react native project

# Interface

```javascript
import { transcode } from 'react-native-audio-transcoder'

const myFilePath = getFilePath()
const myNewFile = myFilePath.replace('aac', 'mp3')

transcode(myFilePath, myNewFile)
    .then(() => console.log('Party!'))
```