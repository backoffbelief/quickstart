package com.kael.mvc.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kael.mapper.OrdersMapper;
import com.kael.model.Orders;
import com.kael.model.Person;
import com.kael.model.Person0;
import com.kael.model.User;
import com.kael.service.UserService;

@Controller
@RequestMapping("/quick")
public class QuickStartController {
	/** 日志实例 */
    private static final Logger logger = Logger.getLogger(QuickStartController.class);
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private OrdersMapper ordersMapper;
 
    @RequestMapping(value = "/hello", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String hello() {
        return "你好！hello";
    }
 
    @RequestMapping(value = "/say/{msg}", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String say(@PathVariable(value = "msg") String msg) {
        return "{\"msg\":\"you say:'" + msg + "'\"}";
    }
 
    @RequestMapping(value = "/person/{id:\\d+}", method ={ RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    Person0 getPerson(@PathVariable("id") int id) {
        logger.info("获取人员信息id=" + id);
        Person0 person = new Person0();
        person.setName("张三");
        person.setSex("男");
        person.setAge(30);
        person.setId(id);
        return person;
    }
 
    @RequestMapping(value = "/delete/person/{id:\\d+}", method = { RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    Object deletePerson(@PathVariable("id") int id) {
        logger.info("删除人员信息id=" + id);
        Map<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("msg", "删除人员信息成功");
        return jsonObject;
    }
 
    @RequestMapping(value = "/person", method = { RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    Object addPerson(Person0 person) {
        logger.info("注册人员信息成功id=" + person.getId());
        Map<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("msg", "注册人员信息成功");
        return jsonObject;
    }
 
    @RequestMapping(value = "/update/person", method = { RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    Object updatePerson(Person0 person) {
        logger.info("更新人员信息id=" + person.getId());
        Map<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("msg", "更新人员信息成功");
        return jsonObject;
    }
 
    @RequestMapping(value = "/person/list", method = { RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    List<Person0> listPerson(@RequestParam(value = "name", required = false, defaultValue = "") String name) {
 
        logger.info("查询人员name like " + name);
        List<Person0> lstPersons = new ArrayList<Person0>();
 
        Person0 person = new Person0();
        person.setName("张三");
        person.setSex("男");
        person.setAge(25);
        person.setId(101);
        lstPersons.add(person);
 
        Person0 person2 = new Person0();
        person2.setName("李四");
        person2.setSex("女");
        person2.setAge(23);
        person2.setId(102);
        lstPersons.add(person2);
 
        Person0 person3 = new Person0();
        person3.setName("王五");
        person3.setSex("男");
        person3.setAge(27);
        person3.setId(103);
        lstPersons.add(person3);
 
        return lstPersons;
    }
    
    @RequestMapping(value = "/user/list", method = { RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    List<User> listUser(@RequestParam(value = "name", required = false, defaultValue = "") String name) {
        return userService.getAllUser();
    }

    @RequestMapping(value = "/per/{id}", method = { RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    Person selectPersonFetchOrder(@PathVariable("id") int id) {
    	return userService.selectPersonFetchOrder(id);
    }

    @RequestMapping(value = "/order/{id}", method = { RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    Orders selectOrdersFetchPerson(@PathVariable("id") String id) {
    	return ordersMapper.selectOrdersFetchPerson(id);
    }
  /**  
    @RequestMapping(value = "/findBarracks/{id}", method = { RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody Barrack findBarracks(@PathVariable("id") long  userId) throws Exception{
    	Jedis jedis = new Jedis("127.0.0.1", 6379);
    	byte[] key = NativeCodec.toBytes(userId);
		byte[] bytes = jedis.get(key);
    	if(bytes == null){
    		Random random = new Random();
    		Barrack barrack =  new Barrack();
    		barrack.setArcherNum(random.nextInt(Integer.MAX_VALUE));
    		barrack.setFootmanNum(random.nextInt(Integer.MAX_VALUE));
    		barrack.setRiderNum(random.nextInt(Integer.MAX_VALUE));
    		barrack.setSpecialNum(random.nextInt(Integer.MAX_VALUE));
    		jedis.set(key, ProtobufProxy.create(Barrack.class).encode(barrack));
    		return barrack;
    	}else{
//    		jedis.del(key);
    	}
		return ProtobufProxy.create(Barrack.class).decode(bytes);
    }*/
}
