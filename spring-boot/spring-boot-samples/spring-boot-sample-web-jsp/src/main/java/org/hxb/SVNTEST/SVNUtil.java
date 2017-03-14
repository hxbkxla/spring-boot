package org.hxb.SVNTEST;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.tmatesoft.svn.core.ISVNLogEntryHandler;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.io.SVNRepository;

public class SVNUtil {
    
    /***
    *
    * @param path
    * @return 查询给定路径下的条目列表
    * @throws SVNException
    */
   @SuppressWarnings("rawtypes")
   public static List<SVN> listEntries(SVNRepository repository,String path)throws SVNException {
      Collection entries = repository.getDir(path, -1, null,(Collection) null);
      Iterator iterator = entries.iterator();
      List<SVN> svns = new ArrayList<SVN>();
      while (iterator.hasNext()) {
          SVNDirEntry entry = (SVNDirEntry) iterator.next();
          SVN svn = new SVN();
          svn.setCommitMessage(entry.getCommitMessage());
          svn.setDate(entry.getDate());
          svn.setKind(entry.getKind().toString());
          svn.setName(entry.getName());
          svn.setRepositoryRoot(entry.getRepositoryRoot().toString());
          svn.setRevision(entry.getRevision());
          svn.setSize(entry.getSize()/1024);
          svn.setUrl(path.equals("") ? entry.getName() : path +"/"+entry.getName());
          svn.setAuthor(entry.getAuthor());
          svn.setState(svn.getKind() == "file"?null:"closed");
          svn.setId(UUID.randomUUID().toString());
          svns.add(svn);
      }
	return svns;
   }
   
   
   /***
    * 查询历史信息
    * @param path
    * @return
    * @throws SVNException List<SVN>
    * @author 中科软-黄雄斌	
    * @date 2016年11月21日
    * @Email huangxbgz@sinosoft.com.cn
    */
	public static List<SVN> getHistory(SVNRepository repository,String path) throws SVNException {
		final List<SVN> svns=new ArrayList<SVN>();
		repository.log(new String[]{path}, 0, -1, false, false, new ISVNLogEntryHandler(){

			@Override
			public void handleLogEntry(SVNLogEntry logEntry) throws SVNException {
				SVN svn=new SVN();
				svn.setId(UUID.randomUUID().toString());
				svn.setAuthor(logEntry.getAuthor());
				svn.setCommitMessage(logEntry.getMessage());
				svn.setDate(logEntry.getDate());
				svn.setRevision(logEntry.getRevision());
				svns.add(svn);
			}
		
			
		});
		return svns;
		
	}
	
	
	
	public static void download(OutputStream os,String path,Long revision,SVNRepository repository) throws SVNException{
		SVNProperties properties = new SVNProperties();
		repository.getFile(path, revision, properties, os);
	}
   
}
