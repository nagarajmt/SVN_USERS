import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class GetAccessFiles extends ServerConstant{
   private static Hashtable<String, String> config = new Hashtable();

   public static void main(String[] args) {
      config.put("StrictHostKeyChecking", "no");
      HashMap<String, HashMap<String, String>> repositorySize = new HashMap();
      GetAccessFiles servers = new GetAccessFiles();
		/*
		 * File folder = new File(ServerConstant.Server.SVNEDGE.getLocalFileName()); if
		 * (!folder.exists()) { folder.mkdirs(); }
		 */

      try {
         repositorySize.put(ServerConstant.Server.SVNEDGE.name(), servers.downloadFtp(ServerConstant.Server.SVNEDGE.getUserId(), ServerConstant.Server.SVNEDGE.getPassword(), ServerConstant.Server.SVNEDGE.getIp(), ServerConstant.Server.SVNEDGE.getPort(), ServerConstant.Server.SVNEDGE.getServerFilePath(), ServerConstant.Server.SVNEDGE.getLocalFileName(), ServerConstant.Server.SVNEDGE.name(), ServerConstant.Server.SVNEDGE.getRepositoriesPath()));
         System.out.println("SVNEDGE-1 Copied ....");
         //repositorySize.put(ServerConstant.Server.SVNEDGE2.name(), servers.downloadFtp(ServerConstant.Server.SVNEDGE2.getUserId(), ServerConstant.Server.SVNEDGE2.getPassword(), ServerConstant.Server.SVNEDGE2.getIp(), ServerConstant.Server.SVNEDGE2.getPort(), ServerConstant.Server.SVNEDGE2.getServerFilePath(), ServerConstant.Server.SVNEDGE2.getLocalFileName(), ServerConstant.Server.SVNEDGE2.name(), ServerConstant.Server.SVNEDGE2.getRepositoriesPath()));
         //System.out.println("SVNEDGE-2 Copied ....");
         repositorySize.put(ServerConstant.Server.SVNEDGE3.name(), servers.downloadFtp(ServerConstant.Server.SVNEDGE3.getUserId(), ServerConstant.Server.SVNEDGE3.getPassword(), ServerConstant.Server.SVNEDGE3.getIp(), ServerConstant.Server.SVNEDGE3.getPort(), ServerConstant.Server.SVNEDGE3.getServerFilePath(), ServerConstant.Server.SVNEDGE3.getLocalFileName(), ServerConstant.Server.SVNEDGE3.name(), ServerConstant.Server.SVNEDGE3.getRepositoriesPath()));
         System.out.println("SVNEDGE-3 Copied ....");
         repositorySize.put(ServerConstant.Server.SVNEDGE4.name(), servers.downloadFtp(ServerConstant.Server.SVNEDGE4.getUserId(), ServerConstant.Server.SVNEDGE4.getPassword(), ServerConstant.Server.SVNEDGE4.getIp(), ServerConstant.Server.SVNEDGE4.getPort(), ServerConstant.Server.SVNEDGE4.getServerFilePath(), ServerConstant.Server.SVNEDGE4.getLocalFileName(), ServerConstant.Server.SVNEDGE4.name(), ServerConstant.Server.SVNEDGE4.getRepositoriesPath()));
         System.out.println("SVNEDGE-4 Copied ....");
         repositorySize.put(ServerConstant.Server.SVNEDGE5.name(), servers.downloadFtp(ServerConstant.Server.SVNEDGE5.getUserId(), ServerConstant.Server.SVNEDGE5.getPassword(), ServerConstant.Server.SVNEDGE5.getIp(), ServerConstant.Server.SVNEDGE5.getPort(), ServerConstant.Server.SVNEDGE5.getServerFilePath(), ServerConstant.Server.SVNEDGE5.getLocalFileName(), ServerConstant.Server.SVNEDGE5.name(), ServerConstant.Server.SVNEDGE5.getRepositoriesPath()));
         System.out.println("SVNEDGE-5 Copied ....");
         //repositorySize.put(ServerConstant.Server.SVNEDGE6.name(), servers.downloadFtp(ServerConstant.Server.SVNEDGE6.getUserId(), ServerConstant.Server.SVNEDGE6.getPassword(), ServerConstant.Server.SVNEDGE6.getIp(), ServerConstant.Server.SVNEDGE6.getPort(), ServerConstant.Server.SVNEDGE6.getServerFilePath(), ServerConstant.Server.SVNEDGE6.getLocalFileName(), ServerConstant.Server.SVNEDGE6.name(), ServerConstant.Server.SVNEDGE6.getRepositoriesPath()));
         //System.out.println("SVNEDGE-6 Copied ....");
         //repositorySize.put(ServerConstant.Server.SVNEDGE7.name(), servers.downloadFtp(ServerConstant.Server.SVNEDGE7.getUserId(), ServerConstant.Server.SVNEDGE7.getPassword(), ServerConstant.Server.SVNEDGE7.getIp(), ServerConstant.Server.SVNEDGE7.getPort(), ServerConstant.Server.SVNEDGE7.getServerFilePath(), ServerConstant.Server.SVNEDGE7.getLocalFileName(), ServerConstant.Server.SVNEDGE7.name(), ServerConstant.Server.SVNEDGE7.getRepositoriesPath()));
         //System.out.println("SVNEDGE-7 Copied ....");
         //repositorySize.put(ServerConstant.Server.SVNHCLCOM.name(), servers.downloadFtp(ServerConstant.Server.SVNHCLCOM.getUserId(), ServerConstant.Server.SVNHCLCOM.getPassword(), ServerConstant.Server.SVNHCLCOM.getIp(), ServerConstant.Server.SVNHCLCOM.getPort(), ServerConstant.Server.SVNHCLCOM.getServerFilePath(), ServerConstant.Server.SVNHCLCOM.getLocalFileName(), ServerConstant.Server.SVNHCLCOM.name(), ServerConstant.Server.SVNHCLCOM.getRepositoriesPath()));
         //System.out.println("SVN.HCL.COM Copied ....");
         
         //repositorySize.put(ServerConstant.Server.SVNSTORAGE.name(), servers.downloadFtp(ServerConstant.Server.SVNSTORAGE.getUserId(), ServerConstant.Server.SVNSTORAGE.getPassword(), ServerConstant.Server.SVNSTORAGE.getIp(), ServerConstant.Server.SVNSTORAGE.getPort(), ServerConstant.Server.SVNSTORAGE.getServerFilePath(), ServerConstant.Server.SVNSTORAGE.getLocalFileName(), ServerConstant.Server.SVNSTORAGE.name(), ServerConstant.Server.SVNSTORAGE.getRepositoriesPath()));
         //System.out.println("SVNSTORAGE Copied ....");
         
      } catch (Exception var8) {
         var8.printStackTrace();
      }

      System.out.println("All Files has been transfered.");
      System.out.println("Analysizing the data ....");

      try {
         System.out.println("**************************************************************************************************");
         System.out.println("Server Length : " + repositorySize.size());
         Iterator var5 = repositorySize.keySet().iterator();

         while(var5.hasNext()) {
            String key = (String)var5.next();
            System.out.println(key + "  :  " + repositorySize.get(key));
            System.out.println("Reposities Length + " + ((HashMap)repositorySize.get(key)).size());
            Iterator var7 = ((HashMap)repositorySize.get(key)).keySet().iterator();

            while(var7.hasNext()) {
               String subKey = (String)var7.next();
               System.out.println("\t\t" + subKey + "  :   " + (String)((HashMap)repositorySize.get(key)).get(subKey));
            }
         }

         System.out.println("**************************************************************************************************");
      } catch (Exception var9) {
         System.out.println("EXCEPTION : " + var9.getLocalizedMessage());
         System.out.println("EXCEPTION : " + var9.getMessage());
         var9.printStackTrace();
      }

      FindAllActiveProjectUsersMap projectUsersMap = new FindAllActiveProjectUsersMap();
      projectUsersMap.listAllActiveProject(repositorySize);
   }

   public static HashMap<String, Long> getDirectorySize(FTPClient ftpClient, String parentDir, String saveDir) {
      HashMap fileSize = new HashMap();

      try {
         FTPFile[] subFiles = ftpClient.listFiles(parentDir);
         if (subFiles != null && subFiles.length > 0) {
            FTPFile[] var8 = subFiles;
            int var7 = subFiles.length;

            for(int var6 = 0; var6 < var7; ++var6) {
               FTPFile aFile = var8[var6];
               String currentFileName = aFile.getName();
               if (!currentFileName.equals(".") && !currentFileName.equals("..")) {
                  fileSize.put(aFile.getName(), aFile.getSize());
               }
            }

            return fileSize;
         }
      } catch (IOException var10) {
         var10.printStackTrace();
      } catch (Exception var11) {
         var11.printStackTrace();
      }

      return null;
   }

   public static void downloadDirectory(FTPClient ftpClient, String parentDir, String currentDir, String saveDir) throws IOException {
      String dirToList = parentDir;
      if (!currentDir.equals("")) {
         dirToList = parentDir + "/" + currentDir;
      }

      FTPFile[] subFiles = ftpClient.listFiles(dirToList);
      if (subFiles != null && subFiles.length > 0) {
         FTPFile[] var9 = subFiles;
         int var8 = subFiles.length;

         for(int var7 = 0; var7 < var8; ++var7) {
            FTPFile aFile = var9[var7];
            String currentFileName = aFile.getName();
            if (!currentFileName.equals(".") && !currentFileName.equals("..")) {
               String filePath = parentDir + "/" + currentDir + "/" + currentFileName;
               if (currentDir.equals("")) {
                  filePath = parentDir + "/" + currentFileName;
               }

               String newDirPath = saveDir + parentDir + File.separator + currentDir + File.separator + currentFileName;
               if (currentDir.equals("")) {
                  newDirPath = saveDir + parentDir + File.separator + currentFileName;
               }

               if (aFile.isDirectory()) {
                  File newDir = new File(newDirPath);
                  boolean created = newDir.mkdirs();
                  if (created) {
                     System.out.println("CREATED the directory: " + newDirPath);
                  } else {
                     System.out.println("COULD NOT create the directory: " + newDirPath);
                  }

                  downloadDirectory(ftpClient, dirToList, currentFileName, saveDir);
               } else {
                  boolean success = downloadSingleFile(ftpClient, filePath, newDirPath);
                  if (success) {
                     System.out.println("DOWNLOADED the file: " + filePath);
                  } else {
                     System.out.println("COULD NOT download the file: " + filePath);
                  }
               }
            }
         }
      }

   }

   public static boolean downloadSingleFile(FTPClient ftpClient, String remoteFilePath, String savePath) throws IOException {
      File downloadFile = new File(savePath);
      File parentDir = downloadFile.getParentFile();
      if (!parentDir.exists()) {
         parentDir.mkdir();
      }

      BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));

      boolean var8;
      try {
         ftpClient.setFileType(2);
         var8 = ftpClient.retrieveFile(remoteFilePath, outputStream);
      } catch (IOException var11) {
         throw var11;
      } finally {
         if (outputStream != null) {
            outputStream.close();
         }

      }

      return var8;
   }

   public HashMap<String, String> downloadFtp(String userName, String password, String host, int port, String path, String localFileName, String serverName, String repositoryPath) {
      Session session = null;
      Channel channel = null;

      try {
         JSch ssh = new JSch();
         JSch.setConfig(config);
         session = ssh.getSession(userName, host, port);
         session.setPassword(password);
         session.connect();
         channel = session.openChannel("sftp");
         channel.connect();
         ChannelSftp sftp = (ChannelSftp)channel;
         sftp.get(path, localFileName);

         try {
            StringBuffer stringBuffer = new StringBuffer();
            Channel execChannel = session.openChannel("exec");
            execChannel.setInputStream((InputStream)null);
            ((ChannelExec)execChannel).setErrStream(System.err);
            ((ChannelExec)execChannel).setCommand("du -sh " + repositoryPath + "/*");
            InputStream in = execChannel.getInputStream();
            execChannel.connect();
            System.out.println("Is Connected : " + execChannel.isConnected());
            byte[] tmp = new byte[1024];

            while(true) {
               if (in.available() > 0) {
                  int i = in.read(tmp, 0, 1024);
                  if (i >= 0) {
                     String tempVar = new String(tmp, 0, i);
                     stringBuffer.append(tempVar);
                     continue;
                  }
               }

               if (execChannel.isClosed()) {
                  if (in.available() <= 0) {
                     System.out.println("exit-status: " + execChannel.getExitStatus());
                     in.close();
                     execChannel.disconnect();
                     HashMap var20 = this.parseBufferToHashmap(stringBuffer, repositoryPath);
                     return var20;
                  }
               } else {
                  try {
                     Thread.sleep(1000L);
                  } catch (Exception var26) {
                     var26.printStackTrace();
                  }
               }
            }
         } catch (Exception var27) {
            var27.printStackTrace();
         }
      } catch (JSchException var28) {
         var28.printStackTrace();
      } catch (SftpException var29) {
         var29.printStackTrace();
      } finally {
         if (channel != null) {
            channel.disconnect();
         }

         if (session != null) {
            session.disconnect();
         }

      }

      return null;
   }

   public HashMap<String, String> parseBufferToHashmap(StringBuffer buffer, String repositoryPath) {
      try {
         String[] eachLine = buffer.toString().split("\n");
         System.out.println("Count of lines : " + eachLine.length);
         HashMap<String, String> reposize = new HashMap();

         for(int i = 0; i < eachLine.length; ++i) {
            System.out.println(eachLine[i].replace(repositoryPath, "").split("/")[1].trim() + " : " + eachLine[i].replace(repositoryPath, "").split("/")[0].trim());
            reposize.put(eachLine[i].replace(repositoryPath, "").split("/")[1].trim(), eachLine[i].replace(repositoryPath, "").split("/")[0].trim());
         }

         return reposize;
      } catch (Exception var6) {
         var6.printStackTrace();
         return null;
      }
   }
}
