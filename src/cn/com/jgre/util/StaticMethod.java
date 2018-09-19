package cn.com.jgre.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






/**
 * 这个里面写一些静态方法
 * 
 * @author liuyingxiang
 * 
 */
public class StaticMethod {
	
	
	public static void main(String[] args) {
		System.out.println(StaticMethod.getMD5("root"));
	}
	/**
	 * 判断一个字符串是不是数字
	 * 
	 * @author jw
	 */
	public static int getInteger(String strNum) {
		try {
			if (strNum != null) {
				int x = Integer.parseInt(strNum);
				return x;
			}
		} catch (Exception e) {
			return -1;
		}
		return -1;
	}
	
	/**
	 * 将list转成以,分开的字符串
	 * 
	 * @param list
	 * @return
	 */
	public static String getStrings(List<?> list){
		String ids = "";
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object obj = list.get(i);
				if (ids.length() == 0) {
					ids += String.valueOf(obj);
				} else {
					ids += "," + String.valueOf(obj);
				}
			}
		}
		return ids;
	}
	
	/**
	 * 将list转成以,分开的字符串
	 * 
	 * @return
	 */
	public static String getStrings(Set<?> set){
		String ids = "";
		if (set != null && set.size() > 0) {
			for (Object obj:set) {
				if (ids.length() == 0) {
					ids += String.valueOf(obj);
				} else {
					ids += "," + String.valueOf(obj);
				}
			}
		}
		return ids;
		
	}
	
	/**
	 * list类型的id转化为string id1,id2,id3
	 * 
	 * @param list
	 * @return
	 */
	public static String getIds(List<?> list) {
		String ids = "";
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int id = (Integer) list.get(i);
				if (ids.length() == 0) {
					ids += id;
				} else {
					ids += "," + id;
				}
			}
		}
		return ids;
	}

	/**
	 * 截取字符串 汉字占2个字符
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static String subString(String str, int length) {
		String substr = "";
		int len = 0;
		if (str == null || str.length() <= 0) {
			return "";
		}
		str = str.trim();
		char c;
		for (int i = 0; i < str.length(); i++) {
			if (len >= length)
				break;
			c = str.charAt(i);
			if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
				// 字母, 数字
				len++;
			} else {
				if (Character.isLetter(c)) { // 中文
					len += 2;
				} else { // 符号或控制字符
					len++;
				}
			}
			substr += str.substring(i, i + 1);
		}
		return substr;
	}

	public static String getDomain(String url) {
		String url_p = "[\\w-]+\\.[\\w-]+\\.(com|net|org|gov|cc|biz|info|cn)(\\.(cn|hk))*";
		if (url != null && url.length() > 0) {
			Pattern pattern = Pattern.compile(url_p, Pattern.CASE_INSENSITIVE); // 第二个参数为不区分大小写
			Matcher matcher = pattern.matcher(url);
			if (matcher.find()) {
				return matcher.group();
			}
		}
		return "";
	}

	public static short getUrltype(String address) {
		String url_build = "(build|news|traffic|info|housetype|price|props|pic)/\\d{1,3}/\\d{1,9}\\.html";
		String url_house = "house/\\d{1,3}/\\d{1,9}\\.html";
		if (address != null && address.length() > 0) {
			Pattern pattern = Pattern.compile(url_build, Pattern.CASE_INSENSITIVE); // 第二个参数为不区分大小写
			Matcher matcher = pattern.matcher(address);
			if (matcher.find()) {
				return 1;
			}
			pattern = Pattern.compile(url_house, Pattern.CASE_INSENSITIVE); // 第二个参数为不区分大小写
			matcher = pattern.matcher(address);
			if (matcher.find()) {
				return 2;
			}
		}
		return 0;
	}

	public static String removeInterpunction(String str) {
		String str_p = "(,|、|，){2,30}";
		Pattern pattern = Pattern.compile(str_p, Pattern.CASE_INSENSITIVE); // 第二个参数为不区分大小写
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			str = matcher.replaceAll("、");
		}
		str_p = "(,|、|，){1,2}$";
		pattern = Pattern.compile(str_p, Pattern.CASE_INSENSITIVE); // 第二个参数为不区分大小写
		matcher = pattern.matcher(str);
		if (matcher.find()) {
			str = matcher.replaceAll("");
		}
		return str;
	}

	/**
	 * 判断是否非法email
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		String email_p = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern pattern = Pattern.compile(email_p); // 第二个参数为不区分大小写
		Matcher matcher = pattern.matcher(email);
		return matcher.find();
	}

	/**
	 * 标准的调用的MD5加密
	 * 
	 * @param value
	 * @return
	 */
	public static String getMD5(String value) {
		return getMD5(value.getBytes());
	}

	/**
	 * 标准的调用的MD5加密
	 * 
	 * @param value
	 * @return
	 */
	public static String getMD5(byte[] value) {
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(value);
			result = toHex(md.digest());
		} catch (NoSuchAlgorithmException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	public static String toHex(byte[] buffer) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
		}
		return sb.toString();
	}

	/**
	 * 去掉收尾空格后是否为空?
	 * 
	 * @param astr
	 * @return
	 */
	public static boolean isTrimEmpty(String astr) {
		if ((null == astr) || (astr.length() == 0)) {
			return true;
		}
		if (isBlank(astr.trim())) {
			return true;
		}
		return false;
	}

	/** */
	/**
	 * 字符串是否为空:null或者长度为0.
	 */
	public static boolean isBlank(String astr) {
		if ((null == astr) || (astr.length() == 0)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return 字符串，将src的第一个字母转换为大写，src为空时返回null
	 */
	public static String change(String str) {
		if (str != null) {
			StringBuffer sb = new StringBuffer(str);
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
			return sb.toString();
		} else {
			return null;
		}
	}

	/**
	 * 是大小图的情况下改变
	 * 
	 * @param path
	 * @param type
	 * @return
	 */
	public static String changePathTemp(String path, String type) {
		if (path != null) {
			int index = path.lastIndexOf("_");
			if (index >= 0) {
				return path.substring(0, index + 1) + type + path.substring(index + 2);
			}
		}
		return "";
	}

	/**
	 * 获得原文件
	 * 
	 * @param path
	 * @return
	 */
	public static String changePath(String path) {
		if (path != null) {
			int index = path.lastIndexOf("_");
			if (index >= 0) {
				return path.substring(0, index) + path.substring(index + 2);
			}
		}
		return "";
	}

	/**
	 * 原图获得大图小图
	 * 
	 * @param path
	 * @param type
	 * @return
	 */
	public static String changePath(String path, String type) {
		if (path != null) {
			int index = path.lastIndexOf(".");
			if (index >= 0) {
				return path.substring(0, index) + "_" + type + path.substring(index);
			}
		}
		return "";
	}

	/**
	 * 字符串转int
	 * 
	 * @param str
	 * @return
	 */
	@SuppressWarnings("finally")
	public static int parseInt(String str) {
		int value = 0;
		try {
			value = Integer.parseInt(str);
		} catch (Exception e) {
			// Do nothing
		} finally {
			return value;
		}
	}

	/**
	 * 获得城市区域配置的索引
	 * 
	 * @param obj
	 * @param value
	 * @return
	 */
	public static int getAreaconfig(Object[][] obj, int value) {
		if (obj != null && obj.length > 0) {
			for (int i = 0; i < obj.length; i++) {
				if ((Integer) obj[i][0] == value) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * 通过城市ID获得城市中文名称
	 * 
	 * @param value
	 * @return
	 */
	public static String getCityName(int value) {
		
		return "";
	}

	/**
	 * @param obj
	 * @param value
	 * @return
	 */

	public static int getAreaconfig(String[] obj, String value) {
		if (obj != null && obj.length > 0) {
			for (int i = 0; i < obj.length; i++) {
				if ((obj[i]).equals(value)) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * 获得城市区域配置的索引
	 * 
	 * @param obj
	 * @param value
	 * @return
	 */
	public static int getAreaconfig(Object[][] obj, Object value, int place) {
		if (obj != null && obj.length > 0) {
			for (int i = 0; i < obj.length; i++) {
				if ((obj[i][place]).equals(value)) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * 获得区域id
	 * 
	 * @return
	 */
	public static int getTypeId(String filter, String type) {
		String f_values[] = filter.split("q");
		if (f_values != null && f_values.length > 0) {
			for (int i = 0; i < f_values.length; i++) {
				if (f_values[i].length() > 0 && f_values[i].substring(0, 1).equals(type)) {
					return StaticMethod.parseInt(f_values[i].substring(1, f_values[i].length()));
				}
			}
		}
		return 0;
	}

	/**
	 * 半角转全角
	 * 
	 * @param input
	 *            String.
	 * @return 全角字符串.
	 */
	public static String ToSBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == ' ') {
				c[i] = '\u3000';
			} else if (c[i] < '\177') {
				c[i] = (char) (c[i] + 65248);
			}
		}
		return new String(c);
	}

	/**
	 * 全角转半角
	 * 
	 * @param input
	 *            String.
	 * @return 半角字符串
	 */
	public static String ToDBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);
			}
		}
		String returnString = new String(c);
		return returnString;
	}

	/**
	 * 获得json字符串
	 * 
	 * @param list
	 * @return
	 */
	public static String getJsonValue(List<?> list) {
		String json = "{\"json\":[";
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				json += "{\"value\":\"" + list.get(i) + "\"}";
				if (i < list.size() - 1) {
					json += ",";
				}
			}
		}
		json += "]}";
		return json;
	}

	/**
	 * 获得json字符串
	 * 
	 * @param list
	 * @return
	 */
	public static String getJsonKeyValue(List<?> list, int key, int name) {
		String json = "{\"json\":[";
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				json += "{\"id\":\"" + obj[key] + "\", \"name\":\"" + obj[name] + "\"}";
				if (i < list.size() - 1) {
					json += ",";
				}
			}
		}
		json += "]}";
		return json;
	}

	/**
	 * 获得个数
	 * 
	 * @param list
	 * @param bid
	 * @return
	 */
	public static int getTypeNum(List<?> list, int bid) {
		if (list != null && list.size() > 0) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				Object[] obj = (Object[]) list.get(i);
				if ((Integer) obj[0] == bid) {
					return (Integer) obj[1];
				}
			}
		}
		return 0;
	}

	/**
	 * 获得文本编辑器里面内容的页数
	 * 
	 * @param content
	 * @return
	 */
	public static int getPageNum(String content) {
		String[] str = content.split("<!-- pagebreak -->");
		return str.length;
	}

	/**
	 * 判断某个字符串是否含有某个字符
	 * 
	 * @param strs
	 * @param s
	 * @return
	 */
	public static boolean isHave(String strs, String s) {
		/*
		 * 此方法有两个参数，第一个是要查找的字符串数组，第二个是要查找的字符或字符串
		 */
		// for(int i=0;i<strs.length();i++){
		if (strs.indexOf(s) != -1) {// 循环查找字符串数组中的每个字符串中是否包含所有查找的内容
			return true;// 查找到了就返回真，不在继续查询
		}
		// }
		return false;// 没找到返回false
	}

	/**
	 * 由list列表获得id的字符串列表
	 * 
	 * @param list
	 * @return
	 */
	public static String getIdsStr(List<?> list) {
		String result = "";
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				result += (Integer) list.get(i);
				if (i < list.size() - 1) {
					result += ",";
				}
			}
		}
		return result;
	}


	/**
	 * 把ip地址转化成long型
	 * 
	 * @param ipAddress
	 * @return
	 */
	public static long getLongIp(String ipAddress) {
		String[] str = ipAddress.split("\\.");
		String result = "0";
		for (int i = 0; i < str.length; i++) {
			if (str[i].length() == 3) {
				result += str[i];
			} else if (str[i].length() == 2) {
				result += "0" + str[i];
			} else if (str[i].length() == 1) {
				result += "00" + str[i];
			}
		}
		return Long.parseLong(result);
	}

	/**
	 * 过滤所有HTML标签 去除所有含英文字母的<>;
	 */
	public static String removeAllHTML(String info) {
		if (info == null || info.equals("")) {
			return info;
		}
		Pattern p_html;
		Matcher m_html;
		String regEx_html = "<\\s*/?[a-z][^>]*?>|<\\s*!--|-->";
		p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		m_html = p_html.matcher(info);
		info = m_html.replaceAll(""); // 过滤html标签
		return info;
	}

	/**
	 * 移除特殊字符
	 * 
	 * @param str
	 * @return
	 */
	public static String removestr(String str) {
		if (str != null && str.length() > 0) {
			str = str.replaceAll("[‘’'“”;；]*", "");
		}
		return str;
	}

	/**
	 * 从字符串中获取中文
	 * 
	 * @param content
	 * @return
	 */
	public static String getCnFromStr(String content) {
		if (content == null) {
			return "";
		}
		String ret = "";
		for (int i = 0; i < content.length(); i++) {
			String s = content.charAt(i) + "";
			byte[] by = s.getBytes();
			if (by.length >= 2) {// 这里就是判断单个string是否是中文
				ret += new String(by);
			}
		}
		return ret;
	}

	/**
	 * 获取一个对象的所有字段 格式:columns1,columns2,...columns100
	 * 
	 * @param o
	 * @return
	 */
	public synchronized static String getColumns(Object o) {
		Class entityClass = (Class) o.getClass();
		Field[] fields = entityClass.getDeclaredFields();
		String columns = "";
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true); // 设置些属性是可以访问的
			if (!field.getName().equals("serialVersionUID")) {
				columns += field.getName();
				if (i < fields.length - 1) {
					columns += ",";
				}
			}
		}
		columns = columns.replace("serialVersionUID,", "");
		return columns;
	}

	/**
	 * 数组转对象
	 * 
	 * @param list
	 *@param entity
	 *@return
	 *@author jwjw233233@163.com 2014-6-17
	 */
	public static List getEntityList(List<Object[]> list, Class entity) {
		List<Object> ret = new ArrayList();
		Field[] fields = entity.getDeclaredFields();
		try {
			for (int j = 0; j < list.size(); j++) {
				Object[] values = list.get(j);
				Object object = entity.newInstance();
				int total = 0;
				for (int i = 0; i < fields.length; i++) {
					Field field = fields[i];
					if (!field.getName().equals("serialVersionUID")) {
						Method me = entity.getDeclaredMethod("set"
								+ field.getName().substring(0, 1).toUpperCase()
								+ field.getName().substring(1), new Class[] { field.getType() });
						me.invoke(object, values[total]);
						total++;
					}
				}
				ret.add(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}


	public static int getInt(Object obj) {
		if (obj == null)
			return 0;
		else
			return Integer.parseInt(obj.toString());
	}

	public static String getString(Object obj) {
		if (obj == null)
			return "";
		else
			return obj.toString();
	}

	/**
	 * 对象转map description 2014-7-8
	 * 
	 * @author jwjw233233@163.com
	 */
	public static Map<String, Object> convertBeanToMap(Object bean) {
		Field[] fields = bean.getClass().getDeclaredFields();
		HashMap<String, Object> data = new HashMap<String, Object>();
		try {
			for (Field field : fields) {
				if (!field.getName().equals("serialVersionUID")) {
					field.setAccessible(true);
					data.put(field.getName(), field.get(bean));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public static void write(HttpServletResponse response, String result) {
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().print(result);
		} catch (Exception e) {
		}
	}

	/**
	 * 获取固定位数随机数
	 * 
	 * @param length
	 * @return
	 */
	public static String getCode(int length) {
		Random rand = new Random();
		String min = "1";
		String max = "9";
		for (int i = 0; i < length - 1; i++) {
			min += "0";
			max += "9";
		}
		int tmp = Math.abs(rand.nextInt());
		int rst = tmp % (Integer.parseInt(max) - Integer.parseInt(min) + 1) + Integer.parseInt(min);
		return String.valueOf(rst);
	}


	/**
	 * 设置对象属性
	 * 
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws IntrospectionException
	 */
	public static Object setObjProperties(Object obj, String name, Object value) {
		try {
			PropertyDescriptor pd = new PropertyDescriptor(name, obj.getClass());// 参数1为person类中属性的名（首字母大写），参数2为person类转化的Class
			Method m = pd.getWriteMethod();// 获取PropertyDescriptor 类的写入方法
			String data_type = pd.getPropertyType().toString();// 获取参数在Class中的类型
			if (value == null) {
				;
				value = "";
			}
			if (data_type.equals("int")) {
				m.invoke(obj, Integer.parseInt(value.toString()));
			} else if (data_type.equals("short")) {
				m.invoke(obj, Short.parseShort(value.toString()));
			} else if (data_type.equals("double")) {
				m.invoke(obj, Double.parseDouble(value.toString()));
			} else if (data_type.equals("long")) {
				m.invoke(obj, Long.getLong(value.toString()));
			} else if (data_type.equals("class java.util.Date")) {
				if (value != null && !"".equals(value)) {
					m.invoke(obj, value);
				} else {
					m.invoke(obj, null);
				}
			} else {
				m.invoke(obj, (value == null ? "" : value));
			}
		} catch (Exception e) {
		} finally {
			return obj;
		}

	}

	/**
	 * 手机号码验证
	 */
	public static boolean checkMobile(String mobile) {
		return mobile.matches("^1[3578]\\d{9}$");
	}

	/**
	 * 座机验证
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean checkMobileNew(String mobile) {
		return mobile
				.matches("((0\\d{3})?\\d{7,8}||(010|020|021|022|023|024|025|026|027|028|029|852)\\d{8})");
	}



	public static String consParams(HashMap<String, Object> map) {
		Iterator iter = map.entrySet().iterator();
		StringBuffer sb = new StringBuffer();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();

			sb.append(entry.getKey());
			sb.append("=");

			try {
				if (entry.getKey().equals("signature")) {
					sb.append(entry.getValue());
				} else {
					sb
							.append(URLEncoder.encode(entry.getValue() + "", "utf-8").replace("+",
									"%20"));
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if (iter.hasNext()) {
				sb.append("&");
			}
		}
		return sb.toString();
	}

	 public static String getIpAddr(HttpServletRequest request){  
	        String ip = request.getHeader("X-Real-IP");  
	        if (!StaticMethod.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {  
	            return ip;  
	        }  
	        ip = request.getHeader("X-Forwarded-For");  
	        if (!StaticMethod.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {  
	            // 多次反向代理后会有多个IP值，第一个为真实IP。  
	            int index = ip.indexOf(',');  
	            if (index != -1) {  
	                return ip.substring(0, index);  
	            } else {  
	                return ip;  
	            }  
	        } else {  
	            return request.getRemoteAddr();  
	        }  
	    }  
	
	public static String getResult(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);

			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(),"utf-8"));
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
			}
		}
		return result;
	}

	/**
	 * 将一个 Map 对象转化为一个 JavaBean
	 * 
	 * @param type
	 *            要转化的类型
	 * @param map
	 *            包含属性值的 map
	 * @return 转化出来的 JavaBean 对象
	 * @throws IntrospectionException
	 *             如果分析类属性失败
	 * @throws IllegalAccessException
	 *             如果实例化 JavaBean 失败
	 * @throws InstantiationException
	 *             如果实例化 JavaBean 失败
	 * @throws InvocationTargetException
	 *             如果调用属性的 setter 方法失败
	 */
	public static Object convertMap(Class type, Map map) throws IntrospectionException,
			IllegalAccessException, InstantiationException, InvocationTargetException {
		BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
		Object obj = type.newInstance(); // 创建 JavaBean 对象

		// 给 JavaBean 对象的属性赋值
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();

			if (map.containsKey(propertyName)) {
				// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
				Object value = map.get(propertyName);

				Object[] args = new Object[1];
				args[0] = value;

				descriptor.getWriteMethod().invoke(obj, args);
			}
		}
		return obj;
	}
	
	/**
	 * 如果是windwos系统，本地略过验证码步骤 windows 0,linux 1
	 */
	public static int getOSID() {
		String osname = System.getProperty("os.name");
		if (osname != null && "Linux".equals(osname)) {
			return 1;
		} else {
			return 0;
		}
	}
	/**
	 * 过滤非法字符
	 * @param str1 需要处理的字符
	 * @param str2 用户替换非法字符
	 * @return
	 */
	public static String Strfilter(String str1,String str2){
		String chinaStr = "[\u4E00-\u9FA5]";
		String engStr = "[a-z A-Z]";
		String numStr = "[0-9]";
		String regEx="[`~_!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？-]";
		StringBuffer buffer = new StringBuffer();
		if(str1==null)return null;
		for(String s:str1.split("")){
			if(s.equals(""))continue;
			if(s.matches(chinaStr) || s.matches(engStr)|| s.matches(numStr)|| s.matches(regEx) || s.equals(" ")){
				buffer.append(s);
			}else{
				buffer.append(str2==null?"":str2);
			}
		}
		return buffer.toString();
	}
	
}
