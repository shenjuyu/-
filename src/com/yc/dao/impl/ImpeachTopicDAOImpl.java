package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.commons.DbHepler;
import com.yc.dao.IImpeachTopicDAO;
import com.yc.po.ImpeachTopicPO;
import com.yc.po.ReplyPO;
import com.yc.vo.TopicVO;

public class ImpeachTopicDAOImpl implements IImpeachTopicDAO {
	
	DbHepler db=new DbHepler();

	@Override
	public int addImpeachTopic(ImpeachTopicPO po) throws Exception {
		String sql="insert into bbs_impeachtopic values(null,?,?,?,?,?,1)";
		List<Object> params=new ArrayList<Object>();
		params.add(po.getTi_uid());
		params.add(po.getTi_uname());
		params.add(po.getTi_tid());
		params.add(po.getTi_title());
		params.add(po.getTi_reason());
		return db.update(sql, params);
	}

	@Override
	public List<ImpeachTopicPO> findByPage(ImpeachTopicPO po, Integer pageNum, Integer pageSize) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select ti_id,ti_uid,ti_uname,ti_tid,ti_title,ti_reason,ti_state from bbs_impeachtopic where 1=1 ");
		List<Object> params=null;
		if(null!=po){
			params=new ArrayList<Object>();
			if(null!=po.getTi_id()){
				params.add(po.getTi_id());
				sb.append(" and ti_id=? ");
			}
			if(null!=po.getTi_uid()){
				params.add(po.getTi_uid());
				sb.append(" and ti_uid=? ");
			}
			if(null!=po.getTi_uname()){
				params.add(po.getTi_uname());
				sb.append(" and ti_uname=? ");
			}
			if(null!=po.getTi_tid()){
				params.add(po.getTi_tid());
				sb.append(" and ti_tid=? ");
			}
			if(null!=po.getTi_title()){
				params.add(po.getTi_title());
				sb.append(" and ti_title=? ");
			}
			if(null!=po.getTi_reason()){
				params.add(po.getTi_reason());
				sb.append(" and ti_reason=? ");
			}
			if(null!=po.getTi_state()){
				params.add(po.getTi_state());
				sb.append(" and ti_state=? ");
			}
		}
		sb.append(" order by ti_id asc ");
		if(null!=pageSize && null!=pageNum){
			sb.append(" limit "+(pageNum-1)*pageSize+","+pageSize);
		}
		return db.findMutil(sb.toString(), params, ImpeachTopicPO.class);
	}

	@Override
	public int totalPage(ImpeachTopicPO po) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select count(*) from bbs_impeachtopic where 1=1 ");
		List<Object> params=null;
		if(null!=po){
			params=new ArrayList<Object>();
			if(null!=po.getTi_id()){
				params.add(po.getTi_id());
				sb.append(" and ti_id=? ");
			}
			if(null!=po.getTi_uid()){
				params.add(po.getTi_uid());
				sb.append(" and ti_uid=? ");
			}
			if(null!=po.getTi_uname()){
				params.add(po.getTi_uname());
				sb.append(" and ti_uname=? ");
			}
			if(null!=po.getTi_tid()){
				params.add(po.getTi_tid());
				sb.append(" and ti_tid=? ");
			}
			if(null!=po.getTi_title()){
				params.add(po.getTi_title());
				sb.append(" and ti_title=? ");
			}
			if(null!=po.getTi_reason()){
				params.add(po.getTi_reason());
				sb.append(" and ti_reason=? ");
			}
			if(null!=po.getTi_state()){
				params.add(po.getTi_state());
				sb.append(" and ti_state=? ");
			}
		}
		return (int)db.getPromer(sb.toString(), params);
	}

	@Override
	public int updateImpeachTopic(ImpeachTopicPO po) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("update bbs_impeachtopic set ti_id=ti_id ");
		List<Object> params=null;
		if(null!=po){
			params=new ArrayList<Object>();
			if(null!=po.getTi_state()){
				params.add(po.getTi_state());
				sb.append(" ,ti_state=? ");
			}
		}
		if(null!=po){
			if(null!=po.getTi_id()){
				params.add(po.getTi_id());
				sb.append(" where ti_id=? ");
			}
		}
		if(po.getTi_state()==3) {
			return db.update(sb.toString(), params);
		}
		
		String sql =" delete from bbs_reply  where  t_id =?  ";
		String sql1 =" delete from bbs_topic   where  t_id =?  ";
		
		String sql_findReply = " select r_content,r_id ,u_id, t_id from bbs_reply WHERE t_id =? ";
		List<Object> paramsFindReply = new ArrayList<Object>();
		paramsFindReply.add(po.getTi_tid());
		List<ReplyPO> reply=db.findMutil(sql_findReply, paramsFindReply, ReplyPO.class);
		if(null==reply||reply.size()<=0){
			List< List<Object> > params2= new ArrayList<List<Object>>();
			List<String>sqls =new ArrayList<String>();
			sqls.add(sb.toString());
			sqls.add(sql1);
			
			List<Object> params1=new ArrayList<Object>();
			params1.add(po.getTi_tid());
			return db.update(sqls, params2);
		}else{
			List<String>sqls =new ArrayList<String>();
			List< List<Object> > params1= new ArrayList<List<Object>>();
			sqls.add(sb.toString());
			sqls.add(sql);
			sqls.add(sql1);
			
			List<Object> list=new ArrayList<Object>();
			list.add(po.getTi_tid());
			
			params1.add(params);
			params1.add(list);
			params1.add(list);
			
			return db.update(sqls, params1);
		}
	}

}
