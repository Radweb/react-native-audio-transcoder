#import "RNAudioTranscoder.h"
#import <React/RCTBridgeModule.h>
#import <React/RCTUtils.h>
#import <React/RCTLog.h>
#import <AudioToolbox/AudioToolbox.h>
#import "FFmpegWrapper.h"

@implementation RNAudioTranscoder

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(
    transcode: (NSDictionary *) obj
    resolver:(RCTPromiseResolveBlock) resolve
    rejecter: (RCTPromiseRejectBlock) reject
) {
    FFmpegWrapper *ffmpeg = [FFmpegWrapper alloc];
    NSString *inputPath = obj[@"input"];
    NSString *outputPath = obj[@"output"];
    
    [ffmpeg convertInputPath inputPath: inputPath outputPath: outputPath];
    
    RCTLogInfo(@"Logging a thing");
    resolve(@"Yuppers");
}

@end

- void createMP3Format(AudioStreamBasicDescription *format)
{
    format->mFormatId = kAudioFormat
}

- void checkRequiredOptions()

//
//RCT_EXPORT_METHOD(saveImage:(NSDictionary *)obj
//                  resolver:(RCTPromiseResolveBlock)resolve
//                  rejecter:(RCTPromiseRejectBlock)reject)
//{
//    NSString *imagePath = obj[@"imagePath"];
//    NSString *imageName = obj[@"imageName"];
//    NSString *imageType = obj[@"imageType"];
//    NSString *directory = obj[@"directory"];
//    int width =  [obj[@"width"] intValue];
//    int height =  [obj[@"height"] intValue];
//
//    // Create NSURL from uri
//    NSURL *url = [[NSURL alloc] initWithString:imagePath];
//
//    PHFetchResult *result = [PHAsset fetchAssetsWithALAssetURLs:@[url] options:nil];
//    PHAsset *asset = result.firstObject;
//
//    if (asset) {
//        PHCachingImageManager *imageManager = [[PHCachingImageManager alloc] init];
//
//        PHImageRequestOptions *option = [PHImageRequestOptions new];
//        option.synchronous = YES;
//
//        // Request an image for the asset from the PHCachingImageManager.
//        [imageManager requestImageForAsset:asset targetSize:CGSizeMake(width, height) contentMode:PHImageContentModeAspectFit options:option
//                             resultHandler:^(UIImage *image, NSDictionary *info)
//         {
//             NSArray * paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
//             NSString * basePath = ([paths count] > 0) ? [paths objectAtIndex:0] : nil;
//             NSFileManager *fileManager = [NSFileManager defaultManager];
//             NSString *documentsPath = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) firstObject];
//             NSString *imagesPath = directory ? [documentsPath stringByAppendingPathComponent:directory] : basePath;
//             if (![fileManager fileExistsAtPath:imagesPath]) {
//                 [fileManager createDirectoryAtPath:imagesPath withIntermediateDirectories:NO attributes:nil error:nil];
//             }
//
//             NSData * binaryImageData;
//             if([imageType isEqualToString:@"png"]) {
//                 binaryImageData = UIImagePNGRepresentation(image);
//             } else {
//                 binaryImageData = UIImageJPEGRepresentation(image, 1.0);
//             }
//             NSString *imageNameWithExtension = [NSString stringWithFormat:@"%@.%@", imageName, imageType];
//             [binaryImageData writeToFile:[imagesPath stringByAppendingPathComponent:imageNameWithExtension] atomically:YES];
//
//             NSString * newPath = [imagesPath stringByAppendingPathComponent:imageNameWithExtension];
//             resolve(newPath);
//         }
//         ];
//    } else {
//        NSError *error = nil;
//        reject(@"Image not found", @"Error locating file", error);
//    }
//}
//@end
