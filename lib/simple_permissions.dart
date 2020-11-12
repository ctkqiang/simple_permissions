import 'dart:async';
import 'package:flutter/services.dart';

class SimplePermissions {
  static const MethodChannel _channel =
      const MethodChannel('simple_permissions');

  static Future<String> get platformVersion async {
    final String platform = await _channel.invokeMethod('getPlatformVersion');
    return platform;
  }

  /// Check a [permission] and return a [Future] with the result
  static Future<bool> checkPermission(Permission permission) async {
    final bool isGranted = await _channel.invokeMethod(
        "checkPermission", {"permission": getPermissionString(permission)});
    return isGranted;
  }

  /// Request a [permission] and return a [Future] with the result
  static Future<PermissionStatus> requestPermission(
      Permission permission) async {
    final status = await _channel.invokeMethod(
        "requestPermission", {"permission": getPermissionString(permission)});

    if (status is int) {
      return intToPermissionStatus(status);
    } else {
      if (status is bool) {
        return (status ? PermissionStatus.authorized : PermissionStatus.denied);
      } else {
        return PermissionStatus.notDetermined;
      }
    }
  }

  /// Open app settings on Android and iOs
  static Future<bool> openSettings() async {
    final bool isOpen = await _channel.invokeMethod("openSettings");

    return isOpen;
  }

  static Future<PermissionStatus> getPermissionStatus(
      Permission permission) async {
    final int status = await _channel.invokeMethod(
        "getPermissionStatus", {"permission": getPermissionString(permission)});
    return intToPermissionStatus(status);
  }

  static PermissionStatus intToPermissionStatus(int status) {
    switch (status) {
      case 0:
        return PermissionStatus.notDetermined;

      case 1:
        return PermissionStatus.restricted;

      case 2:
        return PermissionStatus.denied;

      case 3:
        return PermissionStatus.authorized;

      case 4:
        return PermissionStatus.deniedNeverAsk;

      default:
        return PermissionStatus.notDetermined;
    }
  }
}

/// Enum of all available [Permission]
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

/// Permissions status enum (iOs: notDetermined, restricted, denied, authorized, deniedNeverAsk)
/// (Android: denied, authorized, deniedNeverAsk)
enum PermissionStatus {
  notDetermined,
  restricted,
  denied,
  authorized,
  deniedNeverAsk /* android */
}

String getPermissionString(Permission permission) {
  String res;
  switch (permission) {
    case Permission.AcceptHandover:
      res = "ACCEPT_HANDOVER";
      break;

    case Permission.AccessBackgroundLocation:
      res = "ACCESS_BACKGROUND_LOCATION";
      break;

    case Permission.CallPhone:
      res = "CALL_PHONE";
      break;

    case Permission.Camera:
      res = "CAMERA";
      break;

    case Permission.PhotoLibrary:
      res = "PHOTO_LIBRARY";
      break;

    case Permission.RecordAudio:
      res = "RECORD_AUDIO";
      break;

    case Permission.WriteExternalStorage:
      res = "WRITE_EXTERNAL_STORAGE";
      break;

    case Permission.ReadExternalStorage:
      res = "READ_EXTERNAL_STORAGE";
      break;

    case Permission.ReadPhoneState:
      res = "READ_PHONE_STATE";
      break;

    case Permission.AccessFineLocation:
      res = "ACCESS_FINE_LOCATION";
      break;

    case Permission.AccessCoarseLocation:
      res = "ACCESS_COARSE_LOCATION";
      break;

    case Permission.WhenInUseLocation:
      res = "WHEN_IN_USE_LOCATION";
      break;

    case Permission.AlwaysLocation:
      res = "ALWAYS_LOCATION";
      break;

    case Permission.ReadContacts:
      res = "READ_CONTACTS";
      break;

    case Permission.SendSMS:
      res = "SEND_SMS";
      break;

    case Permission.ReadSms:
      res = "READ_SMS";
      break;

    case Permission.Vibrate:
      res = "VIBRATE";
      break;

    case Permission.WriteContacts:
      res = "WRITE_CONTACTS";
      break;

    case Permission.AccessMotionSensor:
      res = "MOTION_SENSOR";
      break;

    case Permission.ForegroundService:
      res = "FOREGROUND_SERVICE";
      break;

    case Permission.ModifyAudioSetting:
      res = "MODIFY_AUDIO_SETTINGS";
      break;
  }
  return res;
}
