package cn.mldn.util.service.abs;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractService {
	public Map<String, Object> paramConverterMap(long currentPage, int lineSize, String column, String keyWord) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("start", (currentPage - 1) * lineSize) ;
		map.put("lineSize", lineSize) ;
		if (!(column == null || "".equals(column))) { 
			map.put("column", column) ;
		}
		if (!(keyWord == null || "".equals(keyWord))) { 
			map.put("keyWord", "%" + keyWord + "%") ;
		}
		return map ;
	}
} 
