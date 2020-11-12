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

    case Permission.ModifyAudioSettings:
      res = "MODIFY_AUDIO_SETTINGS";
      break;

    case Permission.AccessCheckinProperties:
      res = "ACCESS_CHECKIN_PROPERTIES";
      break;

    case Permission.AccessLocationExtraCommands:
      res = "ACCESS_LOCATION_EXTRA_COMMANDS";
      break;

    case Permission.AccessMediaLocation:
      res = "ACCESS_MEDIA_LOCATION";
      break;

    case Permission.AccessNetworkState:
      res = "ACCESS_NETWORK_STATE";
      break;

    case Permission.AccessNotificationPolicy:
      res = "ACCESS_NOTIFICATION_POLICY";
      break;

    case Permission.AccessWifiState:
      res = "ACCESS_WIFI_STATE";
      break;

    case Permission.AccountManager:
      res = "ACCOUNT_MANAGER";
      break;

    case Permission.ActivityRecognition:
      res = "ACTIVITY_RECOGNITION";
      break;

    case Permission.AddVoiceMail:
      res = "ADD_VOICEMAIL";
      break;

    case Permission.AnswerPhoneCalls:
      res = "ANSWER_PHONE_CALLS";
      break;

    case Permission.BatteryStats:
      res = "BATTERY_STATS";
      break;

    case Permission.BindAccessibilityService:
      res = "BIND_ACCESSIBILITY_SERVICE";
      break;

    case Permission.BindAppWidget:
      res = "BIND_APPWIDGET";
      break;

    case Permission.BindAutoFillService:
      res = "BIND_AUTOFILL_SERVICE";
      break;

    case Permission.BindCallRedirectionService:
      res = "BIND_CALL_REDIRECTION_SERVICE";
      break;

    case Permission.BindCarrierMessagingClientService:
      res = "BIND_CARRIER_MESSAGING_CLIENT_SERVICE";
      break;

    case Permission.BindCarrierMessagingService:
      res = "BIND_CARRIER_MESSAGING_SERVICE";
      break;

    case Permission.BindCarrierServices:
      res = "BIND_CARRIER_SERVICES";
      break;

    case Permission.BindChooserTargetService:
      res = "BIND_CHOOSER_TARGET_SERVICE";
      break;

    case Permission.BindConditionProviderService:
      res = "BIND_CONDITION_PROVIDER_SERVICE";
      break;

    case Permission.BindControls:
      res = "BIND_CONTROLS";
      break;

    case Permission.BindDeviceAdmin:
      res = "BIND_DEVICE_ADMIN";
      break;

    case Permission.BindDreamService:
      res = "BIND_DREAM_SERVICE";
      break;

    case Permission.BindInCallService:
      res = "BIND_INCALL_SERVICE";
      break;

    case Permission.BindInputMethod:
      res = "BIND_INPUT_METHOD";
      break;

    case Permission.BindNfcService:
      res = "BIND_NFC_SERVICE";
      break;

    case Permission.BindNotificationListenerService:
      res = "BIND_NOTIFICATION_LISTENER_SERVICE";
      break;

    case Permission.BindPrintService:
      res = "BIND_PRINT_SERVICE";
      break;

    case Permission.BindQuickAccessWalletService:
      res = "BIND_QUICK_ACCESS_WALLET_SERVICE";
      break;

    case Permission.BindQuickSettingsTile:
      res = "BIND_QUICK_SETTINGS_TILE";
      break;

    case Permission.BindRemoteViews:
      res = "BIND_REMOTEVIEWS";
      break;

    case Permission.BindScreeningService:
      res = "BIND_SCREENING_SERVICE";
      break;

    case Permission.BindTelecomConnectionService:
      res = "BIND_TELECOM_CONNECTION_SERVICE";
      break;

    case Permission.BindTextService:
      res = "BIND_TEXT_SERVICE";
      break;

    case Permission.BindTvInput:
      res = "BIND_TV_INPUT";
      break;

    case Permission.BindVoiceInteraction:
      res = "BIND_VOICE_INTERACTION";
      break;

    case Permission.BindVRListenerService:
      res = "BIND_VR_LISTENER_SERVICE";
      break;

    case Permission.Bluetooth:
      res = "BLUETOOTH";
      break;

    case Permission.BlutoothAdmin:
      res = "BLUETOOTH_ADMIN";
      break;

    case Permission.BluetoothPrivileged:
      res = "BLUETOOTH_PRIVILEGED";
      break;

    case Permission.BodySensors:
      res = "BODY_SENSORS";
      break;

    case Permission.BroadcastSMS:
      res = "BROADCAST_SMS";
      break;

    case Permission.CallPrivileged:
      res = "CALL_PRIVILEGED";
      break;

    case Permission.CaptureAudioOutput:
      res = "CAPTURE_AUDIO_OUTPUT";
      break;

    case Permission.ChangeComponentEnabledState:
      res = "CHANGE_COMPONENT_ENABLED_STATE";
      break;

    case Permission.ChangeNetworkState:
      res = "CHANGE_NETWORK_STATE";
      break;

    case Permission.ClearAppCache:
      res = "CLEAR_APP_CACHE";
      break;

    case Permission.ControlLocationUpdates:
      res = "CONTROL_LOCATION_UPDATES";
      break;

    case Permission.GetAccountsPrivileged:
      res = "GET_ACCOUNTS_PRIVILEGED";
      break;

    case Permission.GlobalSearch:
      res = "GLOBAL_SEARCH";
      break;

    case Permission.Internet:
      res = "INTERNET";
      break;

    case Permission.KillBackgroundProcesses:
      res = "KILL_BACKGROUND_PROCESSES";
      break;

    case Permission.ManageDocuments:
      res = "MANAGE_DOCUMENTS";
      break;

    case Permission.MediaContentControl:
      res = "MEDIA_CONTENT_CONTROL";
      break;

    case Permission.NFC:
      res = "NFC";
      break;

    case Permission.NFCPreferedPaymentInfo:
      res = "NFC_PREFERRED_PAYMENT_INFO";
      break;

    case Permission.NFCTransactionEvent:
      res = "NFC_TRANSACTION_EVENT";
      break;

    case Permission.ReadCalendar:
      res = "READ_CALENDAR";
      break;

    case Permission.ReadCallLog:
      res = "READ_CALL_LOG";
      break;

    case Permission.ReadPrecisePhoneState:
      res = "READ_PRECISE_PHONE_STATE";
      break;

    case Permission.RequestCompanionRunInBackground:
      res = "REQUEST_COMPANION_RUN_IN_BACKGROUND";
      break;

    case Permission.RequestCompanionUseDataInBackground:
      res = "REQUEST_COMPANION_USE_DATA_IN_BACKGROUND";
      break;

    case Permission.SetAlarm:
      res = "SET_ALARM";
      break;

    case Permission.SmsFinancialTransactions:
      res = "SMS_FINANCIAL_TRANSACTIONS";
      break;

    case Permission.UseBiomatric:
      res = "USE_BIOMETRIC";
      break;

    case Permission.UseFingeprint:
      res = "USE_FINGERPRINT";
      break;

    case Permission.UseSip:
      res = "USE_SIP";
      break;
  }
  return res;
}
