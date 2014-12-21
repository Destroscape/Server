package com;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
 
@SuppressWarnings("serial")
public class Splash extends JFrame {


	 private static String downloadUrl = "https://dl.dropboxusercontent.com/s/rw8pghagf9zvy65/Destroscape.jar";
	 private static String fileName = "Destroscape.jar";
	 private static String serverName = "Destroscape";
	 public static double VERSION = 1.0;
	 public static String VERSION_URL = "http://projectx.x10.bz/local/version.php";
	 private static String backgroundImageUrl = "http://projectx.x10.bz/local/Splash.gif";
	 private static String saveDirectory = System.getProperty("user.home")+"/Desktop/";
	
	public static URL url;
    private JLabel imglabel;
    public static String dir;
    private ImageIcon img;
    private static JProgressBar pbar;
    Thread t = null;
 
    public double getCurrentVersion(){
			return VERSION;
	}
    public String checkVersion()
    {
    String s1 = "";
        try
        {
            URL url = new URL(""+VERSION_URL+"");
            URLConnection urlconnection = url.openConnection();
            BufferedReader bufferedreader = null;
            bufferedreader = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
            s1 = bufferedreader.readLine();
            bufferedreader.close();
        }
        catch(Exception exception) { }
        return s1;
    }
    
    public double getNewestVersion(){
    	String version = ""+checkVersion()+"";
    	double v = Double.parseDouble(version);
    	return v;
	}
    
    private void handleException(Exception e){
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("Please Screenshot this message, and send it to an admin!\r\n\r\n");
		strBuff.append(e.getClass().getName() + " \"" + e.getMessage() + "\"\r\n");
		for(StackTraceElement s : e.getStackTrace())
			strBuff.append(s.toString() + "\r\n");
		alert("Exception [" + e.getClass().getSimpleName() + "]",strBuff.toString(),true);
	}
	
	private void alert(String msg){
		alert("Message",msg,false);
	}
	
	private void alert(String title,String msg,boolean error){
		JOptionPane.showMessageDialog(null,
			   msg,
			   title,
			    (error ? JOptionPane.ERROR_MESSAGE : JOptionPane.PLAIN_MESSAGE));
	}
    
    public Splash() {
        super("Splash");
        if(getCurrentVersion() != getNewestVersion()){
    	downloadClient();
    	}
        File file = new File(saveDirectory + fileName);
        
		try {
			url = new URL(downloadUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
        setSize(471, 339);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        
        try {
			img = new ImageIcon(new URL(backgroundImageUrl));
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
        
        imglabel = new JLabel(img);
        add(imglabel);
        setLayout(null);
        pbar = new JProgressBar();
        pbar.setMinimum(0);
        pbar.setMaximum(100);
        pbar.setStringPainted(true);
        pbar.setForeground(Color.LIGHT_GRAY);
          imglabel.setBounds(0, 0, 471, 339);
        add(pbar);
        pbar.setPreferredSize(new Dimension(310, 30));
        pbar.setBounds(70, 320, 404, 20);


        try {
            if (file.exists()) {
            	URLConnection connection = url.openConnection();
            	connection.connect();
    			long time = connection.getLastModified();
    			if (time > file.lastModified()) {
                    if (!startDialogue()) {
                    	startApplication();
                        return;
                    }
    			} else {
                    setVisible(true);
                    Thread.sleep(3000);
                    startApplication();
                    return;
                }
    		}
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Thread t = new Thread() {
 
        	public void run() {
            	OutputStream dest = null;
            	URLConnection download;
            	InputStream readFileToDownload = null;
            	try {
            		dest = new BufferedOutputStream(new FileOutputStream(saveDirectory + fileName)); 
            		download = url.openConnection();
            		readFileToDownload = download.getInputStream();
            		byte[] data = new byte[1024];
            		int numRead;
            		long numWritten = 0;
            		int length = download.getContentLength();
            		while ((numRead = readFileToDownload.read(data)) != -1) {
            			dest.write(data, 0, numRead);
            			numWritten += numRead;
            			int percent = (int)(((double)numWritten / (double)length) * 100D);
            			pbar.setValue(percent);
            			pbar.setString(""+(percent < 99 ? "Downloading "+serverName+" - "+percent+"%" : "Complete")+"");
            		}
            	} catch (Exception exception) {
            		exception.printStackTrace();
            	} finally {
            		try {
            			if (readFileToDownload != null)
            				readFileToDownload.close();
            			if (dest != null)
            				dest.close();
            			Thread.sleep(1000L);
            			startApplication();
            		} catch (IOException | InterruptedException ioe) {
            				
            		}
            	}
            }
        };
        t.start();
    }
    
    public void downloadClient(){
    	downloadUpdate("https://dl.dropboxusercontent.com/s/rw8pghagf9zvy65/Destroscape.jar", "");
		alert("Your client has just been updated! version:"+getNewestVersion()+"");
    }
    
    public static void downloadUpdate(String s, String s1)
    {
        dir = s1;
        try
        {
            URLConnection urlconnection = (new URL(s)).openConnection();
            String as[] = s.split("/");
            File file = new File(as[as.length - 1]);
            int i = urlconnection.getContentLength();
            InputStream inputstream = urlconnection.getInputStream();
            FileOutputStream fileoutputstream = new FileOutputStream((new StringBuilder()).append(dir).append(file).toString());
            int j = 0;
            byte abyte0[] = new byte[4096];
            int k;
            while((k = inputstream.read(abyte0)) != -1) 
            {
                fileoutputstream.write(abyte0, 0, k);
                j += k;
                int l = (int)(((double)j / (double)i) * 100D);
            }
            if(i != j)
            {
                inputstream.close();
                fileoutputstream.close();
            } else
            {
                inputstream.close();
                fileoutputstream.close();
            }
        }
        catch(Exception exception)
        {
            System.err.println("Error connecting to server.");
            exception.printStackTrace();
        }
    }

    
    public boolean startDialogue() {
        setVisible(true);
        int selection = JOptionPane.showConfirmDialog(null, "An update is available. Do you wish to download?", "Update Available", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        return selection == JOptionPane.OK_OPTION;
    }


    /**
     * Launches the downloaded Jar file and closes the progress bar
     */
    /**
     * Launches the downloaded Jar file and closes the progress bar
     */
    public static void startApplication() {
    	try {
			Runtime.getRuntime().exec("java -jar "+(saveDirectory + fileName)+"");
			Thread.sleep(1000L);
			System.exit(0);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
    }


}