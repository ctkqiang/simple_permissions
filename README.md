# Simple Permissions

A new flutter plugin for checking and requesting permissions on iOs and Android.

## Getting Started

Make sure you add the needed permissions to your Android Manifest  [Permission](https://developer.android.com/reference/android/Manifest.permission.html)
and Info.plist.

```xml
<uses-permission android:name="android.permission.RECORD_AUDIO" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

## API
### List of currently available permissions

```dart
enum Permission {
  AcceptHandover,
  AccessBackgroundLocation,
  AccessCheckinProperties,
  AccessFineLocation,
  AccessCoarseLocation,
  AccessLocationExtraCommands,
  AlwaysLocation,
  AccessMediaLocation,
  AccessNetworkState,
  AccessNotificationPolicy,
  AccessWifiState,
  AccountManager,
  ActivityRecognition,
  AddVoiceMail,
  AnswerPhoneCalls,
  AccessMotionSensor,
  BatteryStats,
  BindAccessibilityService,
  BindAppWidget,
  BindAutoFillService,
  BindCallRedirectionService,
  BindCarrierMessagingClientService,
  BindCarrierMessagingService,
  BindCarrierServices,
  BindChooserTargetService,
  BindConditionProviderService,
  BindControls,
  BindDeviceAdmin,
  BindDreamService,
  BindInCallService,
  BindInputMethod,
  BindNfcService,
  BindNotificationListenerService,
  BindPrintService,
  BindQuickAccessWalletService,
  BindQuickSettingsTile,
  BindRemoteViews,
  BindScreeningService,
  BindTelecomConnectionService,
  BindTextService,
  BindTvInput,
  BindVoiceInteraction,
  BindVRListenerService,
  Bluetooth,
  BlutoothAdmin,
  BluetoothPrivileged,
  BodySensors,
  BroadcastSMS,
  CallPhone,
  CallPrivileged,
  Camera,
  CaptureAudioOutput,
  ChangeComponentEnabledState,
  ChangeNetworkState,
  ClearAppCache,
  ControlLocationUpdates,
  ForegroundService,
  GetAccountsPrivileged,
  GlobalSearch,
  Internet,
  KillBackgroundProcesses,
  ManageDocuments,
  MediaContentControl,
  ModifyAudioSettings,
  NFC,
  NFCPreferedPaymentInfo,
  NFCTransactionEvent,
  PhotoLibrary,
  ReadCalendar,
  ReadCallLog,
  RecordAudio,
  ReadExternalStorage,
  ReadPhoneState,
  ReadPrecisePhoneState,
  ReadContacts,
  ReadSms,
  RequestCompanionRunInBackground,
  RequestCompanionUseDataInBackground,
  SendSMS,
  SetAlarm,
  SmsFinancialTransactions,
  UseBiomatric,
  UseFingeprint,
  UseSip,
  Vibrate,
  WriteContacts,
  WhenInUseLocation,
  WriteExternalStorage
}
```

```dart
/// Permissions status enum (iOS)
enum PermissionStatus { notDetermined, restricted, denied, authorized }
```

### Methods
```dart
  /// Check a [permission] and return a [Future] with the result
  static Future<bool> checkPermission(Permission permission);

  /// Request a [permission] and return a [Future] with the result
  static Future<PermissionStatus> requestPermission(Permission permission);

  /// Open app settings on Android and iOS
  static Future<bool> openSettings();
  
  /// Get iOs permission status 
  static Future<PermissionStatus> getPermissionStatus(Permission permission)
```




<b>$Support</b><br/>
Email: [johnmelodyme@yandex.com](mailto:johnmelodyme@yandex.com )

<b>Donate</b>

### Wechat: 
![](https://raw.githubusercontent.com/johnmelodyme/ShortestPathAlgorithm/master/assets/wechat.png)

<br />

### Bitcoin:
![](https://github.com/johnmelodyme/ShortestPathAlgorithm/raw/master/assets/btc.jpg)
