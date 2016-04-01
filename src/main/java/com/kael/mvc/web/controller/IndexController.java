package com.kael.mvc.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kael.model.Person0;
/**
 * 基于Restful风格架构测试
 * 
 */
@Controller
@RequestMapping
public class IndexController {
	
		/** 日志实例 */
		private static final Logger logger = Logger
				.getLogger(IndexController.class);

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

		@RequestMapping(value = "/person/{id:\\d+}", method = RequestMethod.GET)
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

		@RequestMapping(value = "/person/{id:\\d+}", method = RequestMethod.DELETE)
		public @ResponseBody
		Object deletePerson(@PathVariable("id") int id) {
			logger.info("删除人员信息id=" + id);
			Map<String, Object> jsonObject = new HashMap<String, Object>();
			jsonObject.put("msg", "删除人员信息成功");
			return jsonObject;
		}

		@RequestMapping(value = "/person", method = RequestMethod.POST)
		public @ResponseBody
		Object addPerson(Person0 person) {
			logger.info("注册人员信息成功id=" + person.getId());
			Map<String, Object> jsonObject = new HashMap<String, Object>();
			jsonObject.put("msg", "注册人员信息成功");
			return jsonObject;
		}

		@RequestMapping(value = "/person", method = RequestMethod.PUT)
		public @ResponseBody
		Object updatePerson(Person0 person) {
			logger.info("更新人员信息id=" + person.getId());
			Map<String, Object> jsonObject = new HashMap<String, Object>();
			jsonObject.put("msg", "更新人员信息成功");
			return jsonObject;
		}

		@RequestMapping(value = "/person", method = RequestMethod.PATCH)
		public @ResponseBody
		List<Person0> listPerson(
				@RequestParam(value = "name", required = false, defaultValue = "") String name) {

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
}