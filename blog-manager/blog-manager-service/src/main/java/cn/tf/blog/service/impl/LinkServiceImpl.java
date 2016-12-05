package cn.tf.blog.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tf.blog.mapper.LinkDao;
import cn.tf.blog.po.ULink;
import cn.tf.blog.service.LinkService;



/**
 * 友情链接Service实现类
 * @author Administrator
 *
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService{

	@Resource
	private LinkDao linkDao;
	
	public int add(ULink link) {
		link.setLinkId(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		return linkDao.add(link);
	}

	public int update(ULink link) {
		return linkDao.update(link);
	}

	public List<ULink> list(Map<String, Object> map) {
		return linkDao.list(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return linkDao.getTotal(map);
	}

	public Integer delete(String id) {
		return linkDao.delete(id);
	}

}
