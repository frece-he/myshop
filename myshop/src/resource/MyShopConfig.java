package resource;

import java.io.File;


public class MyShopConfig {

//	public static String getMessageFromProperties(String exceptionName){
//		Properties properties = new Properties();
//		String message = null;
//		try {
//			InputStream is = Object.class.getResourceAsStream(	"exceptions.properties");
//			properties.load( is);
////			properties.load(Thread.currentThread().getContextClassLoader()
////					.getResourceAsStream(	"exceptions.properties"));		
//			System.out.println("properties load success");
//			message =properties.getProperty(exceptionName);
//			if(message==null){
//				message = "System Error";
//			}
//
//		} catch (IOException e) {
//			MyShopLogger
//			.logError("MyShopConfig", "getErrorMessage", e.toString());
//			
//		}
//
//
//		return message;
//
//
//	}


	public static String getPath(String path) {
		//	Properties properties = new Properties();
		try {

			if (path.equalsIgnoreCase("resourcePath")) {
				return "../page-resources/images/";
			}
			// constructing path for products in Desktop
			if (path.equalsIgnoreCase("imageUrlPath")) {
				String userHome = System.getProperty("user.home");
				String tillDesktop = userHome + "\\Desktop\\products\\";
				File myFile = new File(tillDesktop);
				if (!myFile.exists()) {
					myFile.mkdir();
				}
				return tillDesktop;
			}

			if (path.equalsIgnoreCase("errorLoggerPath")) {
				String userHome = System.getProperty("user.home");
				String tillDesktop = userHome + "\\Desktop\\logger\\";
				File myFile = new File(tillDesktop);
				if (!myFile.exists()) {
					myFile.mkdir();
				}
				return tillDesktop + "ErrorLogFile.txt";
			}

		} catch (Exception e) {

			MyShopLogger.logError("MyShopConfig", "getPath", e.toString());
		}
		return "";
	}
}
