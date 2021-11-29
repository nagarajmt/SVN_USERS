import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchResults;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class FindAllActiveProjectUsersMap extends ServerConstant {
   int projectCounter = 1;
   int userCounter = 1;

   public void listAllActiveProject(HashMap<String, HashMap<String, String>> repositorySize) {
      String sourceFolderPath = "svn_access_file";
      BufferedReader br = null;
      LDAPConnection conn = new LDAPConnection();
      XSSFWorkbook workbook = new XSSFWorkbook();
      workbook.createSheet("Project_List");
      workbook.createSheet("Project_Users");
      workbook.createSheet("Group_Users");
      XSSFSheet sheet = null;
      Row row = null;
      Cell cell = null;
      int activeprojectRowNum = 1;
      int projectRowNum = 1;
      int groupRowNum = 1;
      File folder = new File(sourceFolderPath);
      File[] files = folder.listFiles();

      for(int x = 0; x < files.length; ++x) {
         this.appendInfo(" Reading file " + files[x].getName());
         if (files[x].isFile() && files[x].getName().contains(".txt")) {
            this.appendInfo(" Analizing file " + files[x].getName());
            HashMap<String, String[]> group_users = new HashMap();
            HashMap project_users = new HashMap();

            try {
               br = new BufferedReader(new FileReader(files[x]));
               Pattern p1 = Pattern.compile("\\[(.*?)\\]");
               Matcher m = null;
               boolean grab = false;
               String sCurrentLine = null;
               String currentProject = null;

               while((sCurrentLine = br.readLine()) != null) {
                  m = p1.matcher(sCurrentLine);
                  if (!sCurrentLine.startsWith("#") && !sCurrentLine.trim().equalsIgnoreCase("")) {
                     if (sCurrentLine.contains("groups")) {
                        grab = true;
                     } else {
                        if (m.find()) {
                           grab = false;
                        }

                        if (grab && sCurrentLine.contains("=") && sCurrentLine.split("=").length == 2) {
                           group_users.put(sCurrentLine.split("=")[0], sCurrentLine.split("=")[1].split(","));
                        }
                     }
                  }
               }

               br.close();
               br = new BufferedReader(new FileReader(files[x]));

               label679:
               while(true) {
                  while(true) {
                     do {
                        do {
                           if ((sCurrentLine = br.readLine()) == null) {
                              this.appendInfo("Found " + project_users.size() + " active projects....");
                              sheet = workbook.getSheet("Project_List");
                              row = sheet.createRow(0);
                              cell = null;
                              cell = row.createCell(0);
                              cell.setCellValue("PROJECTS");
                              cell.getCellStyle().setAlignment((short)2);
                              cell = row.createCell(1);
                              cell.setCellValue("SERVER");
                              cell.getCellStyle().setAlignment((short)2);
                              cell = row.createCell(2);
                              cell.setCellValue("IP ADDRESS");
                              cell.getCellStyle().setAlignment((short)2);
                              cell = row.createCell(3);
                              cell.setCellValue("REPOSITORY SIZE");
                              cell.getCellStyle().setAlignment((short)2);
                              cell = row.createCell(4);
                              cell.setCellValue("LAST REVISION");
                              cell.getCellStyle().setAlignment((short)2);
                              cell = row.createCell(5);
                              cell.setCellValue("LAST AUTHOR");
                              cell.getCellStyle().setAlignment((short)2);
                              cell = row.createCell(6);
                              cell.setCellValue("LAST COMMIT DATE");
                              cell.getCellStyle().setAlignment((short)2);
                              cell = row.createCell(7);
                              cell.setCellValue("LAST LOG MESSAGE");
                              cell.getCellStyle().setAlignment((short)2);
                              cell = row.createCell(8);
                              cell.setCellValue("CREATED ON");
                              cell.getCellStyle().setAlignment((short)2);
                              ServerConstant.Server server = getObjectByLocalFileName(sourceFolderPath + "/" + files[x].getName());
                              Iterator var81 = project_users.keySet().iterator();

                              String group;
                              while(var81.hasNext()) {
                                 group = (String)var81.next();
                                 row = sheet.createRow(activeprojectRowNum++);
                                 cell = row.createCell(0);
                                 cell.setCellValue(group);
                                 cell = row.createCell(1);
                                 cell.setCellValue(server.name());
                                 cell = row.createCell(2);
                                 cell.setCellValue(server.getIp());
                                 cell = row.createCell(3);

                                 try {
                                    if (repositorySize != null && repositorySize.containsKey(server.name()) && repositorySize.get(server.name()) != null && ((HashMap)repositorySize.get(server.name())).containsKey(group)) {
                                       System.out.println("Size Found : " + (String)((HashMap)repositorySize.get(server.name())).get(group));
                                       cell.setCellValue((String)((HashMap)repositorySize.get(server.name())).get(group));
                                    }
                                 } catch (Exception var72) {
                                    var72.printStackTrace();
                                 }

                                 try {
                                    HashMap<String, Object> log = this.findLastLog("nagarajt", "Strong@098", server.getIp(), group);
                                    System.out.println(log);
                                    cell = row.createCell(4);
                                    cell.setCellValue(log.get("revision").toString());
                                    cell = row.createCell(5);
                                    cell.setCellValue(log.get("author").toString());
                                    cell = row.createCell(6);
                                    cell.setCellValue(log.get("date").toString());
                                    cell = row.createCell(7);
                                    cell.setCellValue(log.get("message").toString());
                                    cell = row.createCell(8);
                                    cell.setCellValue(log.get("createdDate").toString());
                                 } catch (Exception var71) {
                                    var71.printStackTrace();
                                 }
                              }

                              sheet = workbook.getSheet("Project_Users");
                              row = sheet.createRow(0);
                              cell = null;
                              cell = row.createCell(0);
                              cell.setCellValue("PROJECTS");
                              cell.getCellStyle().setAlignment((short)2);
                              cell = row.createCell(1);
                              cell.setCellValue("USERS");
                              cell.getCellStyle().setAlignment((short)2);
                              cell = row.createCell(2);
                              cell.setCellValue("NAME");
                              cell.getCellStyle().setAlignment((short)2);
                              cell = row.createCell(3);
                              cell.setCellValue("EMAIL");
                              cell.getCellStyle().setAlignment((short)2);
                              cell = row.createCell(4);
                              cell.setCellValue("DEPARTMENT");
                              cell.getCellStyle().setAlignment((short)2);
                              cell = row.createCell(5);
                              cell.setCellValue("ENABLED");
                              cell.getCellStyle().setAlignment((short)2);
                              conn.connect("10.98.10.194", 3268);
                              conn.bind("CN=Tools Team Service ID (HCLT),OU=Generic ID,DC=HCLT,DC=CORP,DC=HCL,DC=IN", "India@1234");
                              this.projectCounter = 1;
                              var81 = project_users.keySet().iterator();

                              while(var81.hasNext()) {
                                 group = (String)var81.next();
                                 Set<String> users = (Set)project_users.get(group);
                                 this.userCounter = 1;
                                 Iterator var27 = users.iterator();

                                 while(var27.hasNext()) {
                                    String user = (String)var27.next();

                                    try {
                                       LDAPSearchResults searchResults = conn.search("DC=HCLT,DC=CORP,DC=HCL,DC=IN", 2, "sAMAccountName=" + user.trim(), (String[])null, false);
                                       row = sheet.createRow(projectRowNum++);
                                       cell = row.createCell(0);
                                       cell.setCellValue(group);
                                       cell = row.createCell(1);
                                       cell.setCellValue(user);

                                       while(searchResults.hasMore()) {
                                          try {
                                             LDAPEntry nextEntry = null;
                                             nextEntry = searchResults.next();
                                             LDAPAttributeSet attributeSet = nextEntry.getAttributeSet();
                                             cell = row.createCell(2);
                                             cell.setCellValue(attributeSet.getAttribute("name").getStringValue());
                                             cell = row.createCell(3);
                                             cell.setCellValue(attributeSet.getAttribute("mail").getStringValue());
                                             cell = row.createCell(4);
                                             cell.setCellValue(attributeSet.getAttribute("department").getStringValue());
                                             cell = row.createCell(5);
                                             cell.setCellValue(attributeSet.getAttribute("msRTCSIP-UserEnabled").getStringValue());
                                          } catch (Exception var70) {
                                             this.appendError("Error-001 : " + user + " : " + var70.getLocalizedMessage());
                                          }
                                       }
                                    } catch (Exception var73) {
                                       this.appendError("Error-002 : " + var73.getMessage());
                                    }
                                 }
                              }

                              sheet = workbook.getSheet("Group_Users");
                              row = sheet.createRow(0);
                              cell = null;
                              cell = row.createCell(0);
                              cell.setCellValue("GROUPS");
                              cell.getCellStyle().setAlignment((short)2);
                              cell = row.createCell(1);
                              cell.setCellValue("USERS");
                              cell.getCellStyle().setAlignment((short)2);
                              var81 = group_users.keySet().iterator();

                              while(var81.hasNext()) {
                                 group = (String)var81.next();
                                 String[] users = (String[])group_users.get(group);

                                 for(int i = 0; i < users.length; ++i) {
                                    row = sheet.createRow(groupRowNum++);
                                    cell = row.createCell(0);
                                    cell.setCellValue(group);
                                    cell = row.createCell(1);
                                    cell.setCellValue(users[i]);
                                 }
                              }
                              break label679;
                           }

                           m = p1.matcher(sCurrentLine);
                        } while(sCurrentLine.startsWith("#"));
                     } while(sCurrentLine.trim().equalsIgnoreCase(""));

                     if (m.find() && sCurrentLine.contains(":/")) {
                        currentProject = sCurrentLine.split(":")[0].replace("[", "");
                     } else if (sCurrentLine.startsWith("@") && !sCurrentLine.startsWith("@svnadmin")) {
                        String group = sCurrentLine.split("=")[0].replace("@", "");

                        try {
                           String[] tempUsers = (String[])group_users.get(group);
                           Set<String> user = null;
                           if (project_users.containsKey(currentProject)) {
                              user = (Set)project_users.get(currentProject);
                           } else {
                              user = new HashSet();
                           }

                           for(int i = 0; i < tempUsers.length; ++i) {
                              ((Set)user).add(tempUsers[i]);
                           }

                           project_users.put(currentProject, user);
                        } catch (Exception var74) {
                        }
                     }
                  }
               }
            } catch (Exception var75) {
               var75.printStackTrace();
            } finally {
               try {
                  br.close();
                  if (conn.isConnected()) {
                     conn.disconnect();
                  }

                  this.appendLog("Disconnected");
               } catch (IOException var66) {
                  this.appendError("Error-004 : " + var66.getMessage());
               } catch (LDAPException var67) {
                  this.appendError("Error-005 : " + var67.getMessage());
               }

            }

            this.appendLog("***************************************************************");
         } else {
            this.appendInfo(" Invalid file " + files[x].getName());
         }
      }

      this.appendLog("Populating Excel Sheet....");
      FileOutputStream out = null;

      try {
         File file = new File("Active-Projects-Users-List.xlsx");
         out = new FileOutputStream(file);
         workbook.write(out);
         out.close();
         this.appendLog("Completed ....");
         this.appendLog("File path : " + file.getAbsolutePath());
      } catch (Exception var68) {
         this.appendError("Error-006 : " + var68.getMessage());
      } finally {
         try {
            out.close();
         } catch (IOException var65) {
            this.appendError("Error-007 : " + var65.getMessage());
         }

      }

   }

   public HashMap<String, Object> findLastLog(String userName, String password, String server, String repoName) {
      HashMap<String, Object> data = new HashMap();
      String url = "http://" + server + "/svn/" + repoName;
      long startRevision = -1L;
      long endRevision = -1L;

      try {
         SVNRepository repository = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(url));
         ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(userName, password);
         repository.setAuthenticationManager(authManager);
         Collection logEntries = null;

         try {
            logEntries = repository.log(new String[]{""}, (Collection)null, startRevision, endRevision, true, true);
         } catch (SVNException var16) {
            System.out.println("error while collecting log information for '" + url + "': " + var16.getMessage());
         }

         Iterator entries = logEntries.iterator();

         SVNLogEntry logEntry;
         while(entries.hasNext()) {
            logEntry = (SVNLogEntry)entries.next();
            data.put("revision", logEntry.getRevision());
            data.put("author", logEntry.getAuthor());
            data.put("date", logEntry.getDate());
            data.put("message", logEntry.getMessage());
         }

         logEntries = repository.log(new String[]{""}, (Collection)null, 0L, 0L, true, true);
         entries = logEntries.iterator();

         while(entries.hasNext()) {
            logEntry = (SVNLogEntry)entries.next();
            data.put("createdDate", logEntry.getDate());
         }

         return data;
      } catch (SVNException var17) {
         var17.printStackTrace();
      } catch (Exception var18) {
         var18.printStackTrace();
      }

      return null;
   }

   private void appendLog(String msg) {
      System.out.println(msg);
   }

   private void appendError(String msg) {
      System.out.println(msg);
   }

   private void appendInfo(String msg) {
      System.out.println(msg);
   }
}
