public class ServerConstant {
	
   public static ServerConstant.Server getObjectByLocalFileName(String localFileName) {
      ServerConstant.Server[] var4;
      int var3 = (var4 = ServerConstant.Server.values()).length;

      for(int var2 = 0; var2 < var3; ++var2) {
         ServerConstant.Server s = var4[var2];
         if (s.getLocalFileName().equalsIgnoreCase(localFileName)) {
            return s;
         }
      }

      return null;
   }

   public static enum Server {
      SVNEDGE("10.98.101.201", "svn", "July$2020r3dha5#08", 22, "/home/svn/CSVN/csvn/data/conf/svn_access_file", "svn_access_file/svnedge-1.txt", "/SVN_EDGE/repos"),
      //SVNEDGE2("10.98.101.202", "svn", "Pa55w0rd@12345", 22, "/home/svn/CSVN/csvn/data/conf/svn_access_file", "svn_access_file/svnedge-2.txt", "/SVN_EDGE/repositories"),
      SVNEDGE3("10.98.101.209", "svn", "July$2020r3dha5#08", 22, "/home/svn/CSVN/csvn/data/conf/svn_access_file", "svn_access_file/svnedge-3.txt", "/SVN_EDGE/repositories"),
      SVNEDGE4("10.98.101.213", "svn", "July$2020r3dha5#08", 22, "/home/svn/CSVN/csvn/data/conf/svn_access_file", "svn_access_file/svnedge-4.txt", "/SVN_EDGE/repositories"),
      SVNEDGE5("10.98.101.214", "svn", "July$2020r3dha5#08", 22, "/home/svn/CSVN/csvn/data/conf/svn_access_file", "svn_access_file/svnedge-5.txt", "/SVN_EDGE/repositories"),
      //SVNEDGE6("10.98.100.153", "svn", "Pa55w0rd@12345", 22, "/home/svn/CSVN/csvn/data/conf/svn_access_file", "svn_access_file/svnedge-6.txt", "/SVN_EDGE/repositories"),
      //SVNEDGE7("10.98.100.51", "svn", "July$2020r3dha5#08", 22, "/home/svn/CSVN/csvn/data/conf/svn_access_file", "svn_access_file/svnedge-7.txt", "/SVN_EDGE/repositories"),
      //SVNHCLCOM("10.98.100.179", "svn", "July$2020r3dha5#08", 22, "/home/admin/svn/csvn/data/conf/svn_access_file", "svn_access_file/svn.hcl.com.txt", "/SVN_EDGE/repositories"),
      //SVNSTORAGE("10.98.101.211", "svn", "July$2020r3dha5#08", 22, "/home/svn/CSVN/csvn/data/conf/svn_access_file", "svn_access_file/svnstorage.txt", "/SVN_EDGE/repositories"),
      //SVNTRAINING("10.99.18.226", "Saikrishna.G", "2016@may", 22, "/home/Saikrishna.G/CSVN/csvn/data/conf/svn_access_file", "svn_access_file/svnstorage.txt", "/home/Saikrishna.G/CSVN/csvn/data/repositories")
      ;

      private String ip;
      private String userId;
      private String password;
      private String serverFilePath;
      private String localFileName;
      private String repositoriesPath;
      int port;

      private Server(String ip, String userId, String password, int port, String serverFilePath, String localFileName, String repositoriesPath) {
         this.setIp(ip);
         this.setUserId(userId);
         this.setPassword(password);
         this.setPort(port);
         this.setServerFilePath(serverFilePath);
         this.setLocalFileName(localFileName);
         this.setRepositoriesPath(repositoriesPath);
      }

      public String getIp() {
         return this.ip;
      }

      public void setIp(String ip) {
         this.ip = ip;
      }

      public String getUserId() {
         return this.userId;
      }

      public void setUserId(String userId) {
         this.userId = userId;
      }

      public String getPassword() {
         return this.password;
      }

      public void setPassword(String password) {
         this.password = password;
      }

      public int getPort() {
         return this.port;
      }

      public void setPort(int port) {
         this.port = port;
      }

      public String getServerFilePath() {
         return this.serverFilePath;
      }

      public void setServerFilePath(String serverFilePath) {
         this.serverFilePath = serverFilePath;
      }

      public String getLocalFileName() {
         return this.localFileName;
      }

      public void setLocalFileName(String localFileName) {
         this.localFileName = localFileName;
      }

      public String getRepositoriesPath() {
         return this.repositoriesPath;
      }

      public void setRepositoriesPath(String repositoriesPath) {
         this.repositoriesPath = repositoriesPath;
      }
   }
}
