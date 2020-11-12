package com.ethras.simplepermissions;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * @Referenece : https://developer.android.com/reference/android/Manifest.permission
 */
public class SimplePermissionsPlugin
implements
MethodCallHandler,
PluginRegistry.RequestPermissionsResultListener {

  private Registrar registrar;
  private Result result;

  private static String MOTION_SENSOR = "MOTION_SENSOR";

  /**
   * Plugin registration.
   */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(
    registrar.messenger(), "simple_permissions");
    SimplePermissionsPlugin simplePermissionsPlugin = new SimplePermissionsPlugin(
    registrar);
    channel.setMethodCallHandler(simplePermissionsPlugin);
    registrar.addRequestPermissionsResultListener(simplePermissionsPlugin);
  }

  private SimplePermissionsPlugin(Registrar registrar) {
    this.registrar = registrar;
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    String method = call.method;
    String permission;
    switch (method) {
    case "getPlatformVersion":
      result.success("Android " + android.os.Build.VERSION.RELEASE);
      break;
    case "getPermissionStatus":
      int value;

      permission = call.argument("permission");

      if (MOTION_SENSOR.equalsIgnoreCase(permission)) {
        result.success(3);
        break;
      }

      if (checkPermission(permission)) {
        value = 3;
      } else {
        value = 2;
      }

      result.success(value);
      break;
    case "checkPermission":
      permission = call.argument("permission");

      if (MOTION_SENSOR.equalsIgnoreCase(permission)) {
        result.success(true);
        break;
      }

      result.success(checkPermission(permission));
      break;
    case "requestPermission":
      permission = call.argument("permission");

      if (MOTION_SENSOR.equalsIgnoreCase(permission)) {
        result.success(3);
        break;
      }

      this.result = result;

      requestPermission(permission);

      break;
    case "openSettings":
      openSettings();
      result.success(true);
      break;
    default:
      result.notImplemented();
      break;
    }
  }

  private void openSettings() {
    Activity activity = registrar.activity();
    Intent intent = new Intent(
    Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + activity.getPackageName()));
    intent.addCategory(Intent.CATEGORY_DEFAULT);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    activity.startActivity(intent);
  }

  private String getManifestPermission(String permission) {
    // * More Permission Will Be implemented;
    String res;
    switch (permission) {
      //  Allows a calling app to continue a call which was started in another app.
    case "ACCEPT_HANDOVER":
      res = Manifest.permission.ACCEPT_HANDOVER;
      break;

      // Allows an app to access location in the background.
    case "ACCESS_BACKGROUND_LOCATION":
      res = Manifest.permission.ACCESS_BACKGROUND_LOCATION;
      break;

      // Allows read/write access to the "properties" table in the
      // checkin database, to change values that get uploaded
    case "ACCESS_CHECKIN_PROPERTIES":
      res = Manifest.permission.ACCESS_CHECKIN_PROPERTIES;
      break;

      // Allows an app to access precise location.
    case "ACCESS_FINE_LOCATION":
      res = Manifest.permission.ACCESS_FINE_LOCATION;
      break;

      // Allows an app to access approximate location.
    case "ACCESS_COARSE_LOCATION":
      res = Manifest.permission.ACCESS_COARSE_LOCATION;
      break;

      // Allows an application to access extra location provider commands.
    case "ACCESS_LOCATION_EXTRA_COMMANDS":
      res = Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS;
      break;
      // Allow Always Location
    case "ALWAYS_LOCATION":
      res = Manifest.permission.ACCESS_FINE_LOCATION;
      break;

      // Allows an application to access any geographic locations persisted
      // in the user's shared collection.
    case "ACCESS_MEDIA_LOCATION":
      res = Manifest.permission.ACCESS_MEDIA_LOCATION;
      break;

      // Allows applications to access information about networks.
    case "ACCESS_NETWORK_STATE":
      res = Manifest.permission.ACCESS_NETWORK_STATE;
      break;

      // Marker permission for applications that wish to access notification policy.
    case "ACCESS_NOTIFICATION_POLICY":
      res = Manifest.permission.ACCESS_NOTIFICATION_POLICY;
      break;

      // Allows applications to access information about Wi-Fi networks.
    case "ACCESS_WIFI_STATE":
      res = Manifest.permission.ACCESS_WIFI_STATE;
      break;

      // Allows applications to call into AccountAuthenticators
    case "ACCOUNT_MANAGER":
      res = Manifest.permission.ACCOUNT_MANAGER;
      break;

      // Allows an application to recognize physical activity.
    case "ACTIVITY_RECOGNITION":
      res = Manifest.permission.ACTIVITY_RECOGNITION;
      break;

      // Allows an application to add voicemails into the system.
    case "ADD_VOICEMAIL":
      res = Manifest.permission.ADD_VOICEMAIL;
      break;

      // Allows the app to answer an incoming phone call.
    case "ANSWER_PHONE_CALLS":
      res = Manifest.permission.ANSWER_PHONE_CALLS;
      break;

      // Allows an application to collect battery statistics
      // Protection level: signature|privileged|development
    case "BATTERY_STATS":
      res = Manifest.permission.BATTERY_STATS;
      break;

      // Must be required by an AccessibilityService, to ensure that
      // only the system can bind to it.
    case "BIND_ACCESSIBILITY_SERVICE":
      res = Manifest.permission.BIND_ACCESSIBILITY_SERVICE;
      break;

      // Allows an application to tell the AppWidget service which application
      // can access AppWidget's data.
    case "BIND_APPWIDGET":
      res = Manifest.permission.BIND_APPWIDGET;
      break;

      // Must be required by a AutofillService, to ensure that only the system can bind to it.
    case "BIND_AUTOFILL_SERVICE":
      res = Manifest.permission.BIND_AUTOFILL_SERVICE;
      break;

      // Must be required by a CallRedirectionService, to ensure that only the system can bind to it.
    case "BIND_CALL_REDIRECTION_SERVICE":
      res = Manifest.permission.BIND_CALL_REDIRECTION_SERVICE;
      break;

      // A subclass of CarrierMessagingClientService must be protected with this permission.
    case "BIND_CARRIER_MESSAGING_CLIENT_SERVICE":
      res = Manifest.permission.BIND_CARRIER_MESSAGING_CLIENT_SERVICE;
      break;

      // This constant was deprecated in API level 23. Use BIND_CARRIER_SERVICES instead
    case "BIND_CARRIER_MESSAGING_SERVICE":
      res = Manifest.permission.BIND_CARRIER_MESSAGING_SERVICE;
      break;

      // The system process that is allowed to bind to services in carrier apps will have this permission.
    case "BIND_CARRIER_SERVICES":
      res = Manifest.permission.BIND_CARRIER_SERVICES;
      break;

      // This constant was deprecated in API level 30. For publishing direct share targets,
      // please follow the instructions in
      // https://developer.android.com/training/sharing/receive.html#providing-direct-share-targets
      // instead.
    case "BIND_CHOOSER_TARGET_SERVICE":
      res = Manifest.permission.BIND_CHOOSER_TARGET_SERVICE;
      break;

      // Must be required by a ConditionProviderService, to ensure that only the system can bind to it.
    case "BIND_CONDITION_PROVIDER_SERVICE":
      res = Manifest.permission.BIND_CONDITION_PROVIDER_SERVICE;
      break;

      // Allows SystemUI to request third party controls.
    case "BIND_CONTROLS":
      res = Manifest.permission.BIND_CONTROLS;
      break;

      // Must be required by device administration receiver, to ensure that only the system can interact with it.
    case "BIND_DEVICE_ADMIN":
      res = Manifest.permission.BIND_DEVICE_ADMIN;
      break;

      // Must be required by an DreamService, to ensure that only the system can bind to it.
    case "BIND_DREAM_SERVICE":
      res = Manifest.permission.BIND_DREAM_SERVICE;
      break;

      // Must be required by a InCallService, to ensure that only the system can bind to it.
    case "BIND_INCALL_SERVICE":
      res = Manifest.permission.BIND_INCALL_SERVICE;
      break;

      // Must be required by an InputMethodService, to ensure that only the system can bind to it.
    case "BIND_INPUT_METHOD":
      res = Manifest.permission.BIND_INPUT_METHOD;
      break;

      // Must be required by a HostApduService or OffHostApduService to
      // ensure that only the system can bind to it.
    case "BIND_NFC_SERVICE":
      res = Manifest.permission.BIND_NFC_SERVICE;
      break;

      // Must be required by an NotificationListenerService, to ensure that only the system can bind to it.
    case "BIND_NOTIFICATION_LISTENER_SERVICE":
      res = Manifest.permission.BIND_NOTIFICATION_LISTENER_SERVICE;
      break;

      // Must be required by a PrintService, to ensure that only the system can bind to it.
    case "BIND_PRINT_SERVICE":
      res = Manifest.permission.BIND_PRINT_SERVICE;
      break;

      // Must be required by a QuickAccessWalletService to ensure that only the system can bind to it.
    case "BIND_QUICK_ACCESS_WALLET_SERVICE":
      res = Manifest.permission.BIND_QUICK_ACCESS_WALLET_SERVICE;
      break;

      // Allows an application to bind to third party quick settings tiles.
    case "BIND_QUICK_SETTINGS_TILE":
      res = Manifest.permission.BIND_QUICK_SETTINGS_TILE;
      break;

      // Must be required by a RemoteViewsService, to ensure that only the system can bind to it.
    case "BIND_REMOTEVIEWS":
      res = Manifest.permission.BIND_REMOTEVIEWS;
      break;

      // Must be required by a CallScreeningService, to ensure that only the system can bind to it.
    case "BIND_SCREENING_SERVICE":
      res = Manifest.permission.BIND_SCREENING_SERVICE;
      break;

      // Must be required by a ConnectionService, to ensure that only the system can bind to it.
    case "BIND_TELECOM_CONNECTION_SERVICE":
      res = Manifest.permission.BIND_TELECOM_CONNECTION_SERVICE;
      break;

      // Must be required by a TextService (e.g. SpellCheckerService) to ensure that only the system can bind to it.
    case "BIND_TEXT_SERVICE":
      res = Manifest.permission.BIND_TEXT_SERVICE;
      break;

      // Must be required by a TvInputService to ensure that only the system can bind to it.
    case "BIND_TV_INPUT":
      res = Manifest.permission.BIND_TV_INPUT;
      break;

      // Must be required by a VoiceInteractionService, to ensure that only the system can bind to it.
    case "BIND_VOICE_INTERACTION":
      res = Manifest.permission.BIND_VOICE_INTERACTION;
      break;

      // Must be required by an VrListenerService, to ensure that only the system can bind to it.
    case "BIND_VR_LISTENER_SERVICE":
      res = Manifest.permission.BIND_VR_LISTENER_SERVICE;
      break;

      // Allows applications to connect to paired bluetooth devices.
    case "BLUETOOTH":
      res = Manifest.permission.BLUETOOTH;
      break;

      // Allows applications to discover and pair bluetooth devices.
    case "BLUETOOTH_ADMIN":
      res = Manifest.permission.BLUETOOTH_ADMIN;
      break;

      // Allows applications to pair bluetooth devices without user interaction,
      // and to allow or disallow phonebook access or message access.
    case "BLUETOOTH_PRIVILEGED":
      res = Manifest.permission.BLUETOOTH_PRIVILEGED;
      break;

      // Allows an application to access data from sensors that the user uses to
      // measure what is happening inside their body, such as heart rate.
    case "BODY_SENSORS":
      res = Manifest.permission.BODY_SENSORS;
      break;

      // Allows an application to broadcast an SMS receipt notification.
    case "BROADCAST_SMS":
      res = Manifest.permission.BROADCAST_SMS;
      break;

      // Allows an application to initiate a phone call without going through the Dialer
      // user interface for the user to confirm the call.
    case "CALL_PHONE":
      res = Manifest.permission.CALL_PHONE;
      break;

      // Allows an application to call any phone number,
      // including emergency numbers, without going
      // through the Dialer user interface for the user
      // to confirm the call being placed.
    case "CALL_PRIVILEGED":
      res = Manifest.permission.CALL_PRIVILEGED;
      break;

      // Required to be able to access the camera device.
    case "CAMERA":
      res = Manifest.permission.CAMERA;
      break;

      // Allows an application to capture audio output.
    case "CAPTURE_AUDIO_OUTPUT":
      res = Manifest.permission.CAPTURE_AUDIO_OUTPUT;
      break;

      // Allows an application to change whether an application component
      // (other than its own) is enabled or not.
    case "CHANGE_COMPONENT_ENABLED_STATE":
      res = Manifest.permission.CHANGE_COMPONENT_ENABLED_STATE;
      break;

      // Allows applications to change network connectivity state.
    case "CHANGE_NETWORK_STATE":
      res = Manifest.permission.CHANGE_NETWORK_STATE;
      break;

      // Allows an application to clear the caches of all installed applications on the device.
    case "CLEAR_APP_CACHE":
      res = Manifest.permission.CLEAR_APP_CACHE;
      break;
      // Allows enabling/disabling location update notifications from the radio.
    case "CONTROL_LOCATION_UPDATES":
      res = Manifest.permission.CONTROL_LOCATION_UPDATES;
      break;

      // Allows a regular application to use Service.startForeground.
    case "FOREGROUND_SERVICE":
      res = Manifest.permission.FOREGROUND_SERVICE;
      break;

      // Allows access to the list of accounts in the Accounts Service.
    case "GET_ACCOUNTS_PRIVILEGED":
      res = Manifest.permission.GET_ACCOUNTS_PRIVILEGED;
      break;

      // This permission can be used on content providers to allow the global search system to access their data.
    case "GLOBAL_SEARCH":
      res = Manifest.permission.GLOBAL_SEARCH;
      break;

      // Allows applications to open network sockets.
    case "INTERNET":
      res = Manifest.permission.INTERNET;
      break;
      // 	KILL_BACKGROUND_PROCESSES Allows an application to call ActivityManager.killBackgroundProcesses(String).
    case "KILL_BACKGROUND_PROCESSES":
      res = Manifest.permission.KILL_BACKGROUND_PROCESSES;
      break;

      // Allows an application to manage access to documents, usually as part of a document picker.
    case "MANAGE_DOCUMENTS":
      res = Manifest.permission.MANAGE_DOCUMENTS;
      break;
      // Allows an application to know what content is playing and control its playback.
    case "MEDIA_CONTENT_CONTROL":
      res = Manifest.permission.MEDIA_CONTENT_CONTROL;
      break;

      // Allows an application to modify global audio settings.
    case "MODIFY_AUDIO_SETTINGS":
      res = Manifest.permission.MODIFY_AUDIO_SETTINGS;
      break;

      // Allows applications to perform I/O operations over NFC.
    case "NFC":
      res = Manifest.permission.NFC;
      break;

      // Allows applications to receive NFC preferred payment service information.
    case "NFC_PREFERRED_PAYMENT_INFO":
      res = Manifest.permission.NFC_PREFERRED_PAYMENT_INFO;
      break;

      // Allows applications to receive NFC transaction events.
    case "NFC_TRANSACTION_EVENT":
      res = Manifest.permission.NFC_TRANSACTION_EVENT;
      break;

      // Allows an application to read the user's calendar data.
    case "READ_CALENDAR":
      res = Manifest.permission.READ_CALENDAR;
      break;

      // Allows an application to read the user's call log.
    case "READ_CALL_LOG":
      res = Manifest.permission.READ_CALL_LOG;
      break;
      // Allows an application to record audio.
    case "RECORD_AUDIO":
      res = Manifest.permission.RECORD_AUDIO;
      break;

      // Allows an application to read from external storage.
    case "READ_EXTERNAL_STORAGE":
      res = Manifest.permission.READ_EXTERNAL_STORAGE;
      break;
      
      // Allows read only access to phone state, including the current cellular 
      // network information, the status of any ongoing calls, 
      // and a list of any PhoneAccounts registered on the device.
    case "READ_PHONE_STATE":
      res = Manifest.permission.READ_PHONE_STATE;
      break;

      // Allows read only access to precise phone state.
    case "READ_PRECISE_PHONE_STATE":
      res = Manifest.permission.READ_PRECISE_PHONE_STATE;
      break;

      // Allows an application to read the user's contacts data
    case "READ_CONTACTS":
      res = Manifest.permission.READ_CONTACTS;
      break;

      // Allows an application to read SMS messages.
    case "READ_SMS":
      res = Manifest.permission.READ_SMS;
      break;

      //   Allows a companion app to run in the background.
    case "REQUEST_COMPANION_RUN_IN_BACKGROUND":
      res = Manifest.permission.REQUEST_COMPANION_RUN_IN_BACKGROUND;
      break;
      // Allows a companion app to use data in the background.
    case "REQUEST_COMPANION_USE_DATA_IN_BACKGROUND":
      res = Manifest.permission.REQUEST_COMPANION_USE_DATA_IN_BACKGROUND;
      break;
      // Allows an application to send SMS messages.
    case "SEND_SMS":
      res = Manifest.permission.SEND_SMS;
      break;

      // Allows an application to broadcast an Intent to set an alarm for the user.
    case "SET_ALARM":
      res = Manifest.permission.SET_ALARM;
      break;
      // Allows financial apps to read filtered sms messages.
    case "SMS_FINANCIAL_TRANSACTIONS":
      res = Manifest.permission.SMS_FINANCIAL_TRANSACTIONS;
      break;
      // Allows an app to use device supported biometric modalities.
    case "USE_BIOMETRIC":
      res = Manifest.permission.USE_BIOMETRIC;
      break;

      // This constant was deprecated in API level 28. Applications should request USE_BIOMETRIC instead
    case "USE_FINGERPRINT":
      res = Manifest.permission.USE_FINGERPRINT;
      break;

      // Allows an application to use SIP service.
    case "USE_SIP":
      res = Manifest.permission.USE_SIP;
      break;
      // Allows access to the vibrator.
    case "VIBRATE":
      res = Manifest.permission.VIBRATE;
      break;

      // Allows an application to write the user's contacts data.
    case "WRITE_CONTACTS":
      res = Manifest.permission.WRITE_CONTACTS;
      break;

    case "WHEN_IN_USE_LOCATION":
      res = Manifest.permission.ACCESS_FINE_LOCATION;
      break;
      // Allows an application to write to external storage.
    case "WRITE_EXTERNAL_STORAGE":
      res = Manifest.permission.WRITE_EXTERNAL_STORAGE;
      break;

    default:
      res = "ERROR";
      break;
    }
    return res;
  }

  private void requestPermission(String permission) {
    Activity activity = registrar.activity();
    permission = getManifestPermission(permission);
    Log.d("John Melody : ==> ", "Requesting permission : " + permission);
    String[] perm = {
      permission
    };
    ActivityCompat.requestPermissions(activity, perm, 0);
  }

  private boolean checkPermission(String permission) {
    Activity activity = registrar.activity();
    permission = getManifestPermission(permission);
    Log.d("John Melody : ==>", "Checking permission : " + permission);
    return (
    PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(activity, permission));
  }

  @Override
  public boolean onRequestPermissionsResult(
  int requestCode, String[] permissions, int[] grantResults) {
    int status = 0;
    String permission = permissions[0];
    if (requestCode == 0 && grantResults.length > 0) {
      if (ActivityCompat.shouldShowRequestPermissionRationale(registrar.activity(), permission)) {
        //denied
        status = 2;
      } else {
        if (ActivityCompat.checkSelfPermission(registrar.context(), permission) == PackageManager.PERMISSION_GRANTED) {
          //allowed
          status = 3;
        } else {
          //set to never ask again
          Log.e("John Melody : ==>", "Set to never ask again" + permission);
          status = 4;
        }
      }
    }
    Log.i("John Melody : ==>", "Requesting permission status : " + status);
    Result result = this.result;
    this.result = null;
    if (result != null) {
      result.success(status);
    }
    return status == 3;
  }
}