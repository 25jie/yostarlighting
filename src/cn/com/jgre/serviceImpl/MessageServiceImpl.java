package cn.com.jgre.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import cn.com.jgre.dao.MessageDao;
import cn.com.jgre.entity.Messageinfo;

import cn.com.jgre.service.MessageService;





public class MessageServiceImpl implements MessageService{

	MessageDao messageDao;
	public int addMessageinfo(Messageinfo messageinfo) {

		return messageDao.insertRecord(messageinfo);
	}

	public int deleteMessage(String id) {
		
		return messageDao.deleteRecordById(id);
	}

	@SuppressWarnings("unchecked")
    public List<Messageinfo> getAllMesages(Messageinfo messageinfo) {
	
		return messageDao.getRecords(messageinfo);
	}

	public Messageinfo getMessageinfo(String id) {
		
		return (Messageinfo) messageDao.getRecordById(id);
	}

	@SuppressWarnings("unchecked")
    public List<Messageinfo> getMessages(Messageinfo messageinfo, int offset,
			int limit) {
		RowBounds rb=new RowBounds(offset,limit);
		return messageDao.getRecords(messageinfo, rb);
	}
	 @Override
	    public int updateMessageinfo(Messageinfo messageinfo) {
	       
	        return messageDao.updateRecord(messageinfo);
	    }
	    
	public MessageDao getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

    @Override
    public int getTotalMessageinfo(Messageinfo messageinfo) {
       
        return messageDao.getTotalRecord(messageinfo);
    }

   
}
