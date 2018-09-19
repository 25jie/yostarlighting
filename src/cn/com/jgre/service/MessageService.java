package cn.com.jgre.service;

import java.util.List;

import cn.com.jgre.entity.Messageinfo;







public interface MessageService {

	/*
	 * 获取所有留言
	 */
	public List<Messageinfo> getAllMesages(Messageinfo messageinfo);
	
	/*
	 * page
	 */
	public List<Messageinfo> getMessages(Messageinfo messageinfo, int offset, int limit);
	
	/*
	 * 根据id删除
	 */
	public int deleteMessage(String id);
	
	/*
	 * 根据id获取
	 */
	public Messageinfo getMessageinfo(String id);
	
	/*
	 * 添加评论
	 */
	public int addMessageinfo(Messageinfo messageinfo);
	
	
	public int updateMessageinfo(Messageinfo messageinfo);
	
	/*
	 * 获取总数
	 */
	public int getTotalMessageinfo(Messageinfo messageinfo);
}
