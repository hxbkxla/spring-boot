package sample.jsp;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public enum SVNAuth {

	Auth("http://128.232.6.234:81/svn/repository/ESERVICE", "sinosoft_hxb", "Ab123456");
	private String url;
	private String usrName;
	private String password;
	private SVNRepository repository;
	private SVNClientManager outClientManager;
	private ISVNAuthenticationManager authManager;
	private boolean isLogin;

	private SVNAuth(String url, String usrName, String password) {
		this.password = password;
		this.url = url;
		this.usrName = usrName;
		this.isLogin = login();
		setOutClientManager();
	}

	public boolean isLogin() {
		return isLogin;
	}

	public SVNClientManager getOutClientManager() {
		return outClientManager;
	}

	private void setOutClientManager(){
		DefaultSVNOptions options = SVNWCUtil.createDefaultOptions(true);
		this.outClientManager = SVNClientManager.newInstance(options, authManager);
	}

	public SVNRepository getRepository() {
		return repository;
	}

	private void setupLibrary() {
		// 对于使用http://和https：//
		DAVRepositoryFactory.setup();
		// 对于使用svn：/ /和svn+xxx：/ /
		SVNRepositoryFactoryImpl.setup();
		// 对于使用file://
		FSRepositoryFactory.setup();
	}

	private boolean login() {
		setupLibrary();
		try {
			// 创建库连接
			repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(this.url));
			// 身份验证
			this.authManager = SVNWCUtil.createDefaultAuthenticationManager(this.usrName, this.password);
			// 创建身份验证管理器
			repository.setAuthenticationManager(authManager);
			return true;
		} catch (SVNException svne) {
			svne.printStackTrace();
			return false;
		}
	}
}
