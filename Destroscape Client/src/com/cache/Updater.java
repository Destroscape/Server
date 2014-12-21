package com.cache;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.zip.CRC32;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Updater {
	static String updateurl, Root;
	static ArrayList<String> Name = new ArrayList<String>();
	static ArrayList<String> Dir = new ArrayList<String>();
	static int TotalSize, SizeNow;
	static JLabel label1 = new JLabel();
	static JLabel label2 = new JLabel("Total percent");
	static JProgressBar progressBar,  progressBar1;
	static JFrame frame = new JFrame("Destroscape Cache Updater");
	
    public static void addComponentsToPane(Container pane) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
		pane.add(label1);
		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
		pane.add(progressBar);
		label2.setAlignmentX(Component.CENTER_ALIGNMENT);
		pane.add(label2);
		progressBar1 = new JProgressBar(0, 100);
		progressBar1.setValue(0);
		progressBar1.setStringPainted(true);
		progressBar1.setAlignmentX(Component.CENTER_ALIGNMENT);
		pane.add(progressBar1);
    }
    
	public static void Update(String UpdateUrl, String CacheDirectory) {
    	updateurl = UpdateUrl;
    	Root = CacheDirectory;
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addComponentsToPane(frame.getContentPane());
		frame.setLocationRelativeTo(null);
		frame.setPreferredSize(new Dimension(400, 120));
		frame.pack();
		download("", "Patch.xml", false);
		 try {
			  File file = new File(Root+ "Patch.xml");
			  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			  DocumentBuilder db = dbf.newDocumentBuilder();
			  Document doc = db.parse(file);
			  doc.getDocumentElement().normalize();
			  NodeList nodeLst = doc.getElementsByTagName("Update");
			  for (int s = 0; s < nodeLst.getLength(); s++) {
			    Node fstNode = nodeLst.item(s);
			    
			    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
			      Element fstElmnt = (Element) fstNode;
			      String name, dir, size, crc;
			      NodeList name1 = fstElmnt.getElementsByTagName("name");
			      Element name2 = (Element) name1.item(0);
			      NodeList name3 = name2.getChildNodes();
			      name = ((Node) name3.item(0)).getNodeValue();
			      
			      NodeList dir1 = fstElmnt.getElementsByTagName("dir");
			      Element dir2 = (Element) dir1.item(0);
			      NodeList dir3 = dir2.getChildNodes();
				  if(((Node) dir3.item(0)) == null)
				  dir = "";
				  else
			      dir = ((Node) dir3.item(0)).getNodeValue();
			      
			      NodeList size1 = fstElmnt.getElementsByTagName("size");
			      Element size2 = (Element) size1.item(0);
			      NodeList size3 = size2.getChildNodes();
			      size = ((Node) size3.item(0)).getNodeValue();
			      
			      NodeList crc1 = fstElmnt.getElementsByTagName("crc");
			      Element crc2 = (Element) crc1.item(0);
			      NodeList crc3 = crc2.getChildNodes();
			      crc = ((Node) crc3.item(0)).getNodeValue();
			      File f = new File(Root+"\\"+dir+"\\"+name);
			      if(f.exists()) {
			      String crc21 = doConvert32(f);
			      if(!crc21.contains(crc.toString())) {
			    	  TotalSize += Integer.parseInt(size);
			    	  Name.add(name);
			    	  Dir.add(dir);
			      }
			    } else {
			    	  TotalSize += Integer.parseInt(size);
			    	  Name.add(name);
			    	  Dir.add(dir);
			    }
			    }
			  }
			  } catch (Exception e) {
			    e.printStackTrace();
			  }
			  if(Name.size() > 0){
				  frame.setVisible(true);
				  download(Dir.get(0), Name.get(0), true);
			  } else {
				frame.setVisible(false);
				frame.dispose();
			  }
	}
	
	public static void download(String dir, String name, boolean check)
    {
        try
        {
            URLConnection connection = (new URL(updateurl+dir.replace("\\", "/").replace(" ", "%20")+"/"+name.replace(" ", "%20"))).openConnection();
            File file = new File(Root+dir+"\\"+name);
            int length = connection.getContentLength();
            InputStream instream = connection.getInputStream();
            try{
                    if(file.exists()){
                    file.delete();
                    }
                    String Dir[] = dir.split("\\\\");
                    String dict = "";
                    for(int i = 0; i < Dir.length; i++) {
                    	dict+= Dir[i] + "\\";
                    new File(Root+"\\"+dict).mkdir();
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            FileOutputStream outstream = new FileOutputStream(file);
            int size = 0;
            int copy;
            byte[] buffer = new byte[4096];
			int previousAmount = 0;
            while((copy = instream.read(buffer)) != -1)
            {                
            	outstream.write(buffer, 0, copy);
                size+= copy;
				if(check) {            	
        	    int bytesPerSecond = (size-previousAmount);
        	    previousAmount = size;
                SizeNow += copy;                
				int percentage = (int)(((double)size / (double)length) * 100D);
                int percentage2 = (int)(((double)SizeNow / (double)TotalSize) * 100D);
				progressBar.setValue(percentage);
                progressBar1.setValue(percentage2);
                label1.setText("Grabbing good stuff: " + name + " (" + bytesPerSecond + " Kb/s)");
				}
            }
            if(length != size)
            {
                instream.close();
                outstream.close();
                System.out.println("error");
            } else {
            	if(check) {
				  if(Name.size() > 1){
					  Name.remove(0);
					  Dir.remove(0);
			          instream.close();
			          outstream.close();
					  download(Dir.get(0), Name.get(0), true);
				  } else {
                instream.close();
                outstream.close();
                label1.setText("Done");
				frame.setVisible(false);
				frame.dispose();
				  }
            	} else {
                    instream.close();
                    outstream.close();
            	}
            }
        }
        catch(Exception e)
        {
            System.err.println("Error connecting to server." + e);
            e.printStackTrace();
        }
    }
	
    public static String doConvert32(File file)
    {
        byte[] bytes = readBytesFromFile(file);
        CRC32 x = new CRC32();
        x.update(bytes);
        return (Long.toHexString(x.getValue())).toUpperCase();
    }

    private static byte[] readBytesFromFile(File file)
    {
        try
        {
            InputStream is = new FileInputStream(file);
            long length = file.length(); 
            byte[] bytes = new byte[(int)length]; 
            int offset = 0; 
            int numRead = 0; 
            while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0)
            { 
                offset += numRead; 
            } 
            if (offset < bytes.length) { 
                System.out.println("Could not completely read file " + file.getName()); 
            } 
            is.close(); 
            return bytes;
        }
        catch (IOException e)
        {
            System.out.println("IOException " + file.getName()); 
            return null;
        }
    }

}