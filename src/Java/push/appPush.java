package push;

import push.android.AndroidUnicast;

public class appPush {
    private String appkey = null;
    private String appMasterSecret = null;
    private String timestamp = null;
    private PushClient client = new PushClient();

    public appPush(String key, String secret) {
        try {
            appkey = key;
            appMasterSecret = secret;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public static void startPush() {
        // TODO set your appkey and master secret here
        appPush apppush = new appPush("59f57d4c734be436ff000101", "8ouwchdnyap80q45hedz5miz3zcckkkp");
        try {
            apppush.sendAndroidUnicast();
			/* TODO these methods are all available, just fill in some fields and do the test
			 * demo.sendAndroidCustomizedcastFile();
			 * demo.sendAndroidBroadcast();
			 * demo.sendAndroidGroupcast();
			 * demo.sendAndroidCustomizedcast();
			 * demo.sendAndroidFilecast();
			 *
			 * demo.sendIOSBroadcast();
			 * demo.sendIOSUnicast();
			 * demo.sendIOSGroupcast();
			 * demo.sendIOSCustomizedcast();
			 * demo.sendIOSFilecast();
			 */
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void sendAndroidUnicast() throws Exception {
        AndroidUnicast unicast = new AndroidUnicast(appkey,appMasterSecret);
        // TODO Set your device token
        unicast.setDeviceToken( "AvFDmx7l9VelIvBcf6CYkMR2UWI46zSMfrhYWHPx3wxN");
        unicast.setTicker( "Android unicast ticker");
        unicast.setTitle(  "test");
        unicast.setText(   "hello,world!");
        unicast.goAppAfterOpen();
        unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        // TODO Set 'production_mode' to 'false' if it's a test device.
        // For how to register a test device, please see the developer doc.
        unicast.setProductionMode();
        // Set customized fields
        unicast.setExtraField("test", "helloworld");
        client.send(unicast);
    }


}
