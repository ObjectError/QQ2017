package com.QQ.DAO;

import java.util.Vector;

import com.QQ.beans.Account;

public interface AccountDAO {
	public String saveAccount(Account acc);
	public Account UpdatAccount(Account acc);
	public Account lonin(Account account);
	public Vector<Account> getmyfriend(int myQQcode);
	public void moveGroup(int myQQcode,int friendQQcode,String groupName);
	public Vector<Vector> findFriend(final String sql);
	public Account getSelectedFriend(int myQQcode);
	
	public boolean isFriend(int myQQcode,int friendqqcode);
	public void addFriend(int myqqcode,int friendqqcode);
	public void changeStatus(int qqcode,String status);
	public void delFriend(int myqqcode,int friendqqcode);
	public  Account Onefriend(int QQcode);
	public void StatuFriend(int myqqcode,int friendqqcode,int cmd,String ffige);
	public  Vector readfriend(int friendqqcode);
	public void deloffmsg(int qqcode);
	//找回密码
	public Account zhmm(Account a);
}
