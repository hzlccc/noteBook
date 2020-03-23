package com.huayu.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayu.bean.user.Shgx;
import com.huayu.bean.user.Srjl;
import com.huayu.bean.user.User;
import com.huayu.user.mapper.UserMapper;
import com.huayu.utils.SplitUtil;

@Service
public class AnalogDataService {

	@Autowired
	private  UserMapper userMapper;
	
	
	private String[] address = {"曹县青古集胡庄","单县高韦庄","单县浮岗","单县浮岗王许庄","曹县青古集","曹县青古集周庄"};
	private String[] names = {"种翠翠","胡智龙","胡智航","胡智勇","种灵婧","种献民","王玉兰","王爱青","胡凤喜","种献忠"};
	
	private String[] provinces = {"山东省","河北省","广东省","河南省"};
	private Map<String, String[]> citys = new HashMap<String, String[]>();
	private Map<String, String[]> regions = new HashMap<String, String[]>();

	
	

	 List<Shgx> shgxs = new ArrayList<Shgx>();
	 List<User>  users = null;
	 List<Srjl> srjls = new ArrayList<Srjl>();
	public  void analog() {
			init();
			users = geneterUser();
			
			List<List<User>> usersList = SplitUtil.splitUser(users, 1000);
			for (List<User> list : usersList) {
				userMapper.insertUser(list);
			}
			
			List<List<Shgx>> shgxsList = SplitUtil.splitShgx(shgxs, 1000);
			for (List<Shgx> list : shgxsList) {
				userMapper.insertShgx(list);

			}
			
			List<List<Srjl>> srjlsList = SplitUtil.splitSrjl(srjls, 1000);
			for (List<Srjl> list : srjlsList) {
				userMapper.insertSrjl(list);
			}
			shgxs.clear();
			users.clear();
		
		
	}
/**
 * 初始化省市区信息
 */
	private void init() {
		String[] sdcitys = {"济南市","青岛市","潍坊市","菏泽市"};
		String[] hbcitys = {"廊坊市","石家庄市","沧州市"};
		String[] hncitys = {"郑州市","信阳市","商丘市"};
		String[] gdcitys = {"广州市","深圳市","珠海市"};
		
		String[] jnregions = {"槐荫区","历下区","长清区","莱芜区"};
		String[] qdregions = {"即墨区","崂山区","黄岛区"};
		String[] wfregions = {"寿光","奎文区","坊子区","青州市"};
		String[] hzregions = {"曹县","单县","成武","巨野县"};
		String[] lfregions = {"永清县","霸州市"};
		String[] sjzregions = {"赵县","元氏县","平山县"};
		String[] czregions = {"献县","东光县","南皮县"};
		String[] zzregions = {"新郑市","中原区","中牟县"};
		String[] xyregions = {"商城县","新县","固始县"};
		String[] sqregions = {"民权县","睢阳区","梁园区"};
		String[] gzregions = {"花都区","白云区","从化区","黄浦区"};
		String[] szregions = {"龙岗区","龙华区","光明区"};
		String[] zhregions = {"香洲区","斗门区","金湾区"};


		citys.put("山东省", sdcitys);
		citys.put("河北省", hbcitys);
		citys.put("河南省", hncitys);
		citys.put("广东省", gdcitys);
		
		
		regions.put("济南市", jnregions);
		regions.put("青岛市", qdregions);
		regions.put("潍坊市", wfregions);
		regions.put("菏泽市", hzregions);
		regions.put("廊坊市", lfregions);
		regions.put("石家庄市", sjzregions);
		regions.put("沧州市", czregions);
		regions.put("郑州市", zzregions);
		regions.put("信阳市", xyregions);
		regions.put("商丘市", sqregions);
		regions.put("广州市", gzregions);
		regions.put("深圳市", szregions);
		regions.put("珠海市", zhregions);
	}

	private  List<User> geneterUser() {
		List<User> users = new ArrayList<User>();
		
		for (int i = 1; i <= 1000; i++) {
			String uuid = UUID.randomUUID().toString().replace("-", "");
			
			
			geneterShgx(uuid);
			geneterShgx(uuid);
			geneterShgx(uuid);
			geneterShgx(uuid);
			
			geneterSrjl(uuid);
			geneterSrjl(uuid);
			geneterSrjl(uuid);
			geneterSrjl(uuid);


			User user = new User();
			user.setCId(uuid);
			user.setCDesc("谁都会发生的就");
			user.setPassWord("123");
			user.setRealName(getName(names));
			user.setSex("男");
			user.setUserName("hzl");
			
			String province = this.getProvince();
			String city = this.getCity(province);
			String region = this.getRegion(city);
			
			user.setCProvince(province);
			user.setCCity(city);
			user.setCRegion(region);
			
			user.setCAddress(province+city+region);
			user.setDAge((int)(Math.random()*100+1));
			users.add(user);
		}
		
		return users;
	}

	

	private void geneterSrjl(String userId) {
		
		String uuid = UUID.randomUUID().toString().replace("-", "");
		Srjl srjl = new Srjl();
		srjl.setCID(uuid);
		srjl.setCUserID(userId);
		srjl.setDSrje(100);
		srjls.add(srjl);
	}

	private  void geneterShgx(String userId) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		Shgx shgx = new Shgx();
		shgx.setCId(uuid);
		shgx.setCUserId(userId);
		shgx.setCAddress(getAddress(address));
		shgx.setCName(getName(names));
		shgx.setCSex("男");
		int age = (int)(Math.random()*100+1);
		shgx.setDAge(age);
		shgxs.add(shgx);
	}
	
	private String getAddress(String[] address){
		int index = new Random().nextInt(address.length);
		return address[index];
	}
	
	
	private String getProvince(){
		int index = new Random().nextInt(provinces.length);
		return provinces[index];
	}
	
	private String getCity(String province){
		String[] cityAddress = citys.get(province);
		int index = new Random().nextInt(cityAddress.length);
		return cityAddress[index];
	}
	
	
	private String getRegion(String city){
		String[] regionsAddress = regions.get(city);
		int index = new Random().nextInt(regionsAddress.length);
		return regionsAddress[index];
	}
	
	private String getName(String[] names){
		int index = new Random().nextInt(names.length);
		return names[index];
	}
}
